package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 동전_바꿔주기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, K;
    static int[][] coins, dp;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        coins = new int[K+1][2];  // i번째 j개
        dp = new int[K+1][T+1];

        for (int i = 1; i <= K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coins[i][0] = Integer.parseInt(st.nextToken());
            coins[i][1] = Integer.parseInt(st.nextToken());
        }

        dp[0][0] = 1;

        for (int k = 1; k <= K; k++) {
            int coin = coins[k][0];
            int count = coins[k][1];

            for (int i = 0; i <= T; i++) {
                for (int c = 0; c <= count; c++) {
                    if (i - coin * c >= 0 && i - coin * c <= T) {
                        dp[k][i] += dp[k - 1][i - coin * c];
                    }
                }
            }
        }

        System.out.println(dp[K][T]);

    }

}
