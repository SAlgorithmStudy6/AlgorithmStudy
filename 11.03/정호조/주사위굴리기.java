import java.util.*;
import java.io.*;

public class 주사위굴리기_14499 {

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static ArrayList<Integer> dice, copy, dir;
	static int[][] map;
	static int n, m, k, curR, curC;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		curR = Integer.parseInt(st.nextToken());
		curC = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		dice = new ArrayList<>();
		dir = new ArrayList<>();
		copy = new ArrayList<>();

		// 배열 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//주사위 입력(초기값 0)
		for(int i=0; i<6; i++) {
			dice.add(0);
		}

		// 주사위 굴릴 방향 입력(0 ~ 3)
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			dir.add(Integer.parseInt(st.nextToken()) - 1);
		}

		for (int i = 0; i < k; i++) {
			check();
		}

	}

	static void check() {
		int nextR = curR + dy[dir.get(0)];
		int nextC = curC + dx[dir.get(0)];

		if (0 <= nextR && nextR < n && 0 <= nextC && nextC < m) { // 다음 좌표가 배열 범위 안에 있을 때만 실행
			curR = nextR;
			curC = nextC;

			move(dir.get(0)); // 주사위 굴려줌

			// 주사위 밑부분과 닿은 칸 확인하고 작업
			if (map[curR][curC] == 0) {
				map[curR][curC] = dice.get(5);
			} else if (map[curR][curC] != 0) {
				dice.set(5, map[curR][curC]);
				map[curR][curC] = 0;
			}

			System.out.println(dice.get(0)); // 주사위 윗 면 출력
		}
		dir.remove(0);
	}

	static void move(int direction) {
		copy.clear();
		copy.addAll(dice);
		switch (direction) {
		case 0: { // 동쪽 이동
			dice.set(0, copy.get(2));
			dice.set(2, copy.get(5));
			dice.set(3, copy.get(0));
			dice.set(5, copy.get(3));
			break;
		}
		case 1: { // 서쪽 이동
			dice.set(0, copy.get(3));
			dice.set(2, copy.get(0));
			dice.set(3, copy.get(5));
			dice.set(5, copy.get(2));
			break;
		}
		case 2: { // 북쪽 이동
			dice.set(0, copy.get(1));
			dice.set(1, copy.get(5));
			dice.set(4, copy.get(0));
			dice.set(5, copy.get(4));
			break;
		}
		case 3: { // 남쪽 이동
			dice.set(0, copy.get(4));
			dice.set(1, copy.get(0));
			dice.set(4, copy.get(5));
			dice.set(5, copy.get(1));
			break;
		}
		}
	}
}
