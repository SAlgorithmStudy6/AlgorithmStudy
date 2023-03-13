import java.util.*;
import java.io.*;

// 아기 상어가 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력한다.
// 아기상어는 자신보다 작은 물고기만 먹을 수 있다.
// 자신보다 큰 물고기가 있는 칸은 지나갈 수 없다.
// 자신의 크기만큼 물고기를 먹어야 크기가 커진다.

// 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
// 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
// 먹을 수 있는 물고기가 1마리 보다 많앋면, 거리가 가장 가까운 물고기를 먹으러 간다.
// 거리는 아기 상어가 있는 칸에서 물고기가
// 거리가 가장 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
public class Main_16236_아기_상어 {
    static int N, result, startX, startY;
    static int[][] arr;
    static int[] dirX = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dirY = {0, 0, -1, 1};
    static Shark babyShark;

    static class Coordinates implements Comparable<Coordinates> {
        int x;
        int y;
        int dist;

        public Coordinates(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Coordinates o) {
            if (dist == o.dist) {

                if (x == o.x) {
                    return y - o.y;
                }
                return x - o.x;
            }

            return this.dist - o.dist;
        }
    } // End of Coordinates class

    static class Shark {
        int size;
        int eatCount;

        public Shark(int size, int eatCount) {
            this.size = size;
            this.eatCount = eatCount;
        }
    } // End of Shark class

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/16236.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        init();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 9) {
                    startX = i;
                    startY = j;
                    arr[i][j] = 0;
                }
            }
        }

        BFS(startX, startY);
        System.out.println(result);
    } // End of main

    private static void BFS(int x, int y) {
        PriorityQueue<Coordinates> pque = new PriorityQueue<>();
        boolean[][] isVisited = new boolean[N][N];
        pque.offer(new Coordinates(x, y, 0));
        isVisited[x][y] = true;

        while (!pque.isEmpty()) {
            Coordinates pollCor = pque.poll();

            for (int i = 0; i < 4; i++) {
                int nowX = dirX[i] + pollCor.x;
                int nowY = dirY[i] + pollCor.y;

                if (rangeCheck(nowX, nowY) && !isVisited[nowX][nowY] && arr[nowX][nowY] <= babyShark.size) {
                    isVisited[nowX][nowY] = true;
                    pque.offer(new Coordinates(nowX, nowY, pollCor.dist + 1));
                }
            }

            // 갈 수 있는 방향으로 1 만큼 거리 탐색 후, 갈 수 있는 곳이 있다면,
            if (!pque.isEmpty()) {
                Coordinates peekCor = pque.peek();

                if (arr[peekCor.x][peekCor.y] < babyShark.size && arr[peekCor.x][peekCor.y] > 0) {
                    babyShark.eatCount++;

                    // 먹은 수와, 상어의 크기가 같으면 크기가 커진다.
                    if (babyShark.eatCount == babyShark.size) {
                        babyShark.size++;
                        babyShark.eatCount = 0;
                    }
                    arr[peekCor.x][peekCor.y] = 0; // 먹은 자리는 0으로 처리

                    pque.clear(); // 다시 출발 하기 위해서 que를 clear
                    result += peekCor.dist; // 거리 값을 result에 누적
                    pque.offer(new Coordinates(peekCor.x, peekCor.y, 0)); // 새로운 출발지 설정
                    isVisited = new boolean[N][N];
                    isVisited[peekCor.x][peekCor.y] = true;
                }
            }
        }

    } // End of BFS

    private static boolean rangeCheck(int nowX, int nowY) {
        return nowX >= 0 && nowX < N && nowY >= 0 && nowY < N;
    } // End of rangeCheck

    private static void init() {
        arr = new int[N][N];
        babyShark = new Shark(2, 0);
    } // End of init
} // End of Main class