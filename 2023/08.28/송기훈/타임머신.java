package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 타임머신 {

    static class Node {
        int start;
        int end;
        int weight;

        public Node(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static int N, M;
    static Node[] nodes;
    static long[] distances;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodes = new Node[M];
        distances = new long[N + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(a, b, c);
        }

        if (bellmanFord()) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                if (distances[i] == Integer.MAX_VALUE) {
                    System.out.println(-1);
                } else {
                    System.out.println(distances[i]);
                }
            }
        }
    }

    static boolean bellmanFord() {
        distances[1] = 0; // 시작점은 0

        for (int i = 1; i <= N; i++) {

            for (int j = 0; j < M; j++) {
                int start = nodes[j].start;
                int end = nodes[j].end;
                int weight = nodes[j].weight;

                if (distances[start] == Integer.MAX_VALUE) continue;

                if (distances[end] > (distances[start] + weight)) {
                    distances[end] = distances[start] + weight;

                    // N번째에서 값이 갱신된다면 음수 순환 존재
                    if (i == N) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

}
