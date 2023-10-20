package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 민준이와_마산_그리고_건우 {

    static class Node implements Comparable<Node> {
        int end, d;

        public Node(int end, int d) {
            this.end = end;
            this.d = d;
        }

        @Override
        public int compareTo(Node node) {
            return this.d - node.d;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int V, E, P;
    static ArrayList<ArrayList<Node>> nodeList;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        nodeList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            nodeList.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            nodeList.get(a).add(new Node(b, d));
            nodeList.get(b).add(new Node(a, d));
        }

        int[] distanceA = dijkstra(1);
        int[] distanceB = dijkstra(P);

        if (distanceA[V] == distanceA[P] + distanceB[V]) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }

    }

    static int[] dijkstra(int index) {
        int[] distances = new int[V+1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[index] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(index, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            ArrayList<Node> nodes = nodeList.get(now.end);

            for (Node node : nodes) {
                if (distances[node.end] > node.d + distances[now.end]) {
                    distances[node.end] = node.d + distances[now.end];
                    pq.offer(new Node(node.end, distances[node.end]));
                }
            }
        }

        return distances;
    }
}
