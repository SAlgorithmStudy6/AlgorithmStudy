import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int K;
	static int[][] arr;
	static int[][] copied;
	static int[][] rotateArr;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());

		arr = new int[N + 1][M + 1];
		copied = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				int value = Integer.parseInt(token.nextToken());
				arr[i][j] = value;
				copied[i][j] = value;

			}
		}

		rotateArr = new int[K][3];

		for (int i = 0; i < K; i++) {
			token = new StringTokenizer(br.readLine());
			rotateArr[i][0] = Integer.parseInt(token.nextToken());
			rotateArr[i][1] = Integer.parseInt(token.nextToken());
			rotateArr[i][2] = Integer.parseInt(token.nextToken());

		}

		visited = new boolean[K];
		int[] order = new int[K];

		combination(0, order);

		System.out.println(min);

	}

	static void init() {
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j < arr[i].length; j++) {
				arr[i][j] = copied[i][j];
			}
		}
	}

	static void combination(int size, int[] order) {
		if (size == K) {
			init();
			rotate(order);
			min = Math.min(min, getMin());
			return;
		}

		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				order[size] = i;
				combination(size + 1, order);
				visited[i] = false;
			}
		}
	}

	static void rotate(int[] order) {
		for (int i = 0; i < order.length; i++) {
			int direction = 1;
			int[][] temp = new int[N + 1][M + 1];

			int r = rotateArr[order[i]][0];
			int c = rotateArr[order[i]][1];
			int s = rotateArr[order[i]][2];

			int row = r - s;
			int col = c - s;
			int target = arr[row + 1][col];

			while (true) {

				if (row == r && col == c) {
					temp[row][col] = arr[row][col];
					break;
				}

				temp[row][col] = target;

				switch (direction) {
				case 1:
					if (col == c + s || temp[row][col + 1] != 0) {
						direction = 2;
						row++;
						target = arr[row - 1][col];

					} else {
						col++;
						target = arr[row][col - 1];

					}
					break;

				case 2:
					if (row == r + s || temp[row + 1][col] != 0) {
						direction = 3;
						col--;
						target = arr[row][col + 1];

					} else {
						row++;
						target = arr[row - 1][col];

					}
					break;
				case 3:
					if (col == c - s || temp[row][col - 1] != 0) {
						direction = 4;
						row--;
						target = arr[row + 1][col];

					} else {
						col--;
						target = arr[row][col + 1];

					}
					break;
				case 4:
					if (row == r - s || temp[row - 1][col] != 0) {
						direction = 1;
						col++;
						target = arr[row+1][col];

					} else {
						row--;
						target = arr[row + 1][col];

					}
					break;
				}

			}


			for (int j = r - s; j <= r + s; j++) {
				for (int k = c - s; k <= c + s; k++) {
					arr[j][k] = temp[j][k];
				}
			}

		}
	}

	static int getMin() {
		int rowMin = Integer.MAX_VALUE;
		for (int i = 1; i < arr.length; i++) {
			int sum = 0;
			for (int j = 1; j < arr[i].length; j++) {
				sum += arr[i][j];
			}
			rowMin = Math.min(rowMin, sum);
		}
		return rowMin;
	}
}
