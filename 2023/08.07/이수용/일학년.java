import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/5557

public class 일학년 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N];
        long[][] dp = new long[N - 1][21];
        for(int i = 0; i < N; i++){
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        dp[0][numbers[0]] = 1;
        for(int i = 1; i < N - 1; i++){
            for(int j = 0; j < 21; j++){
                if(dp[i-1][j] > 0){
                    int plus = j + numbers[i];
                    int minus = j - numbers[i];
                    if(0 <= plus && plus <= 20)
                        dp[i][j + numbers[i]] += dp[i - 1][j];
                    if(0 <= minus && minus <= 20)
                        dp[i][j - numbers[i]] += dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[N - 2][numbers[N - 1]]);
    }
}
