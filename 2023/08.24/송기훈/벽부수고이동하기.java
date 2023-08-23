package baekjoon.gold.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 벽부수고이동하기 {

    static int N, M;
    static int[][] matrix;
    static boolean[][][] visited;

    static int[] dY = {1, 0, -1, 0};
    static int[] dX = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        matrix = new int[N][M];
        visited = new boolean[2][N][M];

        for (int i = 0; i < N; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(String.valueOf(c[j]));
            }
        }

        bfs();

    }

    static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 1, false));

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            if (p.y == N-1 && p.x == M-1) {
                System.out.println(p.dist);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nY = p.y + dY[i];
                int nX = p.x + dX[i];

                if (nY < 0 || nX < 0 || nY >= N || nX >= M) continue;

                // 벽
                if (matrix[nY][nX] == 1) {
                    if (!p.crash) {
                        queue.add(new Point(nY, nX, p.dist+1, true));
                        visited[1][nY][nX] = true;
                    }
                }
                // 길
                else {
                    // 아직 안 부숨
                    if (!p.crash && !visited[0][nY][nX]) {
                        queue.add(new Point(nY, nX, p.dist+1, false));
                        visited[0][nY][nX] = true;
                    }
                    // 이미 벽을 부숨
                    else if (p.crash && !visited[1][nY][nX]) {
                        queue.add(new Point(nY, nX, p.dist+1, true));
                        visited[1][nY][nX] = true;
                    }
                }
            }
        }

        System.out.println(-1);
    }

    static class Point {
        int y;
        int x;
        int dist;
        boolean crash;

        public Point(int y, int x, int dist, boolean crash) {
            this.y = y;
            this.x = x;
            this.dist = dist;
            this.crash = crash;
        }
    }

}