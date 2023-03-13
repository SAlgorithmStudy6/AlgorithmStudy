import java.util.*;
import java.io.*;

class P {
    int x;
    int y;

    P(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int N, M, ans;
    static int[][] home;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        home = new int[N+1][N+1];

        // 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                home[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dfs 탐색 시작
        DFS(1, 2, 0);
        sb.append(ans);

        bw.write(sb.toString());
        bw.close();
    }

    static void DFS(int x, int y, int direct) {
        if (x == N && y == N) {
            ans++;
            return;
        }
        if (direct == 0) { // 오른쪽 = 오른쪽 + (오른쪽, 대각선)
            if (y + 1 <= N && home[x][y + 1] == 0) {
                DFS(x, y + 1, 0);
            }

        } else if (direct == 1) { // 아래쪽 = 아래쪽 + (아래쪽,대각선)
            if (x + 1 <= N && home[x + 1][y] == 0) {
                DFS(x + 1, y, 1);
            }
        } else if (direct == 2) { // 대각선 = 대각선 + (오른쪽, 아래쪽, 대각선)
            if (y + 1 <= N && home[x][y + 1] == 0) { // 오른쪽
                DFS(x, y + 1, 0);
            }
            if (x + 1 <= N && home[x + 1][y] == 0) { // 아래쪽
                DFS(x + 1, y, 1);
            }
        }
        if (y + 1 <= N && x + 1 <= N && home[x + 1][y] == 0 && home[x][y + 1] == 0 && home[x + 1][y + 1] == 0) {
            DFS(x + 1, y + 1, 2);
        }

    }
}
