import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/11559
// 상대방의 필드가 주어졌을 때, 연쇄가 몇 번 연속으로 일어날지 계산
// 포인트 -> 터질 수 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고 여러 그룹이 터지더라도 한번의 연쇄가 추가된다.

public class Main_11559_Puyo_Puyo {
    private static final int N = 12;
    private static final int M = 6;
    static char[][] map = new char[N][M];
    static char[][] tempMap;
    static boolean[][] isVisited;

    static int[] dirX = {-1, 1, 0, 0};
    static int[] dirY = {0, 0, -1, 1};
    static List<Coordinates> popColorCoorList;

    static class Coordinates {
        int x;
        int y;
        int count;

        public Coordinates(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    } // End of Coordinates


    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/11559.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        popColorCoorList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String temp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = temp.charAt(j);
            }
        }

        int result = 0; // 최종 결과값
        for (; ; ) {
            // 터트릴 수 있는 것들을 한번에 터트려야 하므로, 터지는 곳의 좌표를 모두 저장.
            popColorCoorList = new ArrayList<>();
            isVisited = new boolean[N][M];

            for (int i = N - 1; i >= 0; i--) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != '.' && !isVisited[i][j]) {
                        findColor(i, j, map[i][j]);
                    }
                }
            }

            // list에 들어가있는 블럭들을 터트리는 작업 진행
            if (popColorCoorList.isEmpty()) {
                break;
            } else {
                result++;
                popColor(); // 색깔 터트리기
                gravityApplication(); // 중력으로 아래로 당기기
                copy(); // 다시 배열 복사해주기.
            }
        }

        System.out.print(result);
    } // End of main

    // 1. 현재 주어진 상황에서 어떤 색깔이 터질 수 있는지를 체크,
    // 2. 터지고 나면 뿌요들을 내리는 작업을 시작. (내릴 때는 열을 기준으로 내린다.) -> 배열 복사
    private static boolean findColor(int x, int y, char color) {
        Queue<Coordinates> que = new LinkedList<>();
        List<Coordinates> tempList = new ArrayList<>();

        que.offer(new Coordinates(x, y, 0));
        tempList.add(new Coordinates(x, y));
        isVisited[x][y] = true;

        int totalCount = 1;
        while (!que.isEmpty()) {
            Coordinates pollCoor = que.poll();

            for (int i = 0; i < 4; i++) {
                int nowX = dirX[i] + pollCoor.x;
                int nowY = dirY[i] + pollCoor.y;
                int nowCount = pollCoor.count;

                if (!rangeCheck(nowX, nowY) || isVisited[nowX][nowY] || map[nowX][nowY] != color) continue;

                que.offer(new Coordinates(nowX, nowY, ++nowCount));
                tempList.add(new Coordinates(nowX, nowY));
                isVisited[nowX][nowY] = true;
                totalCount++;
            }
        }

        if (totalCount >= 4) {
            // tempList에 저장해뒀다가 전체 터트릴 수 있는 색깔의 블럭이 4개이상일 경우에만 list에 저장함.
            for (Coordinates coor : tempList) {
                popColorCoorList.add(new Coordinates(coor.x, coor.y));
            }

            return true;
        }

        return false;
    } // End of findColor

    private static void popColor() {
        // 터트릴 수 있는 색깔들 터트리고 .으로 변경

        for (Coordinates coor : popColorCoorList) {
            map[coor.x][coor.y] = '.';
        }
    } // End of popColor

    private static void gravityApplication() {
        tempMap = new char[N][M];

        for (int i = 0; i < M; i++) {
            // 빈 공간이 있을 경우, 밑으로 내려줌
            Deque<Character> deque = new LinkedList<>();
            for (int j = N - 1; j >= 0; j--) {

                if (map[j][i] != '.') {
                    deque.offerFirst(map[j][i]);
                }
            }

            int index = N - 1;
            while (!deque.isEmpty()) {
                char ch = deque.pollLast();
                tempMap[index--][i] = ch;
            }

            for (int j = index; j >= 0; j--) {
                tempMap[j][i] = '.';
            }
        }

    } // End of gravityApplication

    private static void copy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = tempMap[i][j];
            }
        }
    } // End of copy

    private static boolean rangeCheck(int nowX, int nowY) {
        return nowX >= 0 && nowX < N && nowY >= 0 && nowY < M;
    } // End of rangeCheck
} // End of Main class
