import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 스티커붙이기 {
    static int N;
    static int M;
    static int K;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], 0);
        }
        ArrayList<int[][]> stickerList = new ArrayList<>();
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int width = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[height][width];
            for (int i = 0; i < height; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < width; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            stickerList.add(sticker);
        }
        int rotateCount = 0;
        while (!stickerList.isEmpty()) {
            int[][] sticker = stickerList.remove(0);
            boolean attached = false;
            for (int i = 0; i < N && !attached; i++) {
                for (int j = 0; j < M && !attached; j++) {
                    boolean available = true;
                    for (int k = 0; k < sticker.length && available; k++) {
                        if (i + k >= N) break;
                        for (int l = 0; l < sticker[0].length; l++) {
                            if (j + l >= M) break;
                            if (sticker[k][l] != 0) {
                                if (board[i + k][j + l] != 0) {
                                    available = false;
                                    break;
                                }
                            }
                            // 정상적으로 스티커 배열 끝까지 다 돌았으면, 붙임
                            if (k == sticker.length - 1 && l == sticker[0].length - 1) {
                                attached = true;
                                for (int x = 0; x < sticker.length; x++) {
                                    for (int y = 0; y < sticker[0].length; y++) {
                                        if (sticker[x][y] == 1) board[i + x][j + y] = 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!attached) {
                // 90도 180도 270도 다안되면
                if (rotateCount == 4) {
                    rotateCount = 0;
                } else {
                    int[][] rotatedSticker = rotateSticker(sticker);
                    stickerList.add(0, rotatedSticker);
                    rotateCount++;
                }
            }else{
                rotateCount = 0;
            }
        }
        int answer = 0;
        for(int[] row : board){
            for(int val: row){
                answer += val;
            }
        }
        System.out.println(answer);
    }

    static int[][] rotateSticker(int[][] sticker) {
        int height = sticker.length;
        int width = sticker[0].length;
        int[][] rotatedSticker = new int[width][height];
        for (int i = 0; i < rotatedSticker.length; i++) {
            for (int j = 0; j < rotatedSticker[i].length; j++) {
                rotatedSticker[i][j] = sticker[height - 1 - j][i];
            }
        }
        return rotatedSticker;
    }
}
