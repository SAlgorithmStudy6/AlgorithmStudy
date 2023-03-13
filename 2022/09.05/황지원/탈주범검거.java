import java.io.*;
import java.util.*;

class P {
    int x;
    int y;

    P(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Swea_1953_탈주범검거 {

    // N : 세로크기, M : 가로크기, R : 맨홀뚜껑세로, C : 맨홀뚜껑가로, L : 주어진 시간
    static int N, M, R, C, L, map[][], ans = 0;
    static boolean visit[][];
    static int dx[] = { -1, 1, 0, 0 }; // 상 하 좌 우
    static int dy[] = { 0, 0, -1, 1 };
    static int dist[][];

    public static void main(String[] args) throws NumberFormatException, IOException {
//        System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            dist = new int[N][M];
            map = new int[N][M];
            visit = new boolean[N][M];
            ans = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }


            bfs(R, C); // 시작점을 넣어 bfs를 돌린다.

            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (dist[i][j] <= L) {
                        ans++;
                    }
                }
            }

            System.out.println("#" + tc  + " " + ans);

        }

    }

    private static void bfs(int x, int y) { // 시작점 값을 잡고

        Queue<P> q = new LinkedList<>();

        
        q.add(new P(x, y));// 시작점을 넣고
        visit[x][y] = true;  // 방문체크한다.
        dist[x][y] = 1; // 값은 1로 시작한다.

        while (!q.isEmpty()) {
            P p = q.poll(); 
            int nx = 0, ny = 0;

            if (map[p.x][p.y] == 1) {// 1 : 상하좌우
                for (int i = 0; i < 4; i++) {
                    if (i == 0) { // 상 처리
                        nx = p.x + dx[i]; // 다음 친구의 x 값
                        ny = p.y + dy[i]; // 다음 친구의 y 값

                        for (int j = 0; j < 4; j++) { // 다음 친구의 상하좌우
                            if (rangeCheck(nx, ny) && map[nx][ny] != 0 && !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }
                    else if (i == 1) { // 하 방향 처리
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }
                    else if (i == 2) { // 좌 방향 처리
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) { // + - ㄴ ┌
                                if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }
                    else if (i == 3) { // 우 방향
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) { //
                                if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }

                }
            } else if (map[p.x][p.y] == 2) {// 2 : 상하 |

                for (int i = 0; i < 4; i++) {
                    if (i == 0) { // | 의 위와 연결될 수 있는 친구 +, |, ┐ ,┌
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }

                    if (i == 1) { // | 의 아래(1)와 연결될 수 있는 친구 + │ ┘ └
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny)&& !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }
                }

            } else if (map[p.x][p.y] == 3) {// 3 : __좌우 __23

                for (int i = 0; i < 4; i++) {

                    if (i == 2) {
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }
                    if (i == 3) {
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }
                }

            } else if (map[p.x][p.y] == 4) {// 4 : 상23우 ㄴ

                for (int i = 0; i < 4; i++) {

                    if (i == 0) {
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }
                    if (i == 3) {
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }
                }

            } else if (map[p.x][p.y] == 5) { // 5 : 0하2우 r
                for (int i = 0; i < 4; i++) {

                    if (i == 1) { // 하
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }
                    if (i == 3) { // 우
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }
                }

            } else if (map[p.x][p.y] == 6) { // 6 : 하좌

                for (int i = 0; i < 4; i++) { // 상 하 좌 우 구분 해주는 친구

                    if (i == 1) { // 하 일때

                        // 해당하는 값들 다 큐에 넣어주는 친구
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }

                    }
                    if (i == 2) {

                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }
                }

            } else if (map[p.x][p.y] == 7) {// 7 : 상좌
                for (int i = 0; i < 4; i++) {

                    if (i == 0) {
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];

                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }
                    if (i == 2) {
                        nx = p.x + dx[i];
                        ny = p.y + dy[i];
                        for (int j = 0; j < 4; j++) {
                            if (rangeCheck(nx, ny) && !visit[nx][ny]) {
                                if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
                                    dist[nx][ny] = Math.min(dist[p.x][p.y] + 1, dist[nx][ny]);
                                    q.add(new P(nx, ny));
                                    visit[nx][ny] = true;
                                }
                            }
                        }
                    }
                }

            }

        }

    }

    private static boolean rangeCheck(int x, int y) {
        return (0 <= x && x < N && 0 <= y && y < M);
    }

}
