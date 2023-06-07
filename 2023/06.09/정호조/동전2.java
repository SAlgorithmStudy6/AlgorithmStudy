import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 동전2_2294 {
    static int n, k, ans;
    static int[] coin, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ans = 0;
        coin = new int[n];
        dp = new int[k + 1];

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        Arrays.fill(dp, Integer.MAX_VALUE -1);  // -1을 안해주면 밑에서 dp[j - coin[i]] + 1 값이 max value를 넘어갈 때 음수 값이 나옴
        dp[0] = 0;  //dp[i]가 i 가치를 구하기 위한 동전의 개수일 때, i원의 동전으로 i값을 구하는 경우의 수가 1이 되려면 dp[0]을 항상 0 으로 둬야함

        //LIS 의 Bottom-Up 방식
        for (int i = 0; i < n; i++) {
            for (int j = coin[i]; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
            }
        }

        if (dp[k] == Integer.MAX_VALUE - 1) {
            ans = -1;
        } else {
            ans = dp[k];
        }

        System.out.println(ans);
    }
}
