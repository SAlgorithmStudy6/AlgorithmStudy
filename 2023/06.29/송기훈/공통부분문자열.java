package baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 공통부분문자열 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String s1 = br.readLine();
        String s2 = br.readLine();

        int l1 = s1.length();
        int l2 = s2.length();
        int maxValue = 0;

        int[][] dp = new int[l1+1][l2+1];

        for (int i = 1; i < l1+1; i++) {
            for (int j = 1; j < l2+1; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    maxValue = Math.max(dp[i][j], maxValue);
                }
            }
        }

        System.out.println(maxValue);

    }
}
