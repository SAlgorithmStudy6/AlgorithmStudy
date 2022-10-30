class Solution {
    static int dp[];
    public long solution(int n) {
        dp = new int [2001]; // 왜 n + 1하면 터질까요?
        dp[1] = 1;
        dp[2] = 2;
    
        for(int i=3; i<=n; i++){
            dp[i] = (dp[i-2] + dp[i-1]) % 1234567;
        }
        return dp[n];
    }
}
