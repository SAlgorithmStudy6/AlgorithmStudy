package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 영만들기_7490 {

    static int N, T;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            dfs(1, 1, 0, 1, "1");
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int depth, int now, int sum, int op, String eq) {
        if (depth == N) {
            sum += (now * op);
            if (sum == 0) {
                sb.append(eq + "\n");
            }
            return;
        }

        dfs(depth + 1, now * 10 + (depth + 1), sum, op, eq+" "+ (depth + 1));
        dfs(depth + 1, depth + 1, sum + (now*op), 1, eq+"+"+ (depth + 1));
        dfs(depth + 1, depth + 1, sum + (now*op), -1, eq+"-"+ (depth + 1));
    }

}