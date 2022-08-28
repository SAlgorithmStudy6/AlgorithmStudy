import java.io.*;
import java.util.*;

// https://swexpertacademy.com/main/solvingProblem/solvingProblem.do
// 모든 몬스터를 없애고 고객들에게 확인시켜 작업을 완료하는 데 가장 빠른 시간을 구하는 프로그램을 작성해라.

// 몬스터를 처리하는 순서는 상관이 없다. 고객들에게 확인시켜 주는 순서도 상관이 없다.
// 몬스터를 처리했다고 해서 바로 그 몬스터를 처리해 달라고 요청한 고객에게 돌아가 확인할 필요도 없다.
public class Main_15172_헌터 {
    static int[][] map;
    static int[][] tempMap;

    static int N;
    static int[] dirX = {0, 0, -1, 1}; // 헌터는 상하좌우로 움직인다.
    static int[] dirY = {1, -1, 0, 0};

    static int[] orderArr; // 경우의 수를 담을 배열
    static List<Integer> allList; // 전체 몬스터와 고객들이 들어갈 리스트
    static int totalCount; // 총 몬스터와 고객의 수

    static boolean[] isVisited; // 방문 여부 배열
    static boolean[] monsterisVisited; // 몬스터 처치 여부 배열
    static int result; // 최종 결과 값
    
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
        System.setIn(new FileInputStream("res/15172.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int t = 1; t <= TC; t++) {
            sb.append('#').append(t).append(' ');

            N = Integer.parseInt(br.readLine());
            init(); // 변수들 초기화
            int clientCount = 0; // 고객의 수를 확인할 변수

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    tempMap[i][j] = map[i][j];

                    if (map[i][j] > 0) {
                        clientCount = Math.max(clientCount, map[i][j]);
                        allList.add(map[i][j]);
                    } else if (map[i][j] < 0) {
                        allList.add(map[i][j]);
                    }
                }
            }

            Collections.sort(allList);
            totalCount = allList.size();

            isVisited = new boolean[totalCount];
            monsterisVisited = new boolean[clientCount]; // 몬스터 방문여부를 체크할 배열
            orderArr = new int[totalCount]; // 방문 순서 경우의 수를 담을 배열 고객의 수의 2배 값으로 배열 크기 할당.
            DFS(0, totalCount); // 경우의 수 만들기

            sb.append(result).append('\n');
        } // End of for(TC)

        bw.write(sb.toString());
        bw.close();
    } // End of main

    // 첫번쨰 해결해야할 과제는 올바른 방문 순서를 만들어서 모든 경우의 수를 만들기
    // 배열의 0번째는 무조건 몬스터가 들어와야 하고, 고객을 방문할 때는 해당 몬스터를 방문한 후에 고객을 방문할 수 있음
    private static void DFS(int depth, int depthLimit) { // 방문 순서를 조합하는 백트래킹
        if (depth == depthLimit) {
            Coordinates hunter = new Coordinates(0, 0, 0); // 첫 번째 사냥꾼의 시작 위치는 항상 0, 0 거리도 0

            int count = 0;
            for (int i = 0; i < totalCount; i++) {
                int num = orderArr[i];
                hunter = BFS(hunter.x, hunter.y, num, hunter.dist);
                count += hunter.dist;
            }

            copy(); // 배열 다시 원상 복귀
            result = Math.min(result, count);
            return;
        }

        // 음수는 고객, 양수는 몬스터
        for (int i = 0; i < depthLimit; i++) {
            int num = allList.get(i);

            if (num < 0 && !monsterisVisited[(Math.abs(num) - 1)]) { // 고객일 때 요청한 몬스터가 죽지 않았으면 방문할 수 없다.
                continue;
            }

            if (isVisited[i]) continue;
            isVisited[i] = true;

            if (num > 0) { // num이 양수 즉, 몬스터 일 때,
                monsterisVisited[num - 1] = true;
                orderArr[depth] = num;
                DFS(depth + 1, depthLimit);
                monsterisVisited[num - 1] = false;
            } else { // num이 음수 즉, 고객일 때
                orderArr[depth] = num;
                DFS(depth + 1, depthLimit);
            }

            isVisited[i] = false;
        }
    } // End of DFS

    private static Coordinates BFS(int x, int y, int target, int totalDist) {
        Coordinates hunter = new Coordinates(x, y, totalDist);

        if (tempMap[x][y] == target) {
            tempMap[x][y] = 0;
            return hunter;
        }

        boolean[][] isVisited = new boolean[N][N];
        Queue<Coordinates> que = new LinkedList<>();
        isVisited[x][y] = true;
        que.offer(new Coordinates(x, y, 0));

        while (!que.isEmpty()) {
            Coordinates cor = que.poll();

            for (int i = 0; i < 4; i++) {
                int nowX = cor.x + dirX[i];
                int nowY = cor.y + dirY[i];
                int dist = cor.dist;

                if (rangeCheck(nowX, nowY) && !isVisited[nowX][nowY]) {
                    isVisited[nowX][nowY] = true;
                    if (tempMap[nowX][nowY] == target) {
                        tempMap[nowX][nowY] = 0; // 방문한 타겟들은 0처리
                        hunter.dist = dist + 1;
                        hunter.x = nowX;
                        hunter.y = nowY;
                        return hunter;
                    }

                    que.offer(new Coordinates(nowX, nowY, dist + 1));
                }

            }
        }

        return hunter;
    } // End of BFS

    private static boolean rangeCheck(int nowX, int nowY) {
        return nowX >= 0 && nowX < N && nowY >= 0 && nowY < N;
    } // End of rangeCheck

    private static void init() { // 변수 초기화 메소드
        map = new int[N][N];
        tempMap = new int[N][N];
        allList = new ArrayList<>();
        result = Integer.MAX_VALUE / 4; // 오버플로우 방지
    } // End of init

    private static void copy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempMap[i][j] = map[i][j];
            }
        }
    } // End of copy
} // End of Main class 