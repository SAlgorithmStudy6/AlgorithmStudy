package baekjoon.silver.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 도영이가만든맛있는음식 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static boolean[] visited;
    static int[] sourness;
    static int[] bitterness;
    static int minValue;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        sourness = new int[N];
        bitterness = new int[N];
        minValue = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            sourness[i] = Integer.parseInt(st.nextToken());
            bitterness[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        System.out.println(minValue);
    }

    public static void dfs(int depth) {
        if (depth == N) {
            int sour = 1;
            int bitter = 0;
            int count = 0;
            for (int i = 0; i < N; i++) {
                if (visited[i]) {
                    sour *= sourness[i];
                    bitter += bitterness[i];
                    count += 1;
                }
            }

            // 공집합
            if (count == 0) {
                return;
            }
            int value = Math.abs(sour - bitter);
            if (minValue > value) {
                minValue = value;
            }
            return;
        }

        // 원소를 포함하고 dfs, 포함하지 않고 dfs
        visited[depth] = true;
        dfs(depth + 1);
        visited[depth] = false;
        dfs(depth + 1);

    }

}
