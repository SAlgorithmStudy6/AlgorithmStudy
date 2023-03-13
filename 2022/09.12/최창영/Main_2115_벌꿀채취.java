import java.util.*;
import java.io.*;

public class Main_2115_벌꿀채취 {
    static int N, M, C, result;

    static int[][] arr;
    static boolean[][] isVisited;
    static int[] beekeeper;
    static int beekeeper1Max;
    static Deque<Integer> deque;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/2115.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append('#').append(t).append(' ');

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 벌통의 크기
            M = Integer.parseInt(st.nextToken()); // 벌통의 개수
            C = Integer.parseInt(st.nextToken()); // 채취할 수 있는 꿀의 최대양
            init();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 선택된 M개의 벌통에서 값을 조합
            // 조합된 수가 C를 넘지 않도록하기.
            // 부분 조합 만들기 <- 완전탐색
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {

                    // 첫번째 양봉업자 조합 만들기.
                    int index = 0;
                    for (int k = j; k < j + M; k++) {
                        beekeeper[index] = arr[i][k];
                        isVisited[i][k] = true;
                        index++;
                    }

                    // beekeeper1의 조합으로 최댓값을 찾기.
                    DFS(0, 1, 0);

                    // 두번째 양봉 업자 조합 만들기.
                    madeBekeeper(i);
                }
            }

            sb.append(result).append('\n');
        }

        bw.write(sb.toString());
        bw.close();
    } // End of main

    private static void madeBekeeper(int iIndex) { // 양봉업자1이 만든 벌통을 제외하고 양봉업자 2의 벌통을 만드는 메소드
        for (int i = iIndex; i < N; i++) {
            for (int j = 0; j <= N - M; j++) {
                // 첫번째 양봉업자가 만든 조합은 지나치도록 isVisited를 사용
                if (isVisited[i][j]) {
                    continue;
                }

                // 두번째 양봉업자 조합 만들기.
                int index = 0;
                for (int k = j; k < j + M; k++) {
                    beekeeper[index] = arr[i][k];
                    index++;
                }

                // 만들어진 벌꿀 통에 대해서 다시 조합을 계산해서 최댓값을 만들기.
                DFS(0, 2, 0);
            }
        }

    } // End of madeBekeeper

    private static void DFS(int depth, int beekeeperNum, int index) {

        if (depth <= M) {
            int sum = 0;
            int total = 0;

            for (int num : deque) {
                sum += num;
                total += Math.pow(num, 2);
            }

            // 합이 C를 넘으면 안됨.
            if (sum <= C) {
                if (beekeeperNum == 1) { // 양봉 업자1로 만든 조합에서 최고값을 찾기.
                    beekeeper1Max = Math.max(beekeeper1Max, total);
                } else { // 양봉업자 1이 만든 최고값과 양봉업자2가 만든조합의 합과 최고값 비교.
                    result = Math.max(beekeeper1Max + total, result);
                }
            }

            if (depth == M) {
                return;
            }
        }

        // 덱을 사용해서 조합을 만듬.
        for (int i = index; i < M; i++) {
            deque.offerLast(beekeeper[i]);
            DFS(depth + 1, beekeeperNum, i + 1);
            deque.pollLast();
        }
    } // End of DFS

    private static void init() {
        result = Integer.MIN_VALUE;
        arr = new int[N][N];
        isVisited = new boolean[N][N];
        beekeeper = new int[M];
        deque = new LinkedList<>();
        beekeeper1Max = Integer.MIN_VALUE;
    } // End of init
} // End of Main class