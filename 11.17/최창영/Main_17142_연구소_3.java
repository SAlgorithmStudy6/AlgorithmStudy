import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/17142
// 바이러스를 어떻게 놓아도 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우에는 -1을 출력
public class Main_17142_연구소_3 {
    private static final int INF = Integer.MAX_VALUE;
    private static final int MAX_ARR_SIZE = 50;
    static int N, M, result;
    static int[][] map = new int[MAX_ARR_SIZE][MAX_ARR_SIZE];
    static int[][] tempMap = new int[MAX_ARR_SIZE][MAX_ARR_SIZE];
    static int[][] dist = new int[MAX_ARR_SIZE][MAX_ARR_SIZE];
    static Coordinates[] comb;

    static int[] dirX = {-1, 1, 0, 0};
    static int[] dirY = {0, 0, -1, 1};
    static List<Coordinates> blankList;
    static List<Coordinates> virusList;
    static boolean[] isVisited;

    static class Coordinates {
        int x;
        int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    } // End of Coordinates class

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/17142.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();

        // 0은 빈칸, 1은 벽, 2는 바이러스
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 0) {
                    // 빈공간을 저장
                    blankList.add(new Coordinates(i, j));
                } else if (map[i][j] == 2) {
                    virusList.add(new Coordinates(i, j));
                    // 바이러스 좌표는 저장하고 -1로 표시
                    map[i][j] = -1;
                }
            }
        }

        // 빈공간이 없으면 0을 출력
        if (blankList.isEmpty()) {
            System.out.println(0);
            return;
        }

        // 빈 공간에, 바이러스 좌표를 넣기.
        isVisited = new boolean[virusList.size()];
        DFS(0, 0, virusList.size());

        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(result);
    } // End of main

    private static void DFS(int index, int depth, int size) {

        if (depth == M) {
            mapCopy(); // 다시 map 원상태로 복귀
            virusPlacement(); // 만들어진 조합으로 바이러스를 지도에 배치

            int bfsResult = BFS(); // 바이러스 퍼트리기 시작.
            result = Math.min(bfsResult, result);
            return;
        }

        for (int i = index; i < size; i++) {
            if (isVisited[i]) continue;

            isVisited[i] = true;
            comb[depth] = virusList.get(i);
            DFS(i + 1, depth + 1, size);
            isVisited[i] = false;
        }

    } // End of DFS

    private static int BFS() { // 바이러스 퍼트리기.
        boolean[][] isVisitedMap = new boolean[N][N];
        Queue<Coordinates> que = new LinkedList<>();
        int maxTime = -1;

        // 바이러스 좌표들 집어넣기
        for (Coordinates coor : comb) {
            que.offer(new Coordinates(coor.x, coor.y));
            isVisitedMap[coor.x][coor.y] = true;
            tempMap[coor.x][coor.y] = 2;
            dist[coor.x][coor.y] = 0;
        }

        while (!que.isEmpty()) {
            Coordinates coor = que.poll();

            for (int i = 0; i < 4; i++) {
                int nowX = dirX[i] + coor.x;
                int nowY = dirY[i] + coor.y;

                // 해당 위치에 가장 먼저 도착한 최단 값이 들어가야된다.
                // 계속해서 값을 갱신하면 안됨.
                if (!rangeCheck(nowX, nowY) || isVisitedMap[nowX][nowY]) continue;
                else if (!findEmptySpace()) {
                    // 빈 공간이 없으면 곧 바로 중지
                    return findMaxTime();
                }

                if (tempMap[nowX][nowY] == -1) {
                    isVisitedMap[nowX][nowY] = true;
                    tempMap[nowX][nowY] = 2;
                    que.offer(new Coordinates(nowX, nowY));
                    dist[nowX][nowY] = dist[coor.x][coor.y] + 1;
                } else if (tempMap[nowX][nowY] == 0) {
                    isVisitedMap[nowX][nowY] = true;
                    tempMap[nowX][nowY] = 2;
                    que.offer(new Coordinates(nowX, nowY));
                    dist[nowX][nowY] = dist[coor.x][coor.y] + 1;
                }
            }
        }

        if (findEmptySpace()) {
            // 빈 공간이 있으면 최댓값을 반환
            return Integer.MAX_VALUE;
        }

        // 빈 공간이 없을 경우, 가장 많이 걸린 시간을 찾아서 반환.
        return findMaxTime();
    } // End of BFS

    private static int findMaxTime() {
        int maxTime = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dist[i][j] == INF) continue;
                maxTime = Math.max(maxTime, dist[i][j]);
            }
        }

        return maxTime;
    } // End of findMaxTime

    private static void virusPlacement() {
        // 바이러스 활성화, 비활성화 시키기

        for (Coordinates coor : comb) {
            tempMap[coor.x][coor.y] = 2;
        }

    } // End of virusPlacement

    private static boolean findEmptySpace() {
        // 빈공간 찾기.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tempMap[i][j] == 0) {
                    return true;
                }
            }
        }

        return false; // 빈 공간 없음
    } // End of findEmptySpace

    private static void mapCopy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempMap[i][j] = map[i][j];
                dist[i][j] = INF;
            }
        }

    } // End of mapCopy

    private static boolean rangeCheck(int nowX, int nowY) {
        return nowX >= 0 && nowX < N && nowY >= 0 && nowY < N;
    } // End of rangeCheck

    private static void init() {
        result = Integer.MAX_VALUE;
        blankList = new ArrayList<>();
        virusList = new ArrayList<>();
        comb = new Coordinates[M];
    } // End of init
} // End of Main class
