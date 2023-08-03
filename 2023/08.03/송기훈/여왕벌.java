package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 여왕벌 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int M, N;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        matrix = new int[M][M];
        for (int[] m: matrix) {
            Arrays.fill(m, 1);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            cornerGrow(a, b, c);
        }

        // 코너를 제외하고는 윗칸의 것을 따라가면 됨
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < M; x++) {
                if (y == 0) {
                    sb.append(matrix[y][x]).append(" ");
                } else if (x == 0) {
                    sb.append(matrix[y][x]).append(" ");
                } else {
                    sb.append(matrix[0][x]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    static void cornerGrow(int a, int b, int c) {
        int y = M - 1;
        int x = 0;
        // 0 성장
        while (a > 0) {
            if (y != 0) {
                y -= 1;
            } else {
                x += 1;
            }
            a -= 1;
        }
        // 1 성장
        while (b > 0) {
            matrix[y][x] += 1;

            if (y != 0) {
                y -= 1;
            } else {
                x += 1;
            }
            b -= 1;
        }
        // 2 성장
        while (c > 0) {
            matrix[y][x] += 2;

            if (y != 0) {
                y -= 1;
            } else {
                x += 1;
            }
            c -= 1;
        }
    }

}