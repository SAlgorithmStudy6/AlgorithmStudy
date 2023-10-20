package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 배열_돌리기_3 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, R;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int action = Integer.parseInt(st.nextToken());
            transform(action);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(matrix[i][j]);
                if (j != M - 1) {
                    sb.append(" ");
                }
            }
            if (i != N - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb);

    }

    static void transform(int action) {
        int[][] temp;
        if (action == 1) {
            temp = new int[N][M];

            for (int i = N - 1; i >= 0; i--) {
                temp[N - 1 - i] = matrix[i];
            }
        } else if (action == 2) {
            temp = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    temp[i][j] = matrix[i][M - 1 - j];
                }
            }
        } else if (action == 3) {
            temp = new int[M][N];

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    temp[i][j] = matrix[N - 1 - j][i];
                }
            }

            int swap = N;
            N = M;
            M = swap;
        } else if (action == 4) {
            temp = new int[M][N];

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    temp[i][j] = matrix[j][M - 1 - i];
                }
            }
            int swap = N;
            N = M;
            M = swap;
        } else if (action == 5) {
            temp = new int[N][M];

            int halfN = N / 2;
            int halfM = M / 2;

            // 4사분면
            for (int i = 0; i < halfN; i++) {
                for (int j = 0; j < halfM; j++) {
                    temp[i][j] = matrix[i + halfN][j];
                }
            }

            // 1사분면
            for (int i = 0; i < halfN; i++) {
                for (int j = halfM; j < M; j++) {
                    temp[i][j] = matrix[i][j - halfM];
                }
            }

            // 2사분면
            for (int i = halfN; i < N; i++) {
                for (int j = halfM; j < M; j++) {
                    temp[i][j] = matrix[i - halfN][j];
                }
            }

            // 3사분면
            for (int i = halfN; i < N; i++) {
                for (int j = 0; j < halfM; j++) {
                    temp[i][j] = matrix[i][j + halfM];
                }
            }
        } else {
            temp = new int[N][M];

            int halfN = N / 2;
            int halfM = M / 2;

            // 4사분면
            for (int i = 0; i < halfN; i++) {
                for (int j = 0; j < halfM; j++) {
                    temp[i][j] = matrix[i][j + halfM];
                }
            }

            // 1사분면
            for (int i = 0; i < halfN; i++) {
                for (int j = halfM; j < M; j++) {
                    temp[i][j] = matrix[i + halfN][j];
                }
            }

            // 2사분면
            for (int i = halfN; i < N; i++) {
                for (int j = halfM; j < M; j++) {
                    temp[i][j] = matrix[i][j - halfM];
                }
            }

            // 3사분면
            for (int i = halfN; i < N; i++) {
                for (int j = 0; j < halfM; j++) {
                    temp[i][j] = matrix[i - halfN][j];
                }
            }

        }

        matrix = temp;

    }

}
