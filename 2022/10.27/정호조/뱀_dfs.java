import java.util.*;
import java.io.*;

public class 뱀 {

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static ArrayList<Point> snake, copy;
	static ArrayList<Dir> direction;
	static int[][] map;
	static int n, k, l, time, dir;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		map = new int[n][n];
		snake = new ArrayList<>();
		copy = new ArrayList<>();
		direction = new ArrayList<>();
		snake.add(new Point(0, 0));

		// 사과 입력
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r - 1][c - 1] = 1;
		}

		// 방향 전환 list 생성
		l = Integer.parseInt(br.readLine());
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			direction.add(new Dir(time, dir));
		}

		time = 0;
		dir = 1;

		dfs();

		System.out.println(time);
	}

	static void dfs() {
		time++;
		Point nextHead = new Point(snake.get(0).r + dy[dir], snake.get(0).c + dx[dir]);
		boolean check = checkDone(nextHead.r, nextHead.c);
		if (check)
			return;

		// 다음 칸에 사과가 있으면 nextHead를 0번 인덱스에 넣고, 원본 배열을 그 뒤에 이어줌(길이 +1)
		copy.clear();
		copy.addAll(snake);
		snake.clear();
		snake.add(nextHead);
		snake.addAll(copy);
		if (map[nextHead.r][nextHead.c] == 1) {
			map[nextHead.r][nextHead.c] = 0;
		} else if (map[nextHead.r][nextHead.c] != 1) { // 사과 없으면 맨 뒤 remove(이동 후 길이 그대로)
			snake.remove(snake.size() - 1);
		}

		// 방향 전환할 때인지 체크하고 맞으면 방향 전환
		if (direction.size() > 0 && direction.get(0).time == time) { // 시간 순서대로 주어지므로 0번 인덱스 참조하고 remove
			if (direction.get(0).dir == 'D') {
				dir++;
				if (dir >= 4)
					dir = 0;
			} else if (direction.get(0).dir == 'L') {
				dir--;
				if (dir < 0)
					dir = 3;
			}
			direction.remove(0);
		}

		dfs();
	}

	// 게임 끝났는지 검사
	static boolean checkDone(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= n || checkTouchMyBody(r, c)) {
			return true;
		} else
			return false;
	}

	// 자기 몸 부딛혔는지 검사
	static boolean checkTouchMyBody(int r, int c) {
		for (int i = 0; i < snake.size(); i++) {
			if (snake.get(i).r == r && snake.get(i).c == c) {
				return true;
			}
		}
		// contains()로 객체 포함 여부를 비교하기 위해서는 객체에 equals()를 정의해야함
//		Point temp = new Point(r, c);
//		if (snake.contains(temp))
//			return true;
//		else
		return false;
	}

	public static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static class Dir {
		int time;
		char dir;

		public Dir(int time, char dir) {
			this.time = time;
			this.dir = dir;
		}
	}
}
