package baekjoon.silver.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 상근이의여행 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N, M;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());   // 국가의 수
            M = Integer.parseInt(st.nextToken());   // 비행기의 종류

            int[][] graph = new int[N+1][N+1];
            boolean[] visited = new boolean[N+1];

            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                graph[a][b] = 1;
                graph[b][a] = 1;
            }

            int result = bfs(graph, visited) - 1;
            System.out.println(result);
        }

    }

    static int bfs(int[][] graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        int result = 0;
        while (!queue.isEmpty()) {
            result += 1;
            int node = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (!visited[i] && graph[node][i] == 1) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        return result;
    }
}

/*
연결 그래프를 만족한다 -> 신장 트리를 만들 수 있다 -> 최소 신장 트리의 간선은 (노드 수 - 1)
 */
