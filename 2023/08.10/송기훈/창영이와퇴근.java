package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 창영이와퇴근 {

    static class Node implements Comparable<Node> {
        int y, x;
        long height;

        public Node(int y, int x, long height) {
            this.y = y;
            this.x = x;
            this.height = height;
        }

        @Override
        public int compareTo(Node node) {
            return Long.compare(this.height, node.height);
        }
    }

    static int N;
    static long maxHeight;
    static long[][] matrix;
    static long[][] heights;

    static int[] dY = {1, 0, -1, 0};
    static int[] dX = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        matrix = new long[N][N];
        heights = new long[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Long.parseLong(st.nextToken());
            }
        }

        // 모든 heights를 MAX_VALUE로
        for (long[] h : heights) {
            Arrays.fill(h, Long.MAX_VALUE);
        }

        maxHeight = 0;
        bfs();
        System.out.println(maxHeight);
    }

    static void bfs() {
        // 1 x 1이면 갈 곳이 없으므로 끝
        if (N == 1) {
            return;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(0, 1, Math.abs(matrix[0][0] - matrix[0][1])));
        queue.offer(new Node(1, 0, Math.abs(matrix[0][0] - matrix[1][0])));
        maxHeight = 0;
        heights[0][0] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            // 이미 간 곳이라 패스
            if (heights[now.y][now.x] != Long.MAX_VALUE) continue;

            heights[now.y][now.x] = now.height;
            maxHeight = Math.max(maxHeight, now.height);

            // 도착
            if (now.y == N - 1 && now.x == N - 1) break;

            for (int i = 0; i < 4; i++) {
                int nY = now.y + dY[i];
                int nX = now.x + dX[i];

                if (nY < 0 || nX < 0 || nY >= N || nX >= N) continue;
                if (heights[nY][nX] != Long.MAX_VALUE) continue;

                queue.offer(new Node(nY, nX, Math.abs(matrix[now.y][now.x] - matrix[nY][nX])));
            }
        }

    }
}
