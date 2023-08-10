package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class 회장뽑기 {

    static int N;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        matrix = new int[N+1][N+1];

        // (i, i)를 제외하고 모두 큰 값으로 초기화
        for (int[] m : matrix) {
            Arrays.fill(m, 5000);
        }
        for (int i = 1; i <= N; i++) {
            matrix[i][i] = 0;
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            if (y == -1 && x == -1) {
                break;
            }

            matrix[y][x] = 1;
            matrix[x][y] = 1;
        }

        // 플로이드 워셜
        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {

                    if (matrix[start][end] > matrix[start][mid] + matrix[mid][end]) {
                        matrix[start][end] = matrix[start][mid] + matrix[mid][end];
                    }

                }
            }
        }

        int minScore = Integer.MAX_VALUE;
        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int temp = 0;
            for (int j = 1; j <= N; j++) {
                if (matrix[i][j] != 5000) {
                    temp = Math.max(matrix[i][j], temp);
                }
            }

            if (minScore == temp) {
                candidates.add(i);
            }
            else if (minScore > temp) {
                minScore = Math.min(temp, minScore);
                candidates = new ArrayList<>();
                candidates.add(i);
            }

        }

        Collections.sort(candidates);

        StringBuilder sb = new StringBuilder();
        sb.append(minScore).append(" ").append(candidates.size()).append("\n");
        for (int i = 0; i < candidates.size(); i++) {
            sb.append(candidates.get(i));
            if (i != candidates.size() - 1) {
                sb.append(" ");
            }
        }

        System.out.println(sb);

    }
}
