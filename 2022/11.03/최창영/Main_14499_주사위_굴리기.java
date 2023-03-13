import java.io.*;
import java.util.*;

public class Main_14499_주사위_굴리기 {
    static int N, M, startX, startY;
    static int[][] map = new int[21][21];
    static int[] dice = new int[7];
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, - 1, 1};
    static StringBuilder sb;

    // 가장 처음에 주사위에는 모든 면에 0이 적혀있다.
    // 동쪽은 1, 서쪽은 2, 북쪽은 3 남쪽은 4
    // 이동한 칸에 쓰여있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
    // 0이 아닌경우에는 칸에 쓰여있는 수가 주사위 바닥면으로 복사되며, 칸에 쓰여있는 수는 0이 된다.


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/14499.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        startX = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        init();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            move(Integer.parseInt(st.nextToken()));
        }

        bw.write(sb.toString());
        bw.close();
    } // End of main

    private static void move(int direction) {
        int nowX = startX + dirX[direction - 1];
        int nowY = startY + dirY[direction - 1];

        if (!rangeCheck(nowX, nowY)) return;

        roll(direction, nowX, nowY);
        startX = nowX;
        startY = nowY;
    } // End of move

    private static void roll(int num, int x, int y) {
        int temp = dice[3];

        if (num == 1) {
            dice[3] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        } else if (num == 2) {
            dice[3] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        } else if (num == 3) {
            dice[3] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[1];
            dice[1] = temp;
        } else if (num == 4) {
            dice[3] = dice[1];
            dice[1] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        }

        if (map[y][x] == 0) {
            map[y][x] = dice[6];
        } else {
            dice[6] = map[y][x];
            map[y][x] = 0;
        }

        sb.append(dice[3]).append('\n');
    } // End of roll

    private static boolean rangeCheck(int nowX, int nowY) {
        return nowX >= 0 && nowX < M && nowY >= 0 && nowY < N;
    } // End of rangeCheck

    private static void init() {
        sb = new StringBuilder();
    } // End of init
} // End of Main class