import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 조건 1) 인접한 집은 색이 달라야함, 조건 2) 1번과 N번 집은 색이 달라야함
 * 1번집이 빨, 초, 파를 골랐을 경우를 나누면서 시작
 * 1번이 빨간색을 골랐다면 N번은 빨간색을 제외한 초, 파 중 골라야함. 즉 dp[N][color]의 값이 조건 2까지 부합하는 경우의 최소 비용
 * 크기가 3인 cases[] 배열에 1번 집이 각 색깔을 골랐을 때의 최소비용 저장 후 cases[] 배열의 최솟값을 구하면 정답
 * */
public class RGB거리2_17404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] home = new int[N + 1][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                home[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] cases = new int[3];
        for (int color = 0; color < 3; color++) {
            int[][] dp = new int[N+1][3];
            
            for(int i=0; i<N; i++){
                for(int j=0; j<3; j++){
                    dp[i][j] = home[i][j];
                }
            }

            for (int i = 1; i <= N; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == 1) {
                        if (j != color) {
                            dp[i][j] += dp[i - 1][color];
                        } else {
                            dp[i][color] = Integer.MAX_VALUE;
                        }
                    } else {
                        dp[i][j] += Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
                    }
                }
            }

            cases[color] = dp[N][color];
        }

        int ans = Integer.MAX_VALUE;
        for(int i=0; i<3; i++){
            ans = Math.min(cases[i], ans);
        }

        System.out.println(ans);
    }
}
