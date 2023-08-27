package baekjoon.gold.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 내리막길 {

    static int[] dY = {1, 0, -1, 0};
    static int[] dX = {0, 1, 0, -1};
    static int M, N;
    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        matrix = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(dp[0][0]);

    }

    static int dfs(int y, int x) {

        if (y == M-1 && x == N-1) return 1;

        dp[y][x] = 0;
        for (int i = 0; i < 4; i++) {
            int nY = y + dY[i];
            int nX = x + dX[i];

            if (nY < 0 || nX < 0 || nY >= M || nX >= N) continue;

            if (matrix[nY][nX] < matrix[y][x]) {
                // 이미 방문 했으면 더해주기
                if (dp[nY][nX] != -1) {
                    dp[y][x] += dp[nY][nX];
                } else {
                    dp[y][x] += dfs(nY, nX);
                }
            }
        }

        return dp[y][x];
    }
}