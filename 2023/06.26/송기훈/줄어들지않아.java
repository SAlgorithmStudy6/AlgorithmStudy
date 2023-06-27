package baekjoon.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 줄어들지않아 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        int maxValue = Integer.MIN_VALUE;
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            int value = arr[i] = Integer.parseInt(br.readLine());
            maxValue = Math.max(value, maxValue);
        }

        // j로 시작하는 i 자리 수
        long[][] dp = new long[maxValue + 1][10];

        // index로 시작하는 1 자리 수는 1개 뿐
        for (int index = 0; index < 10; index++) {
            dp[1][index] = 1;
        }

        for (int i = 2; i < maxValue + 1; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = j; k <= 9; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        for (int number : arr) {
            long answer = 0;
            for (int i = 0; i < 10; i++) {
                answer += dp[number][i];
            }
            System.out.println(answer);
        }

    }
}
