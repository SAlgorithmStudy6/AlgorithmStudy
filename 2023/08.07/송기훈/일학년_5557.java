package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 일학년_5557 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            numbers[i] = temp;
        }

        // 상근이는 0 이상 20 이하의 수만 안다
        // long으로 해야 안 터짐
        // y단계 연산 시 x값의 수
        long[][] dp = new long[N][21];

        dp[1][numbers[1]] = 1;

        for (int y = 2; y < N; y++) {
            for (int x = 0; x <= 20; x++) {
                // 이전 단계에 경우의 수가 있다면?
                if (dp[y - 1][x] != 0) {
                    int plus = x + numbers[y];
                    int minus = x - numbers[y];

                    if (plus >= 0 && plus <= 20) {
                        dp[y][plus] += dp[y - 1][x];
                    }

                    if (minus >= 0 && minus <= 20) {
                        dp[y][minus] += dp[y - 1][x];
                    }
                }
            }
        }

        System.out.println(dp[N-1][numbers[N]]);

    }
}
