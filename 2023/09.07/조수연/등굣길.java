import java.util.*;

class Solution {
    int[][] dp;
    int[] dx = {0, 1};
    int[] dy = {1, 0};
    final int MOD = 1000000007;

    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < puddles.length; i++) {
            dp[puddles[i][1] - 1][puddles[i][0] - 1] = -2;
        }

        return dfs(0, 0, m, n);
    }

    public int dfs(int x, int y, int m, int n) {
        if (x == m - 1 && y == n - 1) return 1; // 도착하면 경로 +1

        if (dp[y][x] != -1) return dp[y][x]; // 해당 위치에 경로의 갯수가 있으면 불러오기

        dp[y][x] = 0;

        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < m && ny < n && dp[ny][nx] != -2) {
                dp[y][x] += dfs(nx, ny, m, n);
                dp[y][x] %= MOD;
            }
        }

        return dp[y][x];
    }
}