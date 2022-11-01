import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, X, Y, K, map[][], order[], dice[];
    static int dx[] = {0, 0, 0, -1, +1}; // 동서북남
    static int dy[] = {0, 1, -1, 0, 0}; // 동서북남
    static int botton = 6;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        order = new int[K];
        dice = new int[7];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        int nx = X;
        int ny = Y;
        int diceTemp[] = new int[7];
        boolean inRange = false;
        for (int i = 0; i < K; i++) {
            inRange = false;
            if (order[i] == 1) { // 동
                if (rangeCheck(nx + dx[1], ny + dy[1])) { // 다음 칸이 존재하는 칸이면
                    nx += dx[1]; // 칸으로 값을 옮겨주고
                    ny += dy[1];

                    inRange = true; // 입실 체크

                    rollDice(1); // 주사위 굴려,

                    if (map[nx][ny] > 0) { // 칸에 값이 적혀있으면, 주사위 바닥에 복사, 칸은 0
                        dice[6] = map[nx][ny];
                        map[nx][ny] = 0;
                    } else { // 주사의 바닥 값을 칸에 복사,
                        map[nx][ny] = dice[6];
                    }
                }
            } else if (order[i] == 2) {
                if (rangeCheck(nx + dx[2], ny + dy[2])) {
                    nx += dx[2];
                    ny += dy[2];

                    inRange = true; // 입실 체크

                    rollDice(2);

                    if (map[nx][ny] > 0) { // 칸에 값이 적혀있으면, 주사위 바닥에 복사, 칸은 0
                        dice[6] = map[nx][ny];
                        map[nx][ny] = 0;
                    } else { // 주사의 바닥 값을 칸에 복사,
                        map[nx][ny] = dice[6];
                    }

                }
            } else if (order[i] == 3) {
                if (rangeCheck(nx + dx[3], ny + dy[3])) {
                    nx += dx[3];
                    ny += dy[3];

                    inRange = true; // 입실 체크

                    rollDice(3);

                    if (map[nx][ny] > 0) { // 칸에 값이 적혀있으면, 주사위 바닥에 복사, 칸은 0
                        dice[6] = map[nx][ny];
                        map[nx][ny] = 0;
                    } else { // 주사의 바닥 값을 칸에 복사,
                        map[nx][ny] = dice[6];
                    }
                }
            } else if (order[i] == 4) {
                if (rangeCheck(nx + dx[4], ny + dy[4])) {
                    nx += dx[4];
                    ny += dy[4];

                    inRange = true; // 입실 체크

                    rollDice(4);

                    if (map[nx][ny] > 0) { // 칸에 값이 적혀있으면, 주사위 바닥에 복사, 칸은 0
                        dice[6] = map[nx][ny];
                        map[nx][ny] = 0;
                    } else { // 주사의 바닥 값을 칸에 복사,
                        map[nx][ny] = dice[6];
                    }
                }
            }

            if (inRange)
                System.out.println(dice[1]);

        }


    }


    static void rollDice(int i) {
        int temp = 0;
        if (i == 1) {  //동 :  3->1, 1->4, 4->6, 6->3
            temp = dice[6];
            dice[6] = dice[4];
            dice[4] = dice[1];
            dice[1] = dice[3];
            dice[3] = temp;
        } else if (i == 2) { // 서 : 4->1, 1->3, 3->6, 6->4
            temp = dice[6];
            dice[6] = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[4];
            dice[4] = temp;
        } else if (i == 3) { // 북 : 1->2, 2->6, 6->5, 5->1
            temp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        } else if (i == 4) { // 남 : 2->1, 1->5, 5->6, 6->2
            temp = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = temp;
        }
    }

    static boolean rangeCheck(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
