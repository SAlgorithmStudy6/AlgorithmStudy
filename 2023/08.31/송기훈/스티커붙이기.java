package baekjoon.gold.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커붙이기 {

    static int N, M, K, R, C;
    static boolean[][] matrix = new boolean[40][40];
    static int[][] sticker = new int[10][10];
    static int[][] tempArr = new int[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 세로
        M = Integer.parseInt(st.nextToken());   // 가로
        K = Integer.parseInt(st.nextToken());   // 스티커의 수

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < 4; i++) {
                if (solve()) {
                    break;
                } else {
                    rotate();
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j]) answer += 1;
            }
        }

        System.out.println(answer);
    }

    static boolean solve() {
        for (int i = 0; i <= N - R; i++) {
            for (int j = 0; j <= M - C; j++) {
                if (check(i, j)) {
                    putSticker(i, j);
                    return true;
                }
            }
        }
        return false;
    }

    static boolean check(int y, int x) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 스티커를 둘 곳에 이미 자리를 차지하고 있다면 false
                if (matrix[y + i][x + j] && sticker[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static void putSticker(int y, int x) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (sticker[i][j] == 1) {
                    matrix[y + i][x + j] = true;
                }
            }
        }
    }

    static void rotate() {
        for (int i = 0; i < C; i++) {
            for (int j = 0, k = R - 1; j < R; j++, k--) {
                tempArr[i][j] = sticker[k][i];
            }
        }

        int swap = R;
        R = C;
        C = swap;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sticker[i][j] = tempArr[i][j];
            }
        }
    }
}