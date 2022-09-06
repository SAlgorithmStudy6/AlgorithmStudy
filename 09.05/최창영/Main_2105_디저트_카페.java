import java.util.*;
import java.io.*;

// 디저트를 가장 많이 먹을 수 있는 경우의 디저트 종류 수를 출력
// 디저트를 먹을 수 없는 경우 -1을 출력
// 탐색하는 방향에서 직사각형의 특징을 생각해야 됨 -> 직사각형의 특징. 마주보는 변의 길이가 같음
public class Main_2105_디저트_카페 {
    static int N;
    static int[][] map;
    static int[] dirX = {-1, 1, 1, -1}; // 대각선 방향으로 시계방향. (상우 -> 하우 -> 하좌 -> 상좌)
    static int[] dirY = {1, 1, -1, -1};
    static boolean[][] isVisited; // map의 경로상 방문여부를 체크하는 배열
    static boolean[] isVisitedDesert; // 방문한 디저트가 겹치는지 여부를 파악하기 위한 배열
    static int maxResult;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/2105.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append('#').append(t).append(' ');

            N = Integer.parseInt(br.readLine());
            init(); // 변수 초기화

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 완전탐색 & 백트래킹
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == 0 && (j == 0 || j == N - 1)) continue;
                    else if (i == N - 1 && (j == 0 || j == N - 1)) continue;

                    isVisited[i][j] = true;
                    isVisitedDesert[map[i][j]] = true;
                    DFS(i, j, i, j, 1, 0);
                    isVisitedDesert[map[i][j]] = false;
                    isVisited[i][j] = false;
                }
            }

            sb.append(maxResult).append('\n');
        }

        bw.write(sb.toString());
        bw.close();
    } // End of main

    private static void DFS(int startX, int startY, int x, int y, int counting, int index) {
        for (int i = index; i < 4; i++) {
            int nowX = x + dirX[i];
            int nowY = y + dirY[i];

            //  재귀 탈출 조건
            // counting이 4이상이고, 시작한 곳으로 돌아오면 중지,
            if (startX == nowX && startY == nowY && counting >= 4) {
                maxResult = Math.max(maxResult, counting);
                return;
            }

            // 만약 i의 값으로 계속 이동할 수 있으면 계속해서 이동.
            if (rangeCheck(nowX, nowY) && !isVisited[nowX][nowY] && !isVisitedDesert[map[nowX][nowY]]) {

                isVisited[nowX][nowY] = true;
                isVisitedDesert[map[nowX][nowY]] = true;

                // 만약 i의 값으로 계속 이동할 수 있으면 계속해서 이동.
                DFS(startX, startY, nowX, nowY, counting + 1, i);
                isVisitedDesert[map[nowX][nowY]] = false;
                isVisited[nowX][nowY] = false;
            }
        }

    } // End of DFS

    private static boolean rangeCheck(int nowX, int nowY) {
        return nowX >= 0 && nowX < N && nowY >= 0 && nowY < N;
    } // End of rangeCheck

    private static void init() { // 변수를 초기화 하는 메소드
        map = new int[N][N];
        isVisited = new boolean[N][N];
        isVisitedDesert = new boolean[101];
        maxResult = -1;
    } // End of init
} // End of Main class
