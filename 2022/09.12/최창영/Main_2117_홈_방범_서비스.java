import java.util.*;
import java.io.*;

public class Main_2117_홈_방범_서비스 {
    static int N, M, maxBenefit, result;
    static int[][] arr;
    static int[] dirX = {0, 0, -1, 1};
    static int[] dirY = {-1, 1, 0, 0};

    static class Coordinates {
        int x;
        int y;
        int dist;

        public Coordinates(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    } // End of Coordinates class

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/2117.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append('#').append(t).append(' ');

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken()); // 집 하나당 지불하는 비용.
            init();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int k = 1; k <= N+1; k++) { // 방범 면적의 크기 증가.
                int protectArea = preventionAreaCalc(k); // 방범 면적 계산
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        BFS(i, j, k, protectArea);
                    }
                }
            }

            sb.append(result).append('\n');
        }

        bw.write(sb.toString());
        bw.close();
    } // End of main

    // 어차피 K값은 BFS탐색 전에 고정됨. K는 한번만 계산하면 그 값을 활용할 수 있음
    private static void BFS(int x, int y, int distLimit, int protectArea) {
        boolean[][] isVisited = new boolean[N][N];
        isVisited[x][y] = true;
        int homeCount = 0;
        if (arr[x][y] == 1) {
            homeCount++;
        }

        Queue<Coordinates> que = new LinkedList<>();
        que.offer(new Coordinates(x, y, 1));

        while (!que.isEmpty()) {
            Coordinates cor = que.poll();

            for (int i = 0; i < 4; i++) {
                int nowX = cor.x + dirX[i];
                int nowY = cor.y + dirY[i];
                int dist = cor.dist;

                if (rangeCheck(nowX, nowY) && !isVisited[nowX][nowY] && dist < distLimit) {
                    isVisited[nowX][nowY] = true;
                    que.offer(new Coordinates(nowX, nowY, dist + 1));

                    if (arr[nowX][nowY] == 1) {
                        homeCount++;
                    }
                }
            }
        }

        if (benefitCalc(protectArea, homeCount) >= 0 && maxBenefit <= benefitCalc(protectArea, homeCount)) {
            result = Math.max(result, homeCount);
        }

    } // End of BFS


    private static int benefitCalc(int area, int homeCount) { // 이익을 계산하는 메소드
        // 방범 면적 - 집의 개수 * M
        return (homeCount * M) - area;
    } // End of calc

    private static int preventionAreaCalc(int K) {
        return K * K + (K - 1) * (K - 1);
    } // End of preventionAreaCalc

    private static boolean rangeCheck(int nowX, int nowY) {
        return nowX >= 0 && nowX < N && nowY >= 0 && nowY < N;
    } // End of rangeCheck

    private static void init() {
        result = Integer.MIN_VALUE;
        maxBenefit = Integer.MIN_VALUE;
        arr = new int[N][N];
    } // End of init
} // End of Main class