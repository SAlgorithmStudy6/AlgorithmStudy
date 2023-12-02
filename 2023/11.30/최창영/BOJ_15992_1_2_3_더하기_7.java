package BOJ_15992;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15992_1_2_3_더하기_7 {

    // https://www.acmicpc.net/problem/15992
    // input
    private static BufferedReader br;

    // variables
    private static final int MOD = 1_000_000_009;
    private static int N, M;
    private static int[][] memo = new int[1001][1001];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("C:\\Users\\bigyo\\Desktop\\알고리즘\\JavaAlgorithm\\src\\BOJ_15992\\res.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        for (int[] t : memo) {
            Arrays.fill(t, -1);
        }

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            input();
            bw.write(solve());
        }

        bw.close();
    } // End of main

    private static String solve() {
        StringBuilder sb = new StringBuilder();

        sb.append(topDown(N, M, 3)).append('\n');
        return sb.toString();
    } // End of solve()

    private static int topDown(int n, int m, int currentNumber) {
        if (n == 0 && m == 0) return 1;
        if (n < 0 || m < 0 || currentNumber <= 0) return 0;

        if (memo[n][m] != -1) return memo[n][m];

        memo[n][m] = (topDown(n - currentNumber, m - 1, 3) + topDown(n, m, currentNumber - 1)) % MOD;

        return memo[n][m];
    } // End of topDown()

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    } // End of input()
} //  End of Main class
