import java.util.*;
import java.awt.*;

class Test {
    public static void main(String[] args) {
        int[][] rectangle = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
        int characterX = 1, characterY = 3, itemX = 7, itemY = 8;
        new Solution().solution(rectangle, characterX, characterY, itemX, itemY);
    }
}

class Solution {
    static final int SIZE = 100;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        board = new int[SIZE + 1][SIZE + 1];
        visited = new boolean[SIZE + 1][SIZE + 1];

        // 테두리 선에 이동이 가능하도록 board 배열 갱신
        for (int i = 0; i < rectangle.length; i++) {
            for (int j = SIZE - 2 * rectangle[i][3]; j <= SIZE - 2 * rectangle[i][1]; j++) {
                for (int k = 2 * rectangle[i][0]; k <= 2 * rectangle[i][2]; k++) {
                    // 이미 이전 사각형 안에 있는 곳이라면 pass
                    if (board[j][k] == 2) continue;

                    // 테두리 선인 경우에는 1 아닌 경우에는 내부이므로 2
                    if (j == SIZE - 2 * rectangle[i][3] || j == SIZE - 2 * rectangle[i][1] || k == 2 * rectangle[i][0] || k == 2 * rectangle[i][2]) {
                        board[j][k] = 1;
                    } else board[j][k] = 2;
                }
            }
        }

        return bfs(2 * characterX, 2 * characterY, 2 * itemX, 2 * itemY) / 2;
    }

    static int bfs(int characterX, int characterY, int itemX, int itemY) {
        Queue<Point> q = new LinkedList<>();
        int count = 0;

        q.add(new Point(characterX, SIZE - characterY));
        visited[SIZE - characterY][characterX] = true;

        while (!q.isEmpty()) {
            int qSize = q.size();

            for (int i = 0; i < qSize; i++) {
                Point now = q.poll();

                if (now.x == itemX && now.y == SIZE - itemY) return count;

                for (int j = 0; j < 4; j++) {
                    int nx = now.x + dx[j];
                    int ny = now.y + dy[j];

                    if (nx < 0 || nx > SIZE  || ny < 0 || ny > SIZE) continue;

                    if (board[ny][nx] != 1 || visited[ny][nx]) continue;

                    visited[ny][nx] = true;
                    q.add(new Point(nx, ny));
                }
            }
            count++;
        }
        return -1;
    }
}