import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class 인증된쉬운게임 {
    // 자연수 K
    public static int K;
    // dp 배열
    // 모니터의 값이 x일때부터 게임을 시작할때 Kali가 이기면 True, 지면 False
    public static boolean [] dp;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        K = Integer.parseInt(br.readLine());
        dp = new boolean[K+1];

        for (int cur = K; cur >= 1; cur--) {
            // 시간 절약을 위해 y*y<=x 인 범위만큼만 약수 확인
            for (int divisor = 1; divisor * divisor <= cur; divisor++) {

                if (cur % divisor == 0) {
                    // cur에 divisor를 더했을 때 범위를 벗어나지 않고 cur에 divisor를 더한 값이 false 일 때(링고가 지는 경우) true
                    // 범위를 벗어날 경우 자동적으로 지는 것이기 때문에 범위 벗어나면 안됨

                    // Kali의 다음 차례는 Ringo
                    // Kali가 이기는 최선의 전략은 Ringo가 지는 경우
                    // 따라서 cur에 divisor를 더해 Ringo의 차례를 갔을 때 dp의 값이 False라면 dp[cur]=true
                    if (cur + divisor <= K && !dp[cur + divisor]) {
                        dp[cur]=true;
                        break;
                    }
                    // divisor*divisor<=cur인 경우만큼만 구했기에 cur/divisor도 추가적으로 구해줘야 함.
                    if (cur + cur / divisor <= K && !dp[cur + cur / divisor]) {
                        dp[cur]=true;
                        break;
                    }
                }

            }
        }

        // 초기 모니터의 숫자가 1이기 때문에 dp[1]의 값을 비교
        // Kali가 1부터 시작해서 이긴다면 (dp[1]=True)라면 Kali 출력
        if (dp[1]) {
            bw.write("Kali");
        } else {
            bw.write("Ringo");
        }
        bw.flush();
    }
}
