import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        long[][] dp = new long[65][10];

        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                for (int k = j; k <= 9; k++) {
                    dp[i][j] += dp[i-1][k];
                }
            }
        }

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            long sum = 0;

            for (int j = 0; j <= 9; j++) {
                sum += dp[n][j];
            }

            bw.write(sum+"\n");
        }

        bw.flush();
        bw.close();
    }
}