import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n;
	static int[][] map;
	static int[] order; // 학생 앉히는 순서
	static List<Integer>[] list; // 좋아하는 학생 담는 컬렉션
	static PriorityQueue<Point> queue = new PriorityQueue<>(); // 정렬 큐
	static int[] y = { 1, -1, 0, 0 }, x = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		map = new int[n + 1][n + 1];
		order = new int[n * n + 1];

		list = new ArrayList[n * n + 1];

		// 좋아하는 학생 적는 자리
		for (int i = 1; i <= n * n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i <= n * n; i++) {
			st = new StringTokenizer(br.readLine());
			order[i] = Integer.parseInt(st.nextToken());
			for (int j = 1; j < 5; j++) {
				list[order[i]].add(Integer.parseInt(st.nextToken()));
			}
		}

		// 1번학생부터 n*n학생까지 돌려준다.
		for (int i = 1; i <= n * n; i++) {
			solve(order[i]);
			queue.clear();
		}

		int result = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				result += getPrice(i, j);
			}
		}
		System.out.println(result);

	}

	private static void solve(int num) {
		int ny, nx;
		int nearCount, likeCount;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				nearCount = 0;
				likeCount = 0;
				if (map[i][j] != 0) {
					continue;
				}
				// 상하좌우에 해당하는 값들을 확인
				for (int k = 0; k < 4; k++) {
					ny = i + y[k];
					nx = j + x[k];
					if (ny >= 1 && ny <= n && nx >= 1 && nx <= n) {
						// 주위에 좋아하는 학생 수 카운트
						for (Integer now : list[num]) {
							if (now == map[ny][nx]) {
								likeCount++;
							}
						}
						// 주변에 비어있는 칸이 있는 수를 구하는 로직
						if (map[ny][nx] == 0) {
							nearCount++;
						}
					}
				}
				// 주변의 수, 좋아하는 학생이 주변에 몇명있는지를 저장
				queue.add(new Point(i, j, nearCount, likeCount));
			}
		}

		// 꺼내면 가장 적절한 값
		Point poll = queue.poll();
		map[poll.y][poll.x] = num;
	}

	private static int getPrice(int a, int b) {
		int num = map[a][b];

		int ny, nx, likeCount = 0;
		for (Integer now : list[num]) {
			for (int i = 0; i < 4; i++) {
				ny = a + y[i];
				nx = b + x[i];
				if (ny >= 1 && ny <= n && nx >= 1 && nx <= n) {
					if (map[ny][nx] == now) {
						likeCount++;
					}
				}
			}
		}
		if (likeCount == 0) {
			return 0;
		} else if (likeCount == 1) {
			return 1;
		} else if (likeCount == 2) {
			return 10;
		} else if (likeCount == 3) {
			return 100;
		} else {
			return 1000;
		}
	}
}

class Point implements Comparable<Point> {
	int y;
	int x;
	int nearCount;
	int likeCount;

	public Point(int y, int x, int nearCount, int likeCount) {
		this.y = y;
		this.x = x;
		this.nearCount = nearCount;
		this.likeCount = likeCount;
	}

	@Override
	public int compareTo(Point o2) {
		if (this.likeCount > o2.likeCount) {
			return -1;
		} else if (this.likeCount < o2.likeCount) {
			return 1;
		} else {
			if (this.nearCount > o2.nearCount) {
				return -1;
			} else if (this.nearCount < o2.nearCount) {
				return 1;
			} else {
				if (this.y > o2.y) {
					return 1;
				} else if (this.y < o2.y) {
					return -1;
				} else {
					if (this.x > o2.x) {
						return 1;
					} else {
						return -1;
					}
				}
			}
		}
	}
}
