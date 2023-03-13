import java.util.*;

public class Main {
	static int N, M, K, cnt = 1;
	static boolean visited[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		sc.nextLine();
		visited = new boolean[N][M];

		// 입력
		for (int k = 0; k < K; k++) {
			String s[] = sc.nextLine().split(" "); // 0 2 4 4
			for (int i = Integer.parseInt(s[0]); i < Integer.parseInt(s[2]); i++) {
				for (int j = Integer.parseInt(s[1]); j < Integer.parseInt(s[3]); j++) {
					visited[j][i] = true;
				}
			}
		}
		LinkedList<Integer> area = new LinkedList<>();

		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					cnt = 0;
					area.add(bfs(i, j));
				}
			}
		}
		System.out.println(area.size());
		Collections.sort(area);
		for (int a : area) {
			System.out.print(a + " ");
		}

	}

	static int bfs(int i, int j) {
		Queue<Pair> q = new LinkedList<>();
		visited[i][j] = true;
		q.add(new Pair(i, j));

		while (!q.isEmpty()) { // 목표는 영역 구해주기
			Pair p = q.poll();
			cnt++;
			for (int a = 0; a < 4; a++) {
				int nx = dx[a] + p.x;
				int ny = dy[a] + p.y;
				if (0 <= nx && nx < N && 0 <= ny && ny < M) {
					if (!visited[nx][ny]) {
						q.add(new Pair(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
		return cnt;
	}

	static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
