import java.io.*;
import java.util.*;

public class Main {
    static class Road implements Comparable<Road> {
        int node;
        int cost;
        List<Integer> route;

        public Road(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        public Road(int node, int cost, List<Integer> route) {
            this.node = node;
            this.cost = cost;
            this.route = route;
        }

        @Override
        public int compareTo(Road o) {
            return this.cost - o.cost;
        }
    }

    static final int INF = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());
        int[] dist = new int[v + 1];
        List<List<Road>> graph = new ArrayList<>();

        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 0; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Road(b, c));
            graph.get(b).add(new Road(a, c));
        }

        boolean isFind = false;
        PriorityQueue<Road> pq = new PriorityQueue<>();
        List<Integer> initRoute = new ArrayList<>();
        initRoute.add(1);

        pq.add(new Road(1, 0, initRoute));

        while (!pq.isEmpty()) {
            Road now = pq.poll();

            if (now.node == v) {
                if (now.route.contains(p)) isFind = true;
            }

            for (Road next : graph.get(now.node)) {
                if (dist[next.node] >= now.cost + next.cost) {
                    List<Integer> nextRoute = new ArrayList<>();
                    nextRoute.addAll(now.route);
                    nextRoute.add(next.node);
                    dist[next.node] = now.cost + next.cost;
                    pq.add(new Road(next.node, dist[next.node], nextRoute));
                }
            }
        }

        if (isFind) bw.write("SAVE HIM");
        else bw.write("GOOD BYE");

        bw.flush();
        bw.close();
    }
}