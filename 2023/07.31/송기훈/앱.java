package baekjoon.gold.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 앱 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] memories, costs;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());   // 확보해야 하는 메모리

        StringTokenizer memoryTokenizer = new StringTokenizer(br.readLine());
        StringTokenizer costTokenizer = new StringTokenizer(br.readLine());

        int totalCost = 0;
        memories = new int[N+1];
        costs = new int[N+1];
        for (int i = 1; i <= N; i++) {
            int memory = Integer.parseInt(memoryTokenizer.nextToken());
            int cost = Integer.parseInt(costTokenizer.nextToken());

            totalCost += cost;
            memories[i] = memory;
            costs[i] = cost;
        }

        int[][] dp = new int[N + 1][totalCost + 1];
        for (int i = 1; i <= N; i++) {
            int memory = memories[i];
            int cost = costs[i];

            for (int now = 0; now <= totalCost; now++) {
                if (now >= cost) {
                    dp[i][now] = Math.max(dp[i - 1][now], dp[i - 1][now - cost] + memory);
                } else {
                    dp[i][now] = dp[i - 1][now];
                }
            }
        }

        for (int i = 0; i <= totalCost; i++) {
            if (dp[N][i] >= M) {
                System.out.println(i);
                break;
            }
        }

    }

}