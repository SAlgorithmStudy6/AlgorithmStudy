package baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RGB거리 {

    final static int RED = 0;
    final static int GREEN = 1;
    final static int BLUE = 2;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int red = Integer.parseInt(st.nextToken());
            int green = Integer.parseInt(st.nextToken());
            int blue = Integer.parseInt(st.nextToken());

            dp[i + 1][RED] = Math.min(dp[i][GREEN], dp[i][BLUE]) + red;
            dp[i + 1][GREEN] = Math.min(dp[i][RED], dp[i][BLUE]) + green;
            dp[i + 1][BLUE] = Math.min(dp[i][RED], dp[i][GREEN]) + blue;
        }

        System.out.println(
                Math.min(
                        Math.min(dp[N][RED], dp[N][GREEN]),
                        dp[N][BLUE]
                )
        );

    }

}