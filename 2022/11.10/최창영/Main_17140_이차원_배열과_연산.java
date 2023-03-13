import java.io.*;
import java.util.*;

public class Main_17140_이차원_배열과_연산 {
    private static final int N = 101;
    static int R, C, K, xLen, yLen;
    static int[][] map = new int[N][N];
    static int[][] tempMap;

    static class Coordinates {
        int x;
        int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    } // End of Coordinates class

    static class Number implements Comparable<Number> {
        int num;
        int freq;

        public Number(int num, int freq) {
            this.num = num;
            this.freq = freq;
        }

        @Override
        public int compareTo(Number o) {

            if (freq > o.freq) {
                return 1;
            } else if (freq == o.freq) {
                return num - o.num;
            } else {
                return -1;
            }
        }
    } // End of Number class

    //  100초가 지나도 A[r][c] = k가 되지 않으면 -1을 출력한다.
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/17140.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        xLen = 3;
        yLen = 3;

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution());
    } // End of main

    // 행 또는 열의 크기가 커진 곳에는 0이 채워진다. 수를 정렬할 때 0은 무시해야 한다
    // 행과 열의 위치를 파악할 때는 0,0을 1,1로 해야된다.
    // 0이 아닌 것의 개수
    private static int solution() {

        for (int t = 0; t < 101; t++) {
            if (map[R - 1][C - 1] == K) {
                // 가장 먼저 해당 위치에 K값이 일치하는지 체크.
                // 일치한다면 곧 바로 t를 return
                return t;
            }

            // 행과 열의 수를 알아내기.
            Coordinates cor = rowLine_ColumnLine_Check();

            // 행과 열의 수에 따른 정렬 실행
            if (cor.x <= cor.y) {
                // R연산 (행을 기준으로 정렬)
                calcR();
            } else {
                // C연산 (열을 기준으로 정렬)
                calcC();
            }
        }

        return -1;
    }

    // 행,열 수 체크
    private static Coordinates rowLine_ColumnLine_Check() {
        // 가장 큰 행을 기준으로 모든 행의 크기가 변하고,
        // 가장 큰 열을 기준으로 모든 열의 크기가 변한다.

        int maxRow = -1;
        int maxCol = -1;
        for (int i = 0; i < N; i++) {
            int rowLen = 0;
            int colLen = 0;
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0) {
                    rowLen++;
                }

                if (map[j][i] != 0) {
                    colLen++;
                }
            }

            maxRow = Math.max(maxRow, rowLen);
            maxCol = Math.max(maxCol, colLen);
        }

        return new Coordinates(maxRow, maxCol);
    } // End of rowLine_ColumnLine_Check

    // R연산 (행을 기준으로 정렬)
    private static void calcR() {
        tempMap = new int[N][N];
        int maxCol = -1;

        for (int i = 0; i < N; i++) {
            HashMap<Integer, Integer> rowHashMap = new HashMap<>();
            PriorityQueue<Number> pque = new PriorityQueue<>();

            for (int j = 0; j < N; j++) {
                int num = map[i][j];
                if (num == 0) continue;

                rowHashMap.put(num, rowHashMap.getOrDefault(num, 0) + 1);
            }

            // 횟수가 적은 순으로 나열 해야됨.
            // key값을 기준으로 정렬.
            Iterator<Map.Entry<Integer, Integer>> iter = rowHashMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<Integer, Integer> entrySet = iter.next();
                int key = entrySet.getKey();
                int value = entrySet.getValue();

                pque.offer(new Number(key, value));
            }

            int index = -1;
            while (!pque.isEmpty()) {
                Number num = pque.poll();

                tempMap[i][++index] = num.num;
                if (index >= 100) break;

                tempMap[i][++index] = num.freq;
                if (index >= 100) break;
            }


            maxCol = Math.max(index, maxCol);
        }

        copy();
    } // End of calcR

    // C연산 (열을 기준으로 정렬)
    private static void calcC() {
        tempMap = new int[N][N];
        int maxRow = -1;

        for (int i = 0; i < N; i++) {
            HashMap<Integer, Integer> colHashMap = new HashMap<>();
            PriorityQueue<Number> pque = new PriorityQueue<>();

            for (int j = 0; j < N; j++) {
                int num = map[j][i];
                if (num == 0) continue;

                colHashMap.put(num, colHashMap.getOrDefault(num, 0) + 1);
            }

            // 횟수가 적은 순으로 나열 해야됨.
            // key값을 기준으로 정렬.
            Iterator<Map.Entry<Integer, Integer>> iter = colHashMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<Integer, Integer> entrySet = (Map.Entry<Integer, Integer>) iter.next();
                int key = entrySet.getKey();
                int value = entrySet.getValue();

                pque.offer(new Number(key, value));
            }

            int index = -1;
            while (!pque.isEmpty()) {
                Number num = pque.poll();

                tempMap[++index][i] = num.num;
                if (index >= 100) break;
                tempMap[++index][i] = num.freq;
                if (index >= 100) break;
            }


            maxRow = Math.max(index, maxRow);
        }

        copy();
    } // End of calcC

    private static void copy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = tempMap[i][j];
            }
        }
    } // End of copy
} // End of Main class