import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7579

public class 앱 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] memory = new int[N];
        int[] cost = new int[N];
        StringTokenizer stMemory = new StringTokenizer(br.readLine());
        StringTokenizer stCost = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(stMemory.nextToken());
            cost[i] = Integer.parseInt(stCost.nextToken());
        }

        // N(최대 100) * cost(최대 100)
        int[][] dp = new int[N][10001];
        int ans = 10001;
        for (int i = 0; i < N; i++) {
            int currentMemory = memory[i];
            int currentCost = cost[i];
            for (int j = 0; j < 10001; j++) {
                if (i == 0) {
                    if (j >= currentCost) {
                        dp[i][j] = currentMemory;
                    }
                } else {
                    if (j >= currentCost) dp[i][j] = Math.max(dp[i - 1][j - currentCost] + currentMemory, dp[i - 1][j]);
                    else dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= M) {
                    ans = Math.min(ans, j);
                    // 최소비용으로 M이상의 메모리만 확보하면 됨. 따라서 break
                    break;
                }

            }
        }
        System.out.println(ans);
    }
}