package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 축구_DP {
    // RGB거리 참고

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int N = 19;

        double[][][] dp = new double[N][N][N];

        double goalA = A / 100.0;
        double noGoalA = 1.0 - goalA;
        double goalB = B / 100.0;
        double noGoalB = 1.0 - goalB;

        dp[0][0][0] = 1.0;

        for (int i = 1; i < N; i++) {
            for (int a = 0; a <= i; a++) {
                for (int b = 0; b <= i; b++) {

                    if (a > 0) {
                        dp[i][a][b] += dp[i - 1][a - 1][b] * goalA * noGoalB;
                    }

                    if (b > 0) {
                        dp[i][a][b] += dp[i - 1][a][b - 1] * goalB * noGoalA;
                    }

                    if (a > 0 && b > 0) {
                        dp[i][a][b] += dp[i - 1][a - 1][b - 1] * goalA * goalB;
                    }

                    dp[i][a][b] += dp[i - 1][a][b] * noGoalA * noGoalB;

                }

            }

        }

        double p = 0.0;
        for (int a = 0; a < N; a++) {
            for (int b = 0; b < N; b++) {
                if (isPrime(a) || isPrime(b)) {
                    p += dp[N - 1][a][b];
                }
            }
        }

        System.out.println(p);

    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


}
