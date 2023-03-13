import java.util.*;
import java.io.*;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRF8s6ezEDFAUo
// -1 : 블랙홀, 0 : 빈공간, 1 ~ 5 : 블록, 6 ~ 10 : 웜홀
// 게임에서 얻을 수 있는 최댓값을 구하라.
// 블록, 웜홀, 블랙홀 위치에서는 출발 할 수 없다.
// 점수는 벽에 부딪힌 횟수가 된다.
// 게임은 핀볼이 출발위치로 돌아오거나, 블랙홀에 빠질 때 끝나게 된다.

// 웜홀에 빠지게 되면, 동일한 숫자를 가진 반대편 웜홀로 나오게 되며 진행방향은 그대로 유지된다.
// 5번은 무조건 뒤로 돌아감

public class Main_5650_핀볼_게임 {
    static int N, result;
    static int[][] map;
    static int[] dirX = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dirY = {0, 0, -1, 1};

    static class Coordinates {
        int x;
        int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    } // End of Coordinates

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/5650.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append('#').append(t).append(' ');

            N = Integer.parseInt(br.readLine());
            init();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    // 돌아온다는 것을 감안했을 때, isVisited는 없어도 될 것 같음
                    if (map[i][j] == 0) {
                        for (int k = 0; k < 4; k++) {
                            DFS(i, j, i, j, k);
                        }
                    }
                }
            }

            sb.append(result).append('\n');
        }

        bw.write(sb.toString());
        bw.close();
    } // End of main

    private static void DFS(int startX, int startY, int x, int y, int direction) {
        int hitCount = 0;
        int nowX = x;
        int nowY = y;

        for (; ; ) {
            nowX = dirX[direction] + nowX;
            nowY = dirY[direction] + nowY;

            // 범위를 벗어나면 벽에 부딪혔기 때문에 방향을 반대로 전환
            if (!rangeCheck(nowX, nowY)) {
                // 틀린 코드여서 주석 처리
                //nowX = nowX - dirX[direction];
                //nowY = nowY - dirY[direction];
                direction = backDirection(direction);
                // 방향만 바꿔주고 다음턴에서 다시 움직임
                hitCount++;

            } else {
                if (map[nowX][nowY] == 0 && nowX == startX && nowY == startY) {
                    result = Math.max(result, hitCount);
                    return;
                } else if (map[nowX][nowY] == 1) {
                    if (direction == 0) {
                        // 상 -> 하
                        direction = backDirection(direction);
                    } else if (direction == 1) {
                        // 하 -> 우
                        direction = 3;
                    } else if (direction == 2) {
                        // 좌 -> 상
                        direction = 0;
                    } else {
                        // 우 -> 좌
                        direction = backDirection(direction);
                    }

                    hitCount++;
                } else if (map[nowX][nowY] == 2) {
                    if (direction == 0) {
                        // 상 -> 우
                        direction = 3;
                    } else if (direction == 1) {
                        // 하 -> 상
                        direction = backDirection(direction);
                    } else if (direction == 2) {
                        // 좌 -> 하
                        direction = 1;
                    } else {
                        // 우 -> 좌
                        direction = backDirection(direction);
                    }

                    hitCount++;
                } else if (map[nowX][nowY] == 3) {
                    if (direction == 0) {
                        // 상 -> 좌
                        direction = 2;
                    } else if (direction == 1) {
                        // 하 -> 상
                        direction = backDirection(direction);
                    } else if (direction == 2) {
                        // 좌 -> 우
                        direction = backDirection(direction);
                    } else {
                        // 우 -> 하
                        direction = 1;
                    }

                    hitCount++;
                } else if (map[nowX][nowY] == 4) {
                    if (direction == 0) {
                        // 상 -> 하
                        direction = backDirection(direction);
                    } else if (direction == 1) {
                        // 하 -> 좌
                        direction = 2;
                    } else if (direction == 2) {
                        // 좌 -> 우
                        direction = backDirection(direction);
                    } else {
                        // 우 -> 상
                        direction = 0;
                    }

                    hitCount++;
                } else if (map[nowX][nowY] == 5) {
                    direction = backDirection(direction);
                    hitCount++;
                } else if (map[nowX][nowY] == -1) {
                    result = Math.max(result, hitCount);
                    return;
                } else if (map[nowX][nowY] >= 6 && map[nowX][nowY] <= 10) {
                    // System.out.println(map[nowX][nowY]);
                    Coordinates wormCoor = findWormhole(map[nowX][nowY], nowX, nowY);
                    nowX = wormCoor.x;
                    nowY = wormCoor.y;
                }
            }
        }

    } // End of DFS

    private static int backDirection(int dir) {
        if (dir == 0) {
            return 1;
        } else if (dir == 1) {
            return 0;
        } else if (dir == 2) {
            return 3;
        } else {
            return 2;
        }
    } // End of backDirection

    private static Coordinates findWormhole(int holeNum, int nowX, int nowY) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == holeNum && (nowX != i || nowY != j)) {
                    return new Coordinates(i, j);
                }
            }
        }

        return null;
    } // End of findWormhole

    private static boolean rangeCheck(int nowX, int nowY) {
        return nowX >= 0 && nowX < N && nowY >= 0 && nowY < N;
    } // End of rangeCheck

    private static void init() {
        map = new int[N][N];
        result = -1;
    } // End of init
} // End of Main class