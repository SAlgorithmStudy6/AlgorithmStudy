import java.util.*;
import java.io.*;

public class PuyoPuyo_11559 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static char[][] board;
	static boolean[][] vis;
	static ArrayList<Point> group;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		board = new char[12][6];
		vis = new boolean[12][6];
		group = new ArrayList<>();
		ans = 0;

		for (int i = 0; i < 12; i++) {
			String str = br.readLine();
			for (int j = 0; j < 6; j++) {
				board[i][j] = str.charAt(j);
			}
		}

		while (checkStart());
		System.out.println(ans);

	}

	static boolean checkStart() {
		boolean result = false;
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (board[i][j] != '.' && !vis[i][j]) {
					group.clear();
					vis = new boolean[12][6];
					if (findGroup(i, j)) {
						result = true;
					}
				}
			}
		}
		if(result) {
			move();
		}
		return result;
	}

	static boolean findGroup(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c));
		vis[r][c] = true;
		char value = board[r][c];
		while (!q.isEmpty()) {
			Point temp = q.poll();
			group.add(new Point(temp.r, temp.c));
			for (int i = 0; i < 4; i++) {
				int nextR = temp.r + dy[i];
				int nextC = temp.c + dx[i];
				if (0 <= nextR && nextR < 12 && 0 <= nextC && nextC < 6 && !vis[nextR][nextC]
						&& board[nextR][nextC] == value) {
					q.add(new Point(nextR, nextC));
					vis[nextR][nextC] = true;
				}
			}
		}
		if (group.size() >= 4) {
			for (int i = 0; i < group.size(); i++) {
				board[group.get(i).r][group.get(i).c] = '.';
			}
			
			return true;
		} else {
	
			return false;
		}

	}

	static void move() {
		ans++;

		for (int j = 0; j < 6; j++) {
			Point start = new Point(-1, -1);
			for (int i = 11; i >= 1; i--) {
				if (board[i][j] == '.' && start.r == -1) {
					start.r = i;
					start.c = j;
				}
				if (board[i][j] == '.' && board[i - 1][j] != '.' && start.r != -1) {
					board[start.r][start.c] = board[i - 1][j];
					board[i - 1][j] = '.';
					i = start.r;
					start.r = -1;
					start.c = -1;
				}
			}
		}
	}

	public static class Point {
		int r, c;
		char value;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
