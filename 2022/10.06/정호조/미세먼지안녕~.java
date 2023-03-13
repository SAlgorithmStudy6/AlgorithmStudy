import java.util.*;
import java.io.*;

public class 미세먼지안녕_17144 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static Map[][] map;
	static boolean[][] vis;
	static int[] machine;
	static int R, C, T, ans;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new Map[R][C];
		vis = new boolean[R][C];
		machine = new int[2];
		ans = 0;

		int idx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = new Map(temp, 0);
				if (temp == -1)
					machine[idx++] = i;
			}
		}
		diffusion(0);
		
		System.out.println(ans);
	}

	// 확산 시켜줌
	static void diffusion(int time) {
		if (time == T) {
			ans = sum();
			return;
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int cnt = 0;
				if (map[i][j].value != -1 && map[i][j].value != 0) {
					for (int k = 0; k < 4; k++) {
						int rr = i + dy[k];
						int cc = j + dx[k];
						if (0 <= rr && rr < R && 0 <= cc && cc < C && map[rr][cc].value != -1) {
							map[rr][cc].valueTemp += map[i][j].value / 5;
							cnt++; // 확산시켜줄 때마다 cnt++해서 남은 양 구할 때 써먹음
						}
					}
					map[i][j].value = map[i][j].value - (map[i][j].value / 5) * cnt;
				}
			}
		}
		
		// 확산 때 임시로 담아놨던 값을 value에 다 더해줌
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j].value += map[i][j].valueTemp;
				map[i][j].valueTemp = 0;
			}
		}
		move(time);
	}

	// 공기청정기 바람 따라 이동
	static void move(int time) {
		// 윗 칸의 공기청정기로 들어오는 쪽부터 시작
		int r = machine[0] - 1;
		int c = 0;
		for (int i = r; i > 0; i--) {
			map[i][c].value = map[i - 1][c].value;
		}
		r = 0;
		for (int i = 0; i < C - 1; i++) {
			map[r][i].value = map[r][i + 1].value;
		}
		c = C - 1;
		for (int i = 0; i < machine[0]; i++) {
			map[i][c].value = map[i + 1][c].value;
		}
		r = machine[0];
		for (int i = C - 1; i > 1; i--) { // 0번 열에 공기청정기 있으므로 조건식 주의
			map[r][i].value = map[r][i - 1].value;
		}

		// 아래칸 시작
		r = machine[1] + 1;
		c = 0;
		for (int i = r; i < R - 1; i++) {
			map[i][c].value = map[i + 1][c].value;
		}
		r = R - 1;
		for (int i = 0; i < C - 1; i++) {
			map[r][i].value = map[r][i + 1].value;
		}
		c = C - 1;
		for (int i = R - 1; i > machine[1]; i--) {
			map[i][c].value = map[i - 1][c].value;
		}
		r = machine[1];
		for (int i = C - 1; i > 1; i--) {
			map[r][i].value = map[r][i - 1].value;
		}
		map[machine[0]][1].value = 0;
		map[machine[1]][1].value = 0;
		
		diffusion(time+1);
	}

	static int sum() {
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j].value != 0 && map[i][j].value != -1) {
					result += map[i][j].value;
				}
			}
		}

		return result;
	}

	public static class Map {
		int value, valueTemp;

		public Map(int value, int valueTemp) {
			this.value = value;
			this.valueTemp = valueTemp;	//확산 된 먼지들 임시로 담아줄 변수(동시에 확산되므로 순회돌 때 기존 값이 변경되면 안됨)
		}
	}

}
