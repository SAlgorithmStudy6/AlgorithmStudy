package baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 소문난칠공주 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static char[][] matrix = new char[5][5];

    // 우 하 좌 상
    static int[] dY = {0, 1, 0, -1};
    static int[] dX = {1, 0, -1, 0};
    static boolean[] visited = new boolean[25];
    static int answer = 0;


    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 5; i++) {
            matrix[i] = br.readLine().toCharArray();
        }

        dfs(0, 0, new int[7]);

        System.out.println(answer);

    }

    static void dfs(int index, int depth, int[] selected) {
        if (depth == 7) {
            if (check(selected)) {
                answer += 1;
            }
            return;
        }

        for (int i = index; i < 25; i++) {
            if (!visited[i]) {
                selected[depth] = i;
                visited[i] = true;
                dfs(i + 1, depth + 1, selected);
                visited[i] = false;
            }
        }
    }

    static boolean check(int[] selected) {
        int sCount = 0;

        for (int loc : selected) {
            if (matrix[loc / 5][loc % 5] == 'S') {
                sCount += 1;
            }
        }

        if (sCount < 4) {
            return false;
        }

        // BFS로 연결되어 있는지 확인
        ArrayList<Integer> selectedList = new ArrayList<>();
        for (int loc : selected) {
            selectedList.add(loc);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(selectedList.get(0));
        while (!queue.isEmpty()) {
            int loc = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nY = loc / 5 + dY[i];
                int nX = loc % 5 + dX[i];

                if (nY < 0 || nY > 4 || nX < 0 || nX > 4) {
                    continue;
                }

                Integer value = nY * 5 + nX;
                if (selectedList.contains(value)) {
                    selectedList.remove(value);
                    queue.offer(value);
                }
            }
        }
        return selectedList.isEmpty();
    }

}
