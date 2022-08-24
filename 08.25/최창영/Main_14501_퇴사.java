import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/14501
public class Main_14501_퇴사 {
    static int[][] arr;
    static int N;
    static int max = -1;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/14501.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        DP(0, 0);
        System.out.println(max);
    } // End of main

    private static void DP(int depth, int total) {
        if (depth == N) {
            max = Math.max(max, total);
            return;
        } else if (depth > N) {
            return;
        }

        DP(depth + 1, total);
        DP(depth + arr[depth][0], total + arr[depth][1]);
    } // End of DP
} // End of Main class