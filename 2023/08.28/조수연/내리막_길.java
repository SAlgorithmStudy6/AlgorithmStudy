import java.io.*;
import java.util.*;

public class Main {

    static int[][] dp;
    static int[][] map;
    static int n;
    static int m;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n + 1][m + 1];
        map = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], 1, m+1, -1);
        }

        bw.write(String.valueOf(dfs(1, 1)));

        bw.flush();
        bw.close();
    }

    public static int dfs(int x, int y) {

        // 경로를 찾은 경우
        if (x == m && y == n) return 1;

        // 해당 좌표에 경로가 있다면 경로의 갯수를 반환
        if (dp[y][x] != -1) return dp[y][x];

        dp[y][x] = 0; // 탐색 시작 지점 초기화

        for (int i = 0; i < 4; i++) {
            int nx = dir[i][1] + x;
            int ny = dir[i][0] + y;

            if (nx < 1 || nx > m || ny < 1 || ny > n) continue;

            if (map[y][x] > map[ny][nx]) { // 현재 좌표 경로의 갯수 += 다음 좌표에 경로의 갯수
                dp[y][x] += dfs(nx, ny);
            }
        }
        return dp[y][x];
    }
}