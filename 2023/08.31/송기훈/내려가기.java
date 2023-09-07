package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 내려가기 {

    static int N;
    static int[][] matrix;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        matrix = new int[N+1][3];
        dp = new int[2][N+1][3];
        for (int i = 1; i <= N; i++) {
            String[] inputs = br.readLine().split(" ");
            matrix[i][0] = Integer.parseInt(inputs[0]);
            matrix[i][1] = Integer.parseInt(inputs[1]);
            matrix[i][2] = Integer.parseInt(inputs[2]);

        }

        StringBuilder sb = new StringBuilder();
        if (N == 1) {
            sb.append(Math.max(Math.max(matrix[1][0], matrix[1][1]), matrix[1][2]));
            sb.append(" ");
            sb.append(Math.min(Math.min(matrix[1][0], matrix[1][1]), matrix[1][2]));
        } else {
            for (int i = 0; i < 3; i++) {
                dp[0][1][i] = matrix[1][i];
                dp[1][1][i] = matrix[1][i];
            }

            for (int i = 2; i <= N; i++) {
                int maxOneTwo = Math.max(dp[0][i-1][0], dp[0][i-1][1]);
                int maxTwoThree = Math.max(dp[0][i-1][1], dp[0][i-1][2]);
                int minOneTwo = Math.min(dp[1][i-1][0], dp[1][i-1][1]);
                int minTwoThree = Math.min(dp[1][i-1][1], dp[1][i-1][2]);

                dp[0][i][0] = maxOneTwo + matrix[i][0];
                dp[0][i][1] = Math.max(maxOneTwo, maxTwoThree) + matrix[i][1];
                dp[0][i][2] = maxTwoThree + matrix[i][2];

                dp[1][i][0] = minOneTwo + matrix[i][0];
                dp[1][i][1] = Math.min(minOneTwo, minTwoThree) + matrix[i][1];
                dp[1][i][2] = minTwoThree + matrix[i][2];
            }

            sb.append(Arrays.stream(dp[0][N]).max().getAsInt() + " " + Arrays.stream(dp[1][N]).min().getAsInt());
            System.out.println(sb);
        }

    }
}
