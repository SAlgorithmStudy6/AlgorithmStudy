package BOJ_7579;

import java.io.*;
import java.util.StringTokenizer;

/*

    몇 개를 비활성화 홰서 M 바이트 이상의 메모리를 추가로 확보해야 한다.
    비활성화 했을 경우의 합을 최소화하여라.


 */

public class BOJ_7579_앱 {
    // input
    private static BufferedReader br;
    private static StringBuilder sb;

    // variables
    private static int N, M, ans;
    private static int[] aliveApps;
    private static int[] costs;

    private static Integer[][] memo = new Integer[101][10_001]; // [N+1][M+1]

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("C:\\Users\\bigyo\\Desktop\\Java Algorithm\\JavaAlgorithm\\src\\BOJ_7579\\res.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();

        input();

        solve();

        bw.write(sb.toString());
        bw.close();
    } // End of main

    private static void solve() {
        DFS(0, aliveApps[0], costs[0]);
        DFS(0, 0, 0);
        sb.append(ans);
    } // End of solve

    private static void DFS(int index, int volume, int cost) {
        System.out.println("DFS(" + index + ", " + volume + ", " + cost + ")");

        if (volume >= M) {
            ans = Math.min(ans, cost);
        }

        if (memo[index][cost] != null && memo[index][cost] >= volume) {
            return;
        }

        memo[index][cost] = volume;

        if (index + 1 < N) {
            DFS(index + 1, volume + aliveApps[index + 1], cost + costs[index + 1]);
            DFS(index + 1, volume, cost);
        }
    } // End of DFS

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;

        aliveApps = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            aliveApps[i] = Integer.parseInt(st.nextToken());
        }

        costs = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }
    } // End of input
} // End of Main class
