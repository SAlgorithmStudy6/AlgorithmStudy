import java.util.*;
import java.io.*;

public class 핀볼게임_5650 {
	static int test = 0;
	static int[] dx = { 1, -1, 0, 0 }; // 우 , 좌, 상, 하
	static int[] dy = { 0, 0, -1, 1 };
	static int[][] map;
	static boolean[][] vis;
	static ArrayList<Point> wormhole;
	static int n, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());

			map = new int[n][n];
			vis = new boolean[n][n];
			wormhole = new ArrayList<>();
			ans = 0;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (6 <= map[i][j] && map[i][j] <= 10) {
						wormhole.add(new Point(i, j, 4, map[i][j]));
					}
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] == 0) {
						search(i, j);
					}
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void search(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			boolean start = true;
			int rr = r + dy[i];
			int cc = c + dx[i];
			int dd = i;
			int cnt = 0;
			int result = 0;
			q.add(new Point(rr, cc, dd, cnt));
			while (!q.isEmpty()) {
				Point temp = q.poll();
				if (start) {
					start = false;

				} else {					

					dd = temp.d;
					rr = temp.r + dy[dd];
					cc = temp.c + dx[dd];
					cnt = temp.cnt;
				}
				if (rr < 0 || rr >= n || cc < 0 || cc >= n || map[rr][cc] == 5) {
					q.add(new Point(rr, cc, reverse(dd), cnt + 1));
				}
				else if (0 <= rr && rr < n && 0 <= cc && cc < n) {
					// 벽에 만나거나 5번 블록 만나면 방향 전환
					if ((rr == r && cc == c) || map[rr][cc] == -1) { // 처음 위치로 돌아왔 거나 블랙홀일 떄
						result = cnt;							
						q.clear();
					} else {
						switch (map[rr][cc]) {
						case 0: { // 아무것도 없을 때 방향 대로 진행
							q.add(new Point(rr, cc, dd, cnt));
							break;
						}
						case 1: { // 1번 블록

							if (dd == 0 || dd == 2) { // 1번 블록 좌측, 하단면 맞았을 때 -> 반대방향
								q.add(new Point(rr, cc, reverse(dd), cnt + 1));
							} else if (dd == 1) { // 왼쪽으로 가다가 맞았을 때 -> 상향
								q.add(new Point(rr, cc, 2, cnt + 1));
							} else if (dd == 3) { // 아래로 내려가다가 맞았을 때 -> 우향
								q.add(new Point(rr, cc, 0, cnt + 1));
							}
							break;
						}
						case 2: { // 2번 블록
							if (dd == 0 || dd == 3) { // 2번 블록 좌측, 상단면 맞았을 때 -> 반대방향
								q.add(new Point(rr, cc, reverse(dd), cnt + 1));
							} else if (dd == 1) { // 왼쪽으로 가다가 맞았을 때 -> 하향
								q.add(new Point(rr, cc, 3, cnt + 1));
							} else if (dd == 2) { // 위로 가다가 맞았을 때 -> 우향
								q.add(new Point(rr, cc, 0, cnt + 1));
							}
							break;
						}
						case 3: { // 3번 블록

							if (dd == 1 || dd == 3) { // 3번 블록 우측, 상단면 맞았을 때 -> 반대방향
								q.add(new Point(rr, cc, reverse(dd), cnt + 1));
							} else if (dd == 0) { // 오른쪽으로 가다가 맞았을 때 -> 하향
								q.add(new Point(rr, cc, 3, cnt + 1));
							} else if (dd == 2) { // 위로 가다가 맞았을 때 -> 좌향
								q.add(new Point(rr, cc, 1, cnt + 1));
							}
							break;
						}
						case 4: { // 4번 블록

							if (dd == 1 || dd == 2) { // 4번 블록 우측, 하단면 맞았을 때 -> 반대방향
								q.add(new Point(rr, cc, reverse(dd), cnt + 1));
							} else if (dd == 0) { // 오른쪽으로 가다가 맞았을 때 -> 상향
								q.add(new Point(rr, cc, 2, cnt + 1));
							} else if (dd == 3) { // 아래로 가다가 맞았을 때 -> 좌향
								q.add(new Point(rr, cc, 1, cnt + 1));
							}
							break;
						}
						default: { // 웜홀
							for (int w = 0; w < wormhole.size(); w++) {

								// 번호가 같고 좌표가 다른 웜홀로 이동
								if (wormhole.get(w).cnt == map[rr][cc]
										&& (rr != wormhole.get(w).r || cc != wormhole.get(w).c)) {
									q.add(new Point(wormhole.get(w).r, wormhole.get(w).c, dd, cnt));
									break;
								}
							}
							break;
						}
						}
					}
				}
			}
			ans = Math.max(result, ans);
		}
	}

	static int reverse(int dir) {
		if (dir == 0)
			return 1;
		else if (dir == 1)
			return 0;
		else if (dir == 2)
			return 3;
		else if (dir == 3)
			return 2;
		return 0;
	}

	public static class Point {
		int r, c, d, cnt;

		public Point(int r, int c, int d, int cnt) {
			this.r = r;
			this.c = c;
			this.d = d; // 우:0, 좌:1, 상:2, 하:3, 웜홀:4
			this.cnt = cnt; // 웜홀에서는 num 입력
		}
	}
}
