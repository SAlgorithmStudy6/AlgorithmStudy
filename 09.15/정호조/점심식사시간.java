import java.util.*;
import java.io.*;

public class 점심식사시간_2383 {
	static int[][] map;
	static ArrayList<Person> person;
	static ArrayList<Group> groupa, groupb;
	static ArrayList<Point> k;
	static boolean[] vis;
	static int ans, n, index, resulta, resultb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {

			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			person = new ArrayList<>();
			k = new ArrayList<>();
			ans = Integer.MAX_VALUE;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 1)
						k.add(new Point(i, j, map[i][j]));
				}
			}

			// 사람마다 좌표와 계단 두 개까지의 거리를 입력
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == 1) {
						int timea = Math.abs(i - k.get(0).r) + Math.abs(j - k.get(0).c);
						int timeb = Math.abs(i - k.get(1).r) + Math.abs(j - k.get(1).c);
						person.add(new Person(i, j, timea, timeb));
					}
				}
			}

			// 한 그룹에 사람 0명~전부 한 계단을 이용할 그룹 편성
			vis = new boolean[person.size()];
			makeorder(0);

			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void makeorder(int idx) {
		if (idx == person.size()) {

			// a, b계단 이용할 그룹 편성
			groupa = new ArrayList<>();
			groupb = new ArrayList<>();
			for (int i = 0; i < person.size(); i++) {
				if (vis[i]) {
					int time = person.get(i).timea;
					groupa.add(new Group(time, 0, 0));
				} else {
					int time = person.get(i).timeb;
					groupb.add(new Group(time, 0, 0));
				}
			}

			resulta = 0;
			resultb = 0;
			// a계단 시작
			if (groupa.size() != 0) {
				groupa.sort(new Comparator<Group>() { // 그룹을 시간에 대해 오름차순으로 정렬
					@Override
					public int compare(Group g1, Group g2) {
						// TODO Auto-generated method stub
						return g1.time - g2.time;
					}
				});
				move(0, groupa.get(0).time, groupa, 0, 0, 0);
			}

			// b계단 시작
			if (groupb.size() != 0) {
				groupb.sort(new Comparator<Group>() { // 그룹을 시간에 대해 오름차순으로 정렬
					@Override
					public int compare(Group g1, Group g2) {
						// TODO Auto-generated method stub
						return g1.time - g2.time;
					}
				});

				move(1, groupb.get(0).time, groupb, 0, 0, 0);
			}

			int result = Math.max(resulta, resultb);
			ans = Math.min(ans, result);
			return;
		}
		vis[idx] = true;
		makeorder(idx + 1);
		vis[idx] = false;
		makeorder(idx + 1);
	}

	static void move(int knum, int time, ArrayList<Group> group, int complete, int wtemp, int stemp) {
		if (complete == group.size()) {
			if (knum == 0)
				resulta = time - 1;
			else if (knum == 1)
				resultb = time - 1;
			return;
		}
		int wait = wtemp;
		int stair = stemp;
		int cpl = complete;

		for (int i = 0; i < group.size(); i++) {

			// 이동 완료
			if (group.get(i).entertime == 0 && time == group.get(i).time + 1 + k.get(knum).time) {
				group.get(i).status = 3;
				cpl++;
				stair--;
			} else if (group.get(i).entertime != 0 && time == group.get(i).entertime + k.get(knum).time) {

				group.get(i).status = 3;
				cpl++;
				stair--;
			}

			// 계단 입장
			if (time == group.get(i).time + 1 || group.get(i).status == 5) {
				if (stair < 3) {
					if (group.get(i).status == 5) {
						group.get(i).entertime = time;
					}
					group.get(i).status = 2;
					stair++;
					wait--;
				}
			}
			// 계단 입구 도착
			if (time == group.get(i).time || group.get(i).status == 4) {
				if (wait < 3) {
					group.get(i).status = 1;
					wait++;
				}
			}

			// 시간 지났는데 밀려서 못간 사람
			if (time >= group.get(i).time) {
				if (group.get(i).status == 0 && wait == 3) {
					group.get(i).status = 4;
				} else if (group.get(i).status == 1 && stair == 3) {
					group.get(i).status = 5;
				}
			}

		}

		move(knum, time + 1, group, cpl, wait, stair);
	}

	public static class Point {
		int r, c, time;

		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	public static class Group {
		int time, status, entertime; // 밀리면 entertime에 밀린 시간 넣어줌

		public Group(int time, int status, int entertime) {
			this.time = time;
			this.status = status; // 0:입구 도착 전, 1:입구도착, 2:계단, 3:이동완료, 4:입구도착 밀림, 5:계단 입장 밀림,
			this.entertime = entertime;
		}
	}

	public static class Person {
		int r, c, timea, timeb;

		public Person(int r, int c, int timea, int timeb) {
			this.r = r;
			this.c = c;
			this.timea = timea;
			this.timeb = timeb;
		}
	}
}
