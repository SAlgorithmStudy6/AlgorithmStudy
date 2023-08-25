import java.io.*;
import java.util.*;

class Edge {
    int a;
    int b;
    int c;

    public Edge(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

public class Main {
    static ArrayList<Edge> graph;
    static final int INF = 1000000000;
    static long[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.add(new Edge(a, b, c));
        }

        if (bellmanFord(n, m)) {
            for (int i = 2; i <= n; i++) {
                if (dist[i] == INF) bw.write("-1\n");
                else bw.write(dist[i] + "\n");
            }
        } else bw.write("-1");

        bw.flush();
        bw.close();
    }

    public static boolean bellmanFord(int n, int m) {
        dist = new long[n + 1];

        Arrays.fill(dist, INF);
        dist[1] = 0;

        // 정점의 개수만큼 반복
        for (int i = 0; i < n; i++) {
            // 간선의 개수만큼 반복
            for (int j = 0; j < m; j++) {
                Edge e = graph.get(j);
                // 최단거리 갱신
                if (dist[e.a] != INF && dist[e.b] > dist[e.a] + e.c) {
                    dist[e.b] = dist[e.a] + e.c;
                }
            }
        }

        // 마지막으로 m개의 간선을 돌 때 변화가 있는지 확인
        for (int i = 0; i < m; i++) {
            Edge e = graph.get(i);

            // 현재 최단거리에서 더 작은 값이 발생하면 음수 사이클
            if (dist[e.a] != INF && dist[e.b] > dist[e.a] + e.c) {
                return false;
            }
        }
        return true;
    }
}