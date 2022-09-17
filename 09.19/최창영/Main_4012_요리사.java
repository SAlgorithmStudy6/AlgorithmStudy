import java.util.*;
import java.io.*;

public class Main_4012_요리사 {
    static int N, result;
    static int[][] arr;
    static int[] idxArr;
    static int[] comb;
    static int[] comb2;
    static boolean[] isVisited;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/4012.txt"));
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
                idxArr[i] = i;
                for (int j = 0; j < N; j++) {
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
        if (depth == N / 2) {
            int sum = 0;
            int sum2 = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (isVisited[i] && isVisited[j]) {
                        sum += arr[i][j] + arr[j][i];
                    } else if(!isVisited[i] && !isVisited[j]){
                        sum2 += arr[i][j] + arr[j][i];
                    }
                }
            }

            result = Math.min(result, Math.abs(sum - sum2));
            return;
        }

        for (int i = index; i < N; i++) {
            isVisited[i] = true;
            DFS(depth + 1, i + 1);
            isVisited[i] = false;
        }
    } // End of DFS

    private static void init() {
        result = Integer.MAX_VALUE;
        arr = new int[N][N];
        idxArr = new int[N];
        isVisited = new boolean[N];
        comb = new int[N / 2];
        comb2 = new int[N / 2];
    } // End of init
} // End of Main class