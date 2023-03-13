import java.util.*;
import java.io.*;

// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu


// 성능검사를 통과할 수 있는 약품의 최소 투입 횟수이다. 약품을 투입하지 않고도 성능검사를 통과하는 경우에는 0을 출력한다.
// for (int[] arr : tempArr) System.out.println(Arrays.toString(arr));
public class Main_2112_보호_필름 {
    static int D, W, K, result;
    static int[][] arr;
    static int[] isVisited;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/2112.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append('#').append(t).append(' ');

            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            init();

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            DFS(0, 0);
            sb.append(result).append('\n');

        }

        bw.write(sb.toString());
        bw.close();
    } // End of main

    private static void DFS(int depth, int index) {

        // 약품이 투여되지 않았을 때와 한번이라도 투여됬을 때도 searching시작 하기위해 최소 조건을 줌
        if (depth <= K) {
            // 가지치기 조건.
            // 지금까지 최소 약품투여횟수와 현재 투여된 약품투여횟수(depth)가 같으면 더 이상 searching할 필요가 없음
            if(depth >= result) {
                return;
            }

            if (searching()) {
                int count = 0;
                for (int num : isVisited) {
                    if (num != 0) {
                        count++;
                    }
                }

                result = Math.min(result, count);
            }

            if (depth == K) {
                return;
            }
        }

        for (int i = index; i < D; i++) {
            if (isVisited[i] == 2) continue;
            isVisited[i] = 1; // isVisited가 1이면 1로 채우고, 2이면 0으로 채움.
            DFS(depth + 1, i + 1);
            isVisited[i] = 2;
            DFS(depth + 1, i + 1);
            isVisited[i] = 0;
        }

    } // End of DFS

    private static boolean searching() {
        for (int i = 0; i < W; i++) {
            boolean check = false;
            int zeroCount = 0;
            int oneCount = 0;

            for (int j = 0; j < D; j++) {

                if (isVisited[j] == 0) {
                    if (arr[j][i] == 0) {
                        zeroCount++;
                        oneCount = 0;
                    } else if (arr[j][i] == 1) {
                        oneCount++;
                        zeroCount = 0;
                    }
                } else if (isVisited[j] == 1) {
                    oneCount++;
                    zeroCount = 0;
                } else if (isVisited[j] == 2) {
                    zeroCount++;
                    oneCount = 0;
                }


                // 둘 중 하나라도 K를 찍으면 중지.
                if (zeroCount == K || oneCount == K) {
                    check = true;
                    break;
                }
            }

            if (!check) {
                return false;
            }
        }

        return true;
    } // End of searching

    private static void init() {
        arr = new int[D][W];
        isVisited = new int[D];
        result = K+1;
    } // End of init
} // End of Main class
