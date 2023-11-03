import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//에라토스테네스의 채 + dp 문제
public class 서로다른소수의합 {
    static int MAX = 1120;
    static boolean[] isPrime;
    static ArrayList<Integer> primeList;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine());
        isPrime = new boolean[MAX + 1];
        primeList = new ArrayList<>();
        dp = new int[MAX + 1][15];

        getPrime();

        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            System.out.println(dp[n][k]);
        }
    }

    static void getPrime() {
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; (i * i) <= MAX; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 0; i < isPrime.length; i++) {
            if (isPrime[i]) primeList.add(i);
        }

        dp[0][0] = 1;
        for (int prime : primeList) {
            for (int j = MAX; j >= prime; j--) {
                for (int k = 1; k < 15; k++) {
                    dp[j][k] += dp[j - prime][k - 1];
                }
            }
        }
    }
}
