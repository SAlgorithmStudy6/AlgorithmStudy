import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**dp문제
 * 1) 점화식 : dp[n][m] = dp[n-1][m-1] + dp[n-2][m-1] + dp[n-3][m-1]
 * 2) 예를 들어 3개의 숫자로 6을 만들어야한다고 했을 때(dp[6][3])
 * 2-1) 첫 번째 숫자가 1일 경우 -> 나머지 두 개의 숫자로 5를 만드는 경우 == dp[5][2], 2일 경우 -> 나머지 두 개의 숫자로 4를 만드는 경우 == dp[4][2], 3일 경우 -> 나머지 두 개의 숫자로 3을 만드는 경우 == dp[3][2]
 * 3) 따라서 먼저 1001 x 1001 배열을 만들어 놓은 후 tc에 대한 값을 출력
 * */
public class 일이삼더하기7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int R = 1_000_000_009;

        int[][] dp = new int[1001][1001];

        dp[1][1] = dp[2][1] = dp[3][1] = dp[2][2] = dp[3][3] = 1;
        dp[3][2] = 2;

        for (int i = 4; i <= 1000; i++) {
            for (int j = 2; j <= 1000; j++) {
                for (int k = 1; k <= 3; k++) {
                    dp[i][j] = (dp[i][j] + dp[i - k][j - 1]) % R;
                }
            }
        }

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            System.out.println(dp[n][m]);
        }
    }
}
