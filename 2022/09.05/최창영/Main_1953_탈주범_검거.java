import java.util.*;
import java.io.*;


public class Main_1953_탈주범_검거 {
    static int N, M, R, C, L;
    static int[][] map;
    static int[] dirX = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dirY = {0, 0, -1, 1};
    static int result;

    static class Coordiantes {
        int x;
        int y;
        int time;

        public Coordiantes(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    } // End of Coordinates class

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/1953.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append('#').append(t).append(' ');

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            result = 0;
            map = new int[N][M];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            BFS(R, C);

            sb.append(result).append('\n');
        }

        bw.write(sb.toString());
        bw.close();
    } // End of main

    private static void BFS(int x, int y) {
        Queue<Coordiantes> que = new LinkedList<>();
        int[][] isVisited2 = new int[N][M];

        isVisited2[x][y] = 1;
        que.offer(new Coordiantes(x, y, 1));
        int nowX;
        int nowY;
        int time;
        int pipe;

        while (!que.isEmpty()) {
            Coordiantes cor = que.poll();
            pipe = map[cor.x][cor.y];
            int loopStart = 0;
            int loopLimit = 4;
            int loopIncrease = 1;

            if (pipe == 2) {
                // 상 하
                loopLimit = 2;
            } else if (pipe == 3) {
                // 좌 우
                loopStart = 2;
            } else if (pipe == 4) {
                // 상, 우
                loopIncrease = 3;
            } else if (pipe == 5) {
                // 하, 우
                loopStart = 1;
                loopIncrease = 2;
            } else if (pipe == 6) {
                // 하, 좌
                loopStart = 1;
                loopLimit = 3;
            } else if (pipe == 7) {
                // 상,좌
                loopLimit = 3;
                loopIncrease = 2;
            }

            for (int i = loopStart; i < loopLimit; i = i + loopIncrease) {
                nowX = cor.x + dirX[i];
                nowY = cor.y + dirY[i];
                time = cor.time + 1;

                // 상 하 좌 우 방향

                if (rangeCheck(nowX, nowY) && isVisited2[nowX][nowY] == 0) {
                    if ((i == 0 || i == 1) && map[nowX][nowY] == 3) {
                        // 상 하 방향인데, 3번 파이프는 불가능
                        continue;
                    } else if ((i == 2 || i == 3) && map[nowX][nowY] == 2) {
                        // 좌 우 방향에서 , 2번 파이프는 불가능
                        continue;
                    } else if (i == 0 && (map[nowX][nowY] == 4 || map[nowX][nowY] == 7)) {
                        continue;
                    } else if (i == 1 && (map[nowX][nowY] == 5 || map[nowX][nowY] == 6)) {
                        continue;
                    } else if (i == 2 && (map[nowX][nowY] == 6 || map[nowX][nowY] == 7)) {
                        continue;
                    } else if (i == 3 && (map[nowX][nowY] == 4 || map[nowX][nowY] == 5)) {
                        continue;
                    }

                    if (map[nowX][nowY] != 0) {
                        if (L == time) {
                            isVisited2[nowX][nowY] = time;
                            continue;
                        }

                        isVisited2[nowX][nowY] = time;
                        que.offer(new Coordiantes(nowX, nowY, time));
                    }
                }

            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isVisited2[i][j] > 0 && isVisited2[i][j] <= L) {
                    result++;
                }
            }
        }

    } // End of BFS

    private static boolean rangeCheck(int nowX, int nowY) {
        return nowX >= 0 && nowX < N && nowY >= 0 && nowY < M;
    } // End of rangeCheck
} // End of Main class