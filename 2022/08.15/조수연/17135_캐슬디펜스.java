import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int D;
	static int[] archers = new int[3];
	static boolean[][] visited;
	static int max = Integer.MIN_VALUE;
	static int[][] castle;
	static int[][] temp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		D = Integer.parseInt(token.nextToken());

		castle = new int[N + 1][M + 1];
		temp = new int[N + 1][M + 1]; // 복사 배열

		for (int i = 1; i <= N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				castle[i][j] = Integer.parseInt(token.nextToken());
				temp[i][j] = castle[i][j];
			}
		}

		archers = new int[3];

		combination(0, 1, archers, castle);

		System.out.println(max);

	}

	static void combination(int size, int start, int[] archers, int[][] castle) { // 궁수 자리 배치

		if (size == 3) {
			copy(); // 배열 초기화
			max = Math.max(max, defense(archers));
			return;
		}

		for (int i = start; i <= M; i++) {
			archers[size] = i;
			combination(size + 1, i + 1, archers, castle);
		}
	}

	static int defense(int[] archers) {
		int count = 0;
		for (int n = 1; n <= N; n++) { // 총 N번 이동
			visited = new boolean[N + 1][M + 1];
			for (int k = 0; k < archers.length; k++) {

				int archerX = archers[k]; // 궁수의 x좌표
				int minD = Integer.MAX_VALUE;
				int minR = Integer.MAX_VALUE;
				int minC = Integer.MAX_VALUE;

				for (int i = 1; i <= N; i++) { // 시작점부터 1인곳을 탐색
					for (int j = 1; j <= M; j++) {
						if (castle[i][j] == 1) {
							int distance = Math.abs(i - (N + 1)) + Math.abs(j - archerX); // 거리계산
							if (minD >= distance) {
								if (minD > distance) { // 최소거리보다 작으면 거리,x,y갱신
									minD = distance;
									minR = i;
									minC = j;
								} else {
									if (minC > j) { // 거리같으면 왼쪽부터 쏜다고했으니 왼쪽 좌표 갱신
										minR = i;
										minC = j;
									}
								}
							}
						}
					}
				}

				if (minD <= D) { // 거리가 가장 최소이고 같으면 왼쪽인 좌표를 방문
					visited[minR][minC] = true;
				}
			}

			for (int i = 1; i <= N; i++) { // 궁수가 같은 적을 쏠수도 있기때문에 체크배열을 통해 count갱신
				for (int j = 1; j <= M; j++) {
					if (visited[i][j]) {
						castle[i][j] = 0;
						count++;
					}
				}
			}

			for (int i = 1; i <= M; i++) { // 적이 성에 도착
				castle[N][i] = 0;
			}

			for (int i = N; i >= 1; i--) { // 윗줄 적을 아래로 1칸씩
				for (int j = 1; j <= M; j++) {
					castle[i][j] = castle[i - 1][j];
				}
			}

		}
		return count;
	}

	static void copy() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				castle[i][j] = temp[i][j];
			}
		}
	}
}
