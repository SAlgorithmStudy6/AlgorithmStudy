import java.util.*;

class Test {
    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
        new Solution().solution(n, edge);
    }
}

class Solution {
    class Node implements Comparable<Node> {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static final int INF = 20000;

    public int solution(int n, int[][] edge) {
        int answer = 0;
        int maxDepth = 0;
        List<List<Integer>> graph = new ArrayList<>();
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        Arrays.fill(dist, INF);
        dist[1] = 0;

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edge.length; i++) {
            int s = edge[i][0];
            int e = edge[i][1];

            graph.get(s).add(e);
            graph.get(e).add(s);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (visited[now.idx]) continue;
            visited[now.idx] = true;

            for (int next : graph.get(now.idx)) {
                if (dist[next] > now.cost + 1) {
                    dist[next] = now.cost + 1;
                    maxDepth = Math.max(maxDepth, dist[next]);
                    pq.add(new Node(next, dist[next]));
                }
            }
        }

        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == maxDepth) answer++;
        }

        return answer;
    }
}