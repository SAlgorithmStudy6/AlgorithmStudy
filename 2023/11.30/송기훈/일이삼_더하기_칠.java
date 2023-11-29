package baekjoon.silver.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 일이삼_더하기_칠 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        ArrayList<int[]> answer = new ArrayList<>();
        int nMax = -1;
        int mMax = -1;

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            nMax = Math.max(n, nMax);
            mMax = Math.max(m, mMax);

            answer.add(new int[] {n, m});

        }

        // m개로 n 만들기
        long[][] dp = new long[mMax+1][nMax+1];
        dp[1][1] = 1;
        dp[1][2] = 1;
        dp[1][3] = 1;
        long mod = 1000000009;
        // dp[m][n] = dp[m-1][n-1] + dp[m-1][n-2] + dp[m-1][n-3]
        for(int m = 2; m <= mMax; m++) {
            for (int n = 2; n <= nMax; n++) {
                dp[m][n] += dp[m-1][n-1];
                if (n-2 >= 0) {
                    dp[m][n] += dp[m-1][n-2];
                }
                if (n-3 >= 0) {
                    dp[m][n] += dp[m-1][n-3];
                }
                dp[m][n] %= mod;
            }
        }

        for (int i = 0; i < answer.size(); i++) {
            int[] ans = answer.get(i);
            System.out.println(dp[ans[1]][ans[0]]);
        }

    }
}
