import java.io.*;
import java.util.*;

public class Main {
    static final int DEGREE = 90;
    static int[][] notebook;
    static int[][] rotateSticker;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        notebook = new int[n][m];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int[][] sticker = new int[r][c];

            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());

                for (int l = 0; l < c; l++) {
                    sticker[j][l] = Integer.parseInt(st.nextToken());
                }
            }

            for (int j = 0; j < 4; j++) {
                rotate(sticker, r, c, DEGREE * j);
                if (stick()) break; // 붙일 수 있으면 종료
            }
        }

        bw.write(String.valueOf(answer));

        bw.flush();
        bw.close();
    }

    static void rotate(int[][] sticker, int r, int c, int degree) {
        switch (degree) { // 각도에 따른 스티커 배열 생성
            case 90:
            case 270:
                rotateSticker = new int[c][r];
                break;
            case 0:
            case 180:
                rotateSticker = new int[r][c];
                break;
        }

        for (int i = 0; i < rotateSticker.length; i++) { // 회전 시킨 값 할당
            for (int j = 0; j < rotateSticker[0].length; j++) {
                switch (degree) {
                    case 0:
                        rotateSticker[i][j] = sticker[i][j];
                        break;
                    case 90:
                        rotateSticker[i][j] = sticker[r - 1 - j][i];
                        break;
                    case 180:
                        rotateSticker[i][j] = sticker[r - 1 - i][c - 1 - j];
                        break;
                    case 270:
                        rotateSticker[i][j] = sticker[j][c - 1 - i];
                        break;
                }
            }
        }
    }

    static boolean stick() {
        for (int i = 0; i < notebook.length; i++) {
            for (int j = 0; j < notebook[0].length; j++) {
                if (search(j, i)) return true;
            }
        }
        return false;
    }

    static boolean search(int x, int y) {
        int n = notebook.length;
        int m = notebook[0].length;
        int r = rotateSticker.length;
        int c = rotateSticker[0].length;

        if (x + c > m || y + r > n) return false; // 범위를 벗어나면 종료

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (rotateSticker[i][j] == 1 && notebook[i + y][j + x] == 1) { // 스티커를 붙일 벽이 1이라면 종료
                    return false;
                }
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (rotateSticker[i][j] == 1) {
                    notebook[i + y][j + x] = 1;
                    answer++;
                }
            }
        }
        return true;
    }
}