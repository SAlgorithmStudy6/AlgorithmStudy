import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14728
// 단원을 공부하는데에 필요한 시간을 인덱스로 두고 해결

public class 벼락치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        Problem[] problems = new Problem[N];
        int[][] dp = new int[N][T + 1];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            problems[i] = new Problem(score, time);
        }
        for(int i = 0; i < N; i++){
            for(int j = 1; j <= T; j++){
                if(i == 0){
                    if(j >= problems[i].time){
                        dp[i][j] = problems[i].score;
                    }
                    continue;
                }
                if(j - problems[i].time >= 0){
                    dp[i][j] = Math.max(dp[i-1][j],Math.max(dp[i-1][j - problems[i].time] + problems[i].score, dp[i][j - 1]));
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[N-1][T]);
    }
}
class Problem{
    int score;
    int time;

    public Problem(int score, int time) {
        this.score = score;
        this.time = time;
    }
}
