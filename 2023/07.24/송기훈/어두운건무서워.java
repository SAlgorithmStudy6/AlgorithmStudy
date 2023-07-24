package baekjoon.silver.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 어두운건무서워 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int R, C, Q;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        matrix = new int[R + 1][C + 1];

        for (int r = 1; r <= R; r++) {
            st = new StringTokenizer(br.readLine());
            int sum = 0;

            for (int c = 1; c <= C; c++) {
                sum += Integer.parseInt(st.nextToken());
                matrix[r][c] = matrix[r - 1][c] + sum;
            }
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int a = matrix[r2][c2] - matrix[r1 - 1][c2] - matrix[r2][c1 - 1] + matrix[r1 - 1][c1 - 1];
            int b = (r2 - r1 + 1) * (c2 - c1 + 1);

            System.out.println(a / b);


        }

    }

}