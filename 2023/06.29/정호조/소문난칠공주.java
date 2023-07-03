import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 조합으로 7개의 좌표를 먼저 뽑음
 * 7개가 서로 인접해있는지 체크하면서 S의 개수 count
 * S의 개수가 4개 이상이고 서로 인접해있으면 ans++
 */
public class 소문난칠공주_1941 {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[] pointR, pointC, com;
    static char[][] arr;
    static boolean[] comVis;
    static boolean[][] vis;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        pointR = new int[25];
        pointC = new int[25];
        com = new int[7];
        comVis = new boolean[25];
        arr = new char[5][5];
        ans = 0;

        for (int i = 0; i < 25; i++) {
            pointR[i] = i / 5;
            pointC[i] = i % 5;
        }

        for (int i = 0; i < 5; i++) {
            String temp = br.readLine();
            for (int j = 0; j < 5; j++) {
                arr[i][j] = temp.charAt(j);
            }
        }

        combination(0, 0);
        System.out.println(ans);
    }

    static void combination(int idx, int start) {
        if (idx == 7) {
            vis = new boolean[5][5];
            bfs();
            return;
        }

        for (int i = start; i < 25; i++) {
            if (!comVis[i]) {
                comVis[i] = true;
                com[idx] = i;
                combination(idx + 1, i + 1);
                comVis[i] = false;
            }
        }
    }

    static void bfs() {
        int cnt = 1;
        int countS = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(com[0]);

        while (!queue.isEmpty()) {

            int idx = queue.poll();
            int r = pointR[idx];
            int c = pointC[idx];
            vis[r][c] = true;

            if (arr[r][c] == 'S') {
                countS++;
            }

            for (int i = 0; i < 4; i++) {
                int rr = r + dr[i];
                int cc = c + dc[i];
                if (0 <= rr && rr < 5 && 0 <= cc && cc < 5 && !vis[rr][cc]) {
                    for (int j = 1; j < 7; j++) {
                        if(rr == pointR[com[j]] && cc == pointC[com[j]]){
                            vis[rr][cc] = true;
                            queue.add(com[j]);
                            cnt++;
                        }
                    }
                }
            }
        }

        if(cnt == 7 && countS >= 4){
            ans++;
        }
    }
}
