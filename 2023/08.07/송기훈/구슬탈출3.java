package baekjoon.gold.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;

public class 구슬탈출3 {

    static class Point {
        int rY, rX;
        int bY, bX;
        int depth;
        String dir;

        public Point(int rY, int rX, int bY, int bX, int depth, String dir) {
            this.rY = rY;
            this.rX = rX;
            this.bY = bY;
            this.bX = bX;
            this.depth = depth;
            this.dir = dir;
        }

    }

    static int N, M;
    static int rY, rX;
    static int bY, bX;
    static int hY, hX;
    static char[][] matrix;

    // 하 우 상 좌
    static int[] dY = {1, 0, -1, 0};
    static int[] dX = {0, 1, 0, -1};
    static String[] dir = {"D", "R", "U", "L"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        matrix = new char[N][M];
        for (int i = 0; i < N; i++) {
            String inputString = br.readLine();
            for (int j = 0; j < M; j++) {
                char temp = inputString.charAt(j);

                if (temp == 'R') {
                    rY = i;
                    rX = j;
                } else if (temp == 'B') {
                    bY = i;
                    bX = j;
                } else if (temp == 'O') {
                    hY = i;
                    hX = j;
                }

                matrix[i][j] = temp;
            }
        }

        bfs(new Point(rY, rX, bY, bX, 0, ""));

    }

    static void bfs(Point point) {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(point);
        boolean[][][][] visited = new boolean[N][M][N][M];
        visited[point.rY][point.rX][point.bY][point.bX] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            // 10 넘어가면 -1 출력
            if (p.depth > 10) {
                System.out.println(-1);
                return;
            }

            // 파란 구슬이 빠지면 안됨
            if (matrix[p.bY][p.bX] == 'O') {
                continue;
            }

            // 빨간 구슬이 O, 파란 구슬이 O가 아니면 성공
            if (matrix[p.rY][p.rX] == 'O' && matrix[p.bY][p.bX] != 'O') {
                System.out.println(p.depth);
                System.out.println(p.dir);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int rY = p.rY;
                int rX = p.rX;

                // 빨간 구슬을 한 쪽 방향으로 이동
                while (true) {
                    if (rY + dY[i] >= 0 && rX + dX[i] >= 0) {
                        rY += dY[i];
                        rX += dX[i];

                        // 벽에 닿았을 경우 한 칸 빼주자
                        if (matrix[rY][rX] == '#') {
                            rY -= dY[i];
                            rX -= dX[i];
                            break;
                        }

                        // O에 도착
                        if (matrix[rY][rX] == 'O') {
                            break;
                        }
                    }
                }

                int bY = p.bY;
                int bX = p.bX;

                // 파란 구슬을 한 쪽 방향으로 이동
                while (true) {
                    if (bY + dY[i] >= 0 && bX + dX[i] >= 0) {
                        bY += dY[i];
                        bX += dX[i];

                        // 벽에 닿았을 경우 한 칸 빼주자
                        if (matrix[bY][bX] == '#') {
                            bY -= dY[i];
                            bX -= dX[i];
                            break;
                        }

                        // O에 도착
                        if (matrix[bY][bX] == 'O') {
                            break;
                        }
                    }
                }

                // 빨간 구슬, 파란 구슬이 같은 곳이면?
                // #...RB# --L--> #RB...#
                if (rY == bY && rX == bX && matrix[rY][rX] != 'O') {
                    int rDiff = Math.abs(rY - p.rY) + Math.abs(rX - p.rX);
                    int bDiff = Math.abs(bY - p.bY) + Math.abs(bX - p.bX);

                    // 벽으로부터 더 먼 것을 한 칸 빼준다
                    if (rDiff > bDiff) {
                        rY -= dY[i];
                        rX -= dX[i];
                    } else {
                        bY -= dY[i];
                        bX -= dX[i];
                    }
                }

                if (!visited[rY][rX][bY][bX]) {
                    visited[rY][rX][bY][bX] = true;
                    queue.offer(new Point(rY, rX, bY, bX, p.depth + 1, p.dir + dir[i]));
                }
            }
        }
        System.out.println(-1);
    }

}
