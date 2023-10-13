package programmers.lv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

//class Test {
//    public static void main(String[] args) {
//        int n = 4;
//        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
//
//        new 섬_연결하기().solution(n, costs);
//    }
//}

public class 섬_연결하기 {

    class Node implements Comparable<Node> {
        int end, distance;

        public Node(int end, int distance) {
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }

    int N;
    ArrayList<ArrayList<Node>> nodes;

    public int solution(int n, int[][] costs) {
        int answer = Integer.MAX_VALUE;
        N = n;
        nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int[] c : costs) {
            nodes.get(c[0]).add(new Node(c[1], c[2]));
            nodes.get(c[1]).add(new Node(c[0], c[2]));
        }

        for (int i = 0; i < n; i++) {
            int[] distance = dijkstra(i);

            int temp = 0;
            for (int d : distance) {
                if (d != Integer.MAX_VALUE) {
                    temp += d;
                }
            }

            answer = Math.min(answer, temp);
        }

        return answer;
    }

    public int[] dijkstra(int index) {
        int[] distances = new int[N];
        boolean[] visited = new boolean[N];
        Arrays.fill(distances, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(index, 0));

        distances[index] = 0;
        visited[index] = true;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            visited[now.end] = true;

            ArrayList<Node> nodeList = nodes.get(now.end);

            for (Node node : nodeList) {
                if (!visited[node.end] && distances[node.end] > node.distance) {
                    distances[node.end] = node.distance;
                    pq.add(new Node(node.end, distances[node.end]));
                }
            }
        }

        return distances;
    }
}
