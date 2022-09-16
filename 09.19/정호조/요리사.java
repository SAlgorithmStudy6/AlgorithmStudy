import java.util.*;
import java.io.*;

public class 요리사_4012 {
	static int n, ans;
	static int[][] ingredient;
	static boolean[] vis;
	static int[] groupa, groupb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			ingredient = new int[n][n];
			groupa = new int[n / 2];
			groupb = new int[n / 2];
			vis = new boolean[n];
			ans = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					ingredient[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			combination(0, 0, n, n / 2);
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void combination(int index, int start, int n, int m) {
		if (index == m) {
			int idxa = 0;
			int idxb = 0;
			int suma = 0;
			int sumb = 0;
			for (int i = 0; i < n-1; i++) {
				for(int j=i+1; j<n; j++) {
					if(vis[i] == true && vis[j] == true) {
						suma += ingredient[i][j];
						suma += ingredient[j][i];
					} else if(vis[i] == false && vis[j] == false) {
						sumb += ingredient[i][j];
						sumb += ingredient[j][i];
					}
				}
			}

			ans = Math.min(ans, Math.abs(suma - sumb));
			return;
		}
		for (int i = start; i < n; i++) {
			if (vis[i] == false) {
				vis[i] = true;
				combination(index + 1, i + 1, n, m);
				vis[i] = false;
			}
		}
	}
}
