package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 주간미팅 {

    static class Node implements Comparable<Node> {
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

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;   // 팀원 수
    static int V;   // 장소 수
    static int E;   // 도로 수
    static int A;   // KIST 위치
    static int B;   // 씨알푸드 위치
    static ArrayList<ArrayList<Node>> nodes;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int[] houses = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            houses[i] = Integer.parseInt(st.nextToken());
        }


        // nodes ArrayList 초기화
        nodes = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            nodes.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            nodes.get(a).add(new Node(b, l));
            nodes.get(b).add(new Node(a, l));
        }

        int[] kistDistances = dijkstra(A);
        int[] foodDistances = dijkstra(B);

        int sum = 0;
        for (int i = 1; i < N + 1; i++) {
            int houseLocation = houses[i];
            int kistDistance = kistDistances[houseLocation] == Integer.MAX_VALUE ? -1 : kistDistances[houseLocation];
            int foodDistance = foodDistances[houseLocation] == Integer.MAX_VALUE ? -1 : foodDistances[houseLocation];

            sum += kistDistance + foodDistance;
        }
        System.out.println(sum);
    }

    static int[] dijkstra(int index) {
        int[] distances = new int[V + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(index, 0));

        distances[index] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            ArrayList<Node> nodeList = nodes.get(now.end);

            for (Node node : nodeList) {
                if (distances[node.end] > distances[now.end] + node.distance) {
                    distances[node.end] = distances[now.end] + node.distance;
                    queue.add(new Node(node.end, distances[node.end]));
                }
            }
        }

        return distances;
    }

}
