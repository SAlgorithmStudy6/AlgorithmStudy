package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RGB거리2 {

    static int N, result;
    static int[][] dp, costs;

    static final int RED = 0;
    static final int GREEN = 1;
    static final int BLUE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][3];
        costs = new int[N][3];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int red = Integer.parseInt(st.nextToken());
            int green = Integer.parseInt(st.nextToken());
            int blue = Integer.parseInt(st.nextToken());

            costs[i][RED] = red;
            costs[i][GREEN] = green;
            costs[i][BLUE] = blue;
        }

        result = Integer.MAX_VALUE;
        reset();
        for (int i = 0; i < 3; i++) {
            paint(i);
            reset();
        }
        System.out.println(result);
    }

    static void reset() {
        for (int d[] : dp) {
            Arrays.fill(d, 10000000);
        }
    }

    static void paint(int index) {

        // 첫번째 색을 고정
        dp[0][index] = costs[0][index];
        for (int i = 0; i < 3; i++) {
            if (i == index) continue;
            dp[1][i] = dp[0][index] + costs[1][i];
        }

        // RGB 거리 참고
        for (int i = 2; i < N; i++) {
            dp[i][RED] = Math.min(dp[i - 1][GREEN], dp[i - 1][BLUE]) + costs[i][RED];
            dp[i][GREEN] = Math.min(dp[i - 1][RED], dp[i - 1][BLUE]) + costs[i][GREEN];
            dp[i][BLUE] = Math.min(dp[i - 1][RED], dp[i - 1][GREEN]) + costs[i][BLUE];
        }

        // 첫번째 색 제외하고 결정
        for (int i = 0; i < 3; i++) {
            if (i == index) continue;
            result = Math.min(result, dp[N - 1][i]);
        }

    }

}
