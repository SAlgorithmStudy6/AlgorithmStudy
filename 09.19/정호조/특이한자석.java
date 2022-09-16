import java.util.*;
import java.io.*;

public class 특이한자석_4013 {
	static int[] dx = { 1, -1 };
	static int k, ans, magnum, dir;
	static boolean[] flag;
	static int[] direction;
	static ArrayList[] mag = new ArrayList[4];;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			k = Integer.parseInt(br.readLine());
			ans = 0;

			for (int i = 0; i < 4; i++) {
				mag[i] = new ArrayList<>();
			}

			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					mag[i].add(Integer.parseInt(st.nextToken()));
				}
			}

			for (int i = 0; i < k; i++) {
				direction = new int[4];
				flag = new boolean[4];

				st = new StringTokenizer(br.readLine());
				magnum = Integer.parseInt(st.nextToken()) - 1;
				dir = Integer.parseInt(st.nextToken());

				direction[magnum] = dir;
				flag[magnum] = true;
				check(mag, magnum, dir);
			}

			for (int i = 0; i < 4; i++) {
				ans += (int) mag[i].get(0) * Math.pow(2, i);
			}

			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void check(ArrayList[] mag, int magnum, int dir) {
		// 모든 자석의 방향을 정해주고 시작
		for (int j = 0; j < 4; j++) {
			if (j == magnum)
				continue;
			if (magnum % 2 == j % 2) {
				direction[j] = dir;
			} else
				direction[j] = revdir(dir);
		}
		
		//주어진 자석을 중심으로 퍼져나가며 다른 자석들이 회전할 수 있는지 확인(for문으로 순서대로 확인하면 결과가 달라짐)
		int cnt = 1;
		Queue<Integer> q = new LinkedList<>();
		q.add(magnum);
		while (!q.isEmpty()) {
			int temp = q.poll();
			for (int i = 0; i < 2; i++) {
				int num = temp + dx[i];
				if (0 <= num && num < 4) {
					if (flag[num] == false) {
						if (num < temp) {
							if (mag[temp].get(6) != mag[num].get(2)) {
								cnt++;
								flag[num] = true;
								q.add(num);
							}
						} else if (temp < num) {
							if (mag[temp].get(2) != mag[num].get(6)) {
								cnt++;
								flag[num] = true;
								q.add(num);
							}
						}
					}
				}
			}
		}

		turn(mag);
	}

	static void turn(ArrayList[] mag) {
		for (int i = 0; i < 4; i++) {
			if (flag[i]) {
				if (direction[i] == 1) { // 시계방향
					int temp = (int) mag[i].get(7);
					for (int j = 7; j >= 1; j--) {
						mag[i].set(j, mag[i].get(j - 1));
					}
					mag[i].set(0, temp);
				} else if (direction[i] == -1) { // 반시계방향
					int temp = (int) mag[i].get(0);
					mag[i].remove(0);
					mag[i].add(temp);
				}
			}
		}
	}

	static int revdir(int dir) {
		if (dir == 1)
			return -1;
		else
			return 1;
	}
}
