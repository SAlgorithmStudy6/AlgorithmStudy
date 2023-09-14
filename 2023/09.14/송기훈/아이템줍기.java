package programmers.lv3;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 아이템줍기 {

    static int answer;

    static int[][] matrix;
    static boolean[][] visited;

    static int[] dX = {1, 0, -1, 0};
    static int[] dY = {0, 1, 0, -1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        answer = 0;
        matrix = new int[101][101];
        visited = new boolean[101][101];

        // 테두리를 1로 내부는 -1
        // 좌표는 2배 해줘야 한다...경로가 아니어도 모두 인접한 한칸이니까
        for (int i = 0; i < rectangle.length; i++) {
            int x1 = rectangle[i][0] * 2;
            int y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2;
            int y2 = rectangle[i][3] * 2;

            for (int x = x1 ; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    // 이미 다른 도형의 내부이면 테두리가 될 수 없음
                    if (matrix[x][y] == -1) continue;
                    matrix[x][y] = -1;
                    if (x == x1 || x == x2 || y == y1 || y == y2) {
                        matrix[x][y] = 1;
                    }
                }
            }
        }

        bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);

        return answer / 2;
    }

    public void bfs(int characterX, int characterY, int itemX, int itemY) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(characterX);
        queue.offer(characterY);
        visited[characterX][characterY] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nX = x + dX[i];
                int nY = y + dY[i];

                if (nX < 0 || nY < 0 || nX > 100 || nY > 100) continue;
                if (matrix[nX][nY] != 1) continue;
                if (visited[nX][nY]) continue;

                matrix[nX][nY] = matrix[x][y] + 1;

                // 도착?
                if (nX == itemX && nY == itemY) {
                    if (answer == 0) {
                        answer = matrix[nX][nY];
                    } else {
                        answer = Math.min(answer, matrix[nX][nY]);
                    }
                    continue;
                }

                // 도착 판별 이후에 큐에 넣고 visited 체크해야 여러 곳에서 올 수 있음
                visited[nX][nY] = true;
                queue.offer(nX);
                queue.offer(nY);
            }
        }

    }
}
