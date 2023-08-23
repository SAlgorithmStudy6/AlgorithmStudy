package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 벼락치기 {

    static int N, T;
    static int[] kList, sList;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        kList = new int[N + 1];
        sList = new int[N + 1];

        int totalCost = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            kList[i] = Integer.parseInt(st.nextToken());
            sList[i] = Integer.parseInt(st.nextToken());
            totalCost += kList[i];
        }

        int timeLimit = Math.min(T, totalCost);

        dp = new int[N + 1][timeLimit + 1];
        for (int i = 1; i <= N; i++) {
            int k = kList[i];
            int s = sList[i];

            for (int cost = 0; cost <= timeLimit; cost++) {
                if (cost >= k) {
                    dp[i][cost] = Math.max(dp[i - 1][cost], dp[i - 1][cost - k] + s);
                } else {
                    dp[i][cost] = dp[i - 1][cost];
                }
            }
        }
        
        System.out.println(dp[N][timeLimit]);

    }
}