import java.io.*;
import java.util.*;

public class Main {

	static int[][] house = new int[17][17];
	static int N;
	static int count = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		for (int i = 1; i <= N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				house[i][j] = Integer.parseInt(token.nextToken());
			}
		}


		dfs(2, 1, 1);

		System.out.println(count);

	}

	static void dfs(int col, int row, int direction) {
		if (col == N && row == N) {
			count++;
			return;
		}

		switch (direction) { // 방향
		case 1:
			if (col + 1 <= N && house[row][col + 1] == 0) { // 가로
				dfs(col + 1, row, 1);
			}
			break;
		case 2:
			if (row + 1 <= N && house[row + 1][col] == 0) {// 세로
				dfs(col, row + 1, 2);
			}
			break;
		case 3:
			if (row + 1 <= N && house[row + 1][col] == 0) {// 세로
				dfs(col, row + 1, 2);
			}
			if (col + 1 <= N && house[row][col + 1] == 0) {// 가로
				dfs(col + 1, row, 1);
			}
			break;
		}

		// 대각선
		if (row + 1 <= N && col + 1 <= N && house[row][col + 1] == 0 && house[row + 1][col] == 0
				&& house[row + 1][col + 1] == 0) {
			dfs(col + 1, row + 1, 3);
		}
	}
}
