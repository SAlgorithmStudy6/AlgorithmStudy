import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int chap[][] = new int[n+1][2];//0 time, 1 score
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<2; j++) {
                chap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int dp[][] = new int[n+1][t+1];
        for(int i=1; i<=n; i++) {
            for(int j=0; j<=t; j++) {
                if(chap[i][0] <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - chap[i][0]] + chap[i][1]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        System.out.println(dp[n][t]);
    }

}
