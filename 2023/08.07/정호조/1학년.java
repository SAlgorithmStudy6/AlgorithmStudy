import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 일학년_5557 {
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        dp = new long[N][21];
        int[] num = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        dp[0][num[0]] = 1;

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dp[i][j] != 0) {
                    int dif = j - num[i + 1];
                    int sum = j + num[i + 1];

                    if (0 <= dif && dif <= 20) dp[i + 1][dif] += dp[i][j];
                    if (0 <= sum && sum <= 20) dp[i + 1][sum] += dp[i][j];
                }
            }
        }

//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(Arrays.stream(dp[i]).toArray()));
//        }

        System.out.println(dp[N - 2][num[N - 1]]);
    }
}
