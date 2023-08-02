import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 최소 비용으로 최대 메모리를 확보해야함
 */
public class 앱_7579 {
    static int N, M, ans;
    static int[] memory, cost;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][10001];
        memory = new int[N + 1];
        cost = new int[N + 1];
        ans = Integer.MAX_VALUE;

        int sum = 0;
        StringTokenizer stM = new StringTokenizer(br.readLine());
        StringTokenizer stC = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(stM.nextToken());
            cost[i] = Integer.parseInt(stC.nextToken());
            sum += cost[i];
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= sum; j++) {  //비용
                if (cost[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);
                }

                if (dp[i][j] >= M) ans = Math.min(ans, j);
            }
        }
        System.out.println(ans);
    }
}
