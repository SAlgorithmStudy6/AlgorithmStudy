package baekjoon.gold.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 서로_다른_소수의_합 {

    static int MAX = 1120;
    static boolean[] isPrime = new boolean[MAX + 1];
    static ArrayList<Integer> primeNumbers = new ArrayList<>();
    static int[][] dp = new int[MAX + 1][15];

    public static void main(String[] args) throws IOException {
        getPrimeNumbers();
        dp[0][0] = 1;
        for (int num : primeNumbers) {
            for (int j = MAX; j >= num; j--) {
                for (int k = 1; k < 15; k++) {
                    dp[j][k] += dp[j - num][k - 1];
                }
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            System.out.println(dp[n][k]);
        }

    }

    public static void getPrimeNumbers() {
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        int sqrtMax = (int) Math.sqrt(MAX);
        for (int i = 2; i <= sqrtMax; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 0; i <= MAX; i++) {
            if (isPrime[i]) {
                primeNumbers.add(i);
            }
        }

    }

}
