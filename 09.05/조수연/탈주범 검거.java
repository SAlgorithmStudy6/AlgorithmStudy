import java.awt.Point;
import java.io.*;
import java.util.*;

public class Solution {

	static int N; // 세로 크기
	static int M; // 가로 크기
	static int L; // 탈출 후 소요된 시간
	static int[][] map; // 터널 지도
	static boolean[][] visited; // 방문 체크
	static int count = 0; // 도달할 수 있는 지점
	static List<Point> thief; // 도둑의 위치

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			N = Integer.parseInt(token.nextToken());
			M = Integer.parseInt(token.nextToken());
			int R = Integer.parseInt(token.nextToken()); // 맨홀 뚜껑이 위치한 세로 위치
			int C = Integer.parseInt(token.nextToken()); // 맨홀 뚜껑이 위치한 가로 위치
			L = Integer.parseInt(token.nextToken());

			map = new int[N][M];
			visited = new boolean[N][M];
			count = 0;
			thief = new LinkedList();

			for (int i = 0; i < N; i++) {
				token = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(token.nextToken());
				}
			}

			escape(R, C);
			
			bw.write("#"+String.valueOf(tc)+" "+String.valueOf(count)+"\n");
		}
		bw.flush();
		bw.close();
	}

	static void escape(int R, int C) { // 탈출 bfs
		Queue<Point> queue = new LinkedList();
		queue.add(new Point(C, R));
		visited[R][C] = true;
		count++;
		thief.add(new Point(C, R));

		for (int loop = 1; loop < L; loop++) {
			int qSize = queue.size();
			for (int i = 0; i < qSize; i++) { // 동시에 진행
				Point point = queue.poll();

				int direction = map[point.y][point.x]; // 방향

				switch (direction) { // 방향값에 따른 좌표 이동
				case 1:
					int[] x1 = { 0, 0, -1, 1 };
					int[] y1 = { -1, 1, 0, 0 };
					for (int j = 0; j < x1.length; j++) {
						int x = point.x + x1[j];
						int y = point.y + y1[j];
						// x 좌표는 0 ~ M-1 y좌표는 0 ~ N -1 , 방문한 적이 없으며 파이프가 있는 곳
						if (x >= 0 && x < M && y >= 0 && y < N && !visited[y][x] && map[y][x] != 0) { 
							switch (j) { //다음 파이크가 연결될 수 있는지 체크
							case 0:
								if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 5 || map[y][x] == 6) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;

							case 1:
								if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 4 || map[y][x] == 7) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;
							case 2:
								if (map[y][x] == 1 || map[y][x] == 3 || map[y][x] == 4 || map[y][x] == 5) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;
							case 3:
								if (map[y][x] == 1 || map[y][x] == 3 || map[y][x] == 6 || map[y][x] == 7) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;
							}
						}
					}
					break;
				case 2:
					int[] y2 = { -1, 1 };
					for (int j = 0; j < y2.length; j++) {
						int x = point.x;
						int y = point.y + y2[j];

						if (x >= 0 && x < M && y >= 0 && y < N && !visited[y][x] && map[y][x] != 0) {
							switch (j) {
							case 0:
								if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 5 || map[y][x] == 6) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;

							case 1:
								if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 4 || map[y][x] == 7) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;
							}
						}
					}
					break;
				case 3:
					int[] x3 = { -1, 1 };

					for (int j = 0; j < x3.length; j++) {
						int x = point.x + x3[j];
						int y = point.y;

						if (x >= 0 && x < M && y >= 0 && y < N && !visited[y][x] && map[y][x] != 0) {
							switch (j) {
							case 0:
								if (map[y][x] == 1 || map[y][x] == 3 || map[y][x] == 4 || map[y][x] == 5) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;

							case 1:
								if (map[y][x] == 1 || map[y][x] == 3 || map[y][x] == 6 || map[y][x] == 7) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;
							}
						}
					}
					break;
				case 4:
					int[] x4 = { 0, 1 };
					int[] y4 = { -1, 0 };

					for (int j = 0; j < y4.length; j++) {
						int x = point.x + x4[j];
						int y = point.y + y4[j];

						if (x >= 0 && x < M && y >= 0 && y < N && !visited[y][x] && map[y][x] != 0) {
							switch (j) {
							case 0:
								if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 5 || map[y][x] == 6) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;

							case 1:
								if (map[y][x] == 1 || map[y][x] == 3 || map[y][x] == 6 || map[y][x] == 7) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;
							}
							
						}
					}
					break;
				case 5:
					int[] x5 = { 0, 1 };
					int[] y5 = { 1, 0 };
					for (int j = 0; j < y5.length; j++) {
						int x = point.x + x5[j];
						int y = point.y + y5[j];

						if (x >= 0 && x < M && y >= 0 && y < N && !visited[y][x] && map[y][x] != 0) {
							switch (j) {
							case 0:
								if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 4 || map[y][x] == 7) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;

							case 1:
								if (map[y][x] == 1 || map[y][x] == 3 || map[y][x] == 6 || map[y][x] == 7) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;
							}	
						}
					}
					break;
				case 6:
					int[] x6 = { 0, -1 };
					int[] y6 = { 1, 0 };
					for (int j = 0; j < y6.length; j++) {
						int x = point.x + x6[j];
						int y = point.y + y6[j];

						if (x >= 0 && x < M && y >= 0 && y < N && !visited[y][x] && map[y][x] != 0) {
							switch (j) {
							case 0:
								if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 4 || map[y][x] == 7) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;

							case 1:
								if (map[y][x] == 1 || map[y][x] == 3 || map[y][x] == 4 || map[y][x] == 5) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;
							}
						}
					}
					break;
				case 7:
					int[] x7 = { 0, -1 };
					int[] y7 = { -1, 0 };
					for (int j = 0; j < y7.length; j++) {
						int x = point.x + x7[j];
						int y = point.y + y7[j];

						if (x >= 0 && x < M && y >= 0 && y < N && !visited[y][x] && map[y][x] != 0) {
							switch (j) {
							case 0:
								if (map[y][x] == 1 || map[y][x] == 2 || map[y][x] == 5 || map[y][x] == 6) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;

							case 1:
								if (map[y][x] == 1 || map[y][x] == 3 || map[y][x] == 4 || map[y][x] == 5) {
									visited[y][x] = true;
									queue.add(new Point(x, y));
									thief.add(new Point(x, y));
									count++;
								}
								break;
							}
						}
					}
					break;
				}
			}
		}
	}
}
