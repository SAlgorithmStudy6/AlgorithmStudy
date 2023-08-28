

import java.io.*;
import java.util.*;


class Main{


    static int N;
    static int[][] nums;
    static int[][][] dp;
    static int max,min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        nums = new int[N][3];
        dp = new int[N][3][2];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++){
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][0][0] = nums[0][0];
        dp[0][1][0] = nums[0][1];
        dp[0][2][0] = nums[0][2];
        dp[0][0][1] = nums[0][0];
        dp[0][1][1] = nums[0][1];
        dp[0][2][1] = nums[0][2];
        for(int i=1;i<N;i++){
            //최대
            dp[i][0][0] = Math.max(dp[i-1][0][0],dp[i-1][1][0]) + nums[i][0];
            dp[i][1][0] = Math.max(dp[i-1][0][0],Math.max(dp[i-1][1][0],dp[i-1][2][0]))+nums[i][1];
            dp[i][2][0] = Math.max(dp[i-1][1][0],dp[i-1][2][0]) + nums[i][2];
            //최소
            dp[i][0][1] = Math.min(dp[i-1][0][1],dp[i-1][1][1]) + nums[i][0];
            dp[i][1][1] = Math.min(dp[i-1][0][1],Math.min(dp[i-1][1][1],dp[i-1][2][1]))+nums[i][1];
            dp[i][2][1] = Math.min(dp[i-1][1][1],dp[i-1][2][1]) + nums[i][2];
        }
        max = Math.max(dp[N-1][0][0],Math.max(dp[N-1][1][0],dp[N-1][2][0]));
        min = Math.min(dp[N-1][0][1],Math.min(dp[N-1][1][1],dp[N-1][2][1]));
        bw.write(max+" "+min);
        bw.close();
        br.close();
    }



}
