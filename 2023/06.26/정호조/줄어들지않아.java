import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 줄어들지않아_2688 {
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        dp = new long[65][10];  //타입 주의
        Arrays.fill(dp[1], 1);

        int maxTc = 1;
        for (int i = 0; i < tc; i++) {
            int n = Integer.parseInt(br.readLine());

            if (maxTc < n) {
                for (int j = maxTc + 1; j <= n; j++) {
                    for (int k = 0; k <= 9; k++) {
                        for (int l = k; l <= 9; l++) {
                            dp[j][k] += dp[j - 1][l];
                        }
                    }
                }
                maxTc = n;
            }

            long ans = 0;
            for (int j = 0; j <= 9; j++) {
                ans += dp[n][j];
            }

            System.out.println(ans);
        }
    }
}
