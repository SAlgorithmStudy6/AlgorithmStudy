package programmers.lv2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Test {

    public static void main(String[] args) {
        String[][] places = {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };
        int[] a = new 거리두기_확인하기().solution(places);
        System.out.println(Arrays.toString(a));
    }

}

public class 거리두기_확인하기 {
    static int[] dY = {1, 0, -1, 0};
    static int[] dX = {0, 1, 0, -1};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            String[] place = places[i];

            boolean flag = true;
            for (int y = 0; y < 5; y++) {
                for (int x = 0; x < 5; x++) {
                    if (place[y].charAt(x) == 'P') {
                        if (!bfs(y, x, place)) {
                            flag = false;
                        }
                    }

                    if (!flag) break;
                }
                if (!flag) break;
            }

            if (flag) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }

    static boolean bfs(int y, int x, String[] place) {
        boolean[][] visited = new boolean[5][5];
        visited[y][x] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(y);
        queue.offer(x);

        while (!queue.isEmpty()) {
            int nowY = queue.poll();
            int nowX = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newY = nowY + dY[i];
                int newX = nowX + dX[i];

                if (newY < 0) continue;
                if (newY >= 5) continue;
                if (newX < 0) continue;
                if (newX >= 5) continue;
                if (newY == y && newX == x) continue;
                if (visited[newY][newX]) continue;

                int distance = Math.abs(newY - y) + Math.abs(newX - x);

                // 거리 내에 사람이 있다면 false
                if (place[newY].charAt(newX) == 'P' && distance <= 2) {
                    return false;
                } else if (place[newY].charAt(newX) == 'O' && distance < 2) {
                    // 공석인 경우
                    queue.offer(newY);
                    queue.offer(newX);
                    visited[newY][newX] = true;
                }
            }
        }

        return true;
    }

}
