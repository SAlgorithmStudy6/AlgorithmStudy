import java.util.*;
import java.io.*;

public class 드래곤커브_15685 {
	static int[][] board;
	static int ans, n, startR, dir, startC, gen;
	static ArrayList<Integer> dirTemp;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		board = new int[101][101];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			startC = Integer.parseInt(st.nextToken());
			startR = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			gen = Integer.parseInt(st.nextToken());

			dirTemp = new ArrayList<>();
			dirTemp.add(dir);
			board[startR][startC] = 1;
			makeDir();
			draw();
		}

		checkResult();
		System.out.println(ans);
	}

	static void makeDir() {
		// 그려야하는 선의 개수는 2^gen 개수
		if (dirTemp.size() == Math.pow(2, gen)) {
			return;
		}
		for (int i = dirTemp.size() - 1; i >= 0; i--) {
			int nextDir = dirTemp.get(i)+1;
			if(nextDir > 3) nextDir = 0;
			dirTemp.add(nextDir);
		}
		makeDir();
	}

	static void draw() {
		for (int i = 0; i < dirTemp.size(); i++) {
			int direction = dirTemp.get(i);
			int nextR = startR + dy[direction];
			int nextC = startC + dx[direction];

			board[nextR][nextC] = 1;
			startR = nextR;
			startC = nextC;
		}
	}
	
	static void checkResult() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (board[i][j] == 1 && board[i + 1][j] == 1 && board[i][j + 1] == 1 && board[i + 1][j + 1] == 1) {
					ans++;
				}
			}
		}
	}
}
