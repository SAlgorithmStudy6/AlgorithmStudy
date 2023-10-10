import java.util.*;

class Test {
    public static void main(String[] args) {
        int n = 4;
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        new Solution().solution(n, costs);
    }
}

class Solution {
    class Node implements Comparable<Node> {
        int index;
        int cost;

        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public int solution(int n, int[][] costs) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();

        graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < costs.length; i++) {
            int s = costs[i][0];
            int e = costs[i][1];
            int c = costs[i][2];

            graph.get(s).add(new Node(e, c));
            graph.get(e).add(new Node(s, c));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (visited[now.index]) continue;

            visited[now.index] = true;
            answer += now.cost;

            for (Node next : graph.get(now.index)) {
                if (!visited[next.index]) pq.add(next);
            }
        }

        return answer;
    }
}