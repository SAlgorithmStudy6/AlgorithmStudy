package programmers.lv3;

import java.util.*;

//class Test {
//
//    public static void main(String[] args) {
////        int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
//        int[][] board = {
//                {0, 0, 0, 0, 0, 0, 0, 0},
//                {1, 0, 1, 1, 1, 1, 1, 0},
//                {1, 0, 0, 1, 0, 0, 0, 0},
//                {1, 1, 0, 0, 0, 1, 1, 1},
//                {1, 1, 1, 1, 0, 0, 0, 0},
//                {1, 1, 1, 1, 1, 1, 1, 0},
//                {1, 1, 1, 1, 1, 1, 1, 0},
//                {1, 1, 1, 1, 1, 1, 1, 0}
//        };
//        System.out.println(new 경주로_건설().solution(board));
//    }
//
//}

public class 경주로_건설 {

    int[] dY = {1, 0, -1, 0};
    int[] dX = {0, 1, 0, -1};
    int n, minValue;
    boolean[][][] visited;
    int[][] nBoard, matrix;

    public int solution(int[][] board) {
        int answer = 0;

        n = board.length;
        minValue = Integer.MAX_VALUE;
        nBoard = board;
        matrix = new int[n][n];
        visited = new boolean[n][n][4];

        for (int[] m : matrix) {
            Arrays.fill(m, 9999);
        }

        bfs(0, 0, -1, 0);

        answer = minValue;

        return answer;
    }

    public void bfs(int y, int x, int dir, int cost) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(y, x, dir, cost));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.y == n - 1 && now.x == n - 1) {
                minValue = Math.min(now.cost, minValue);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nY = now.y + dY[i];
                int nX = now.x + dX[i];
                int nDir = i;
                int nCost = now.cost;

                if (nY < 0) continue;
                if (nX < 0) continue;
                if (nY >= n) continue;
                if (nX >= n) continue;
                if (nBoard[nY][nX] == 1) continue;

                if (now.dir == -1 || now.dir == nDir) {
                    nCost += 100;
                } else {
                    // 역주행은 가격이 비싸서 알아서 걸러짐
                    nCost += 600;
                }

                if (!visited[nY][nX][nDir] || matrix[nY][nX] >= nCost) {
                    queue.offer(new Node(nY, nX, nDir, nCost));
                    visited[nY][nX][nDir] = true;
                    matrix[nY][nX] = nCost;
                }

            }

        }

    }

    class Node {
        int y, x, dir, cost;

        public Node(int y, int x, int dir, int cost) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.cost = cost;
        }
    }

}

/*
import java.util.*;

class Solution {
    int[] dY = {1, 0, -1, 0};
    int[] dX = {0, 1, 0, -1};
    int n, minValue;
    int[][] nBoard, matrix;

    public int solution(int[][] board) {
        int answer = 0;

        n = board.length;
        minValue = Integer.MAX_VALUE;
        nBoard = board;
        matrix = new int[n][n];
        for (int[] m : matrix) {
            Arrays.fill(m, Integer.MAX_VALUE);
        }


        bfs(0, 0, -1, 0);

        answer = minValue;

        return answer;
    }

    public void bfs(int y, int x, int dir, int cost) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(y, x, dir, cost));

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.y == n - 1 && now.x == n - 1) {
                minValue = Math.min(now.cost, minValue);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nY = now.y + dY[i];
                int nX = now.x + dX[i];
                int nDir = i;
                int nCost = now.cost;

                if (nY < 0) continue;
                if (nX < 0) continue;
                if (nY >= n) continue;
                if (nX >= n) continue;
                if (nBoard[nY][nX] == 1) continue;

                if (now.dir == -1) {
                    nCost += 100;
                } else if (now.dir == nDir) {
                    nCost += 100;
                } else {
                    // 역주행은 가격이 비싸서 알아서 걸러짐
                    nCost += 600;
                }

                if (matrix[nY][nX] >= nCost) {
                    matrix[nY][nX] = nCost;
                    queue.offer(new Node(nY, nX, nDir, nCost));
                }

            }

        }

    }

    class Node {
        int y, x, dir, cost;

        public Node(int y, int x, int dir, int cost) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.cost = cost;
        }
    }

}
 */
