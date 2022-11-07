import java.util.*;

public class Main_PRG_배달 {
    private static final int INF = Integer.MAX_VALUE;
    static int N, K;
    static int[][] road;
    static List<List<Node>> nodeList;

    static class Node implements Comparable<Node> {
        int nodeNum;
        int weight;

        public Node(int nodeNum, int weight) {
            this.nodeNum = nodeNum;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    } // End of Node class

    public static void main(String[] args) {
        Main_PRG_배달 m = new Main_PRG_배달();

        int N = 6;
        int[][] road = {{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}};
        int K = 4;

        System.out.println(m.solution(N, road, K));
    } // End of main

    // 참고
    // 양방향으로 통행 가능

    private int solution(int N, int[][] road, int K) {
        this.N = N; // 마을의 개수 (노드)
        this.road = road; // 도로 (간선)
        this.K = K; // 음식 배달이 가능한 시간 (제한 사항)
        init();

        // K시간 이내로 받을 수 있는 집. 찾기

        int len = road.length;
        for (int i = 0; i < len; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];

            nodeList.get(a).add(new Node(b, c));
            nodeList.get(b).add(new Node(a, c));
        }

        // 1번 마을에 있는 음식점이 K 이하의 시간에 배달이 가능한 마을의 개수를 return 하면 됩니다.
        return resultCheck(dijkstra(1));
    } // End of solution

    private static int[] dijkstra(int startNodeNum) {
        PriorityQueue<Node> pque = new PriorityQueue<>();
        boolean[] isVisited = new boolean[N + 1];
        int dist[] = new int[N + 1];

        Arrays.fill(dist, INF);
        dist[startNodeNum] = 0;
        pque.offer(new Node(startNodeNum, 0));

        while (!pque.isEmpty()) {
            Node pollNode = pque.poll();

            if (isVisited[pollNode.nodeNum]) continue;
            isVisited[pollNode.nodeNum] = true;

            for (Node node : nodeList.get(pollNode.nodeNum)) {
                if (!isVisited[node.nodeNum] && dist[node.nodeNum] > dist[pollNode.nodeNum] + node.weight) {
                    dist[node.nodeNum] = dist[pollNode.nodeNum] + node.weight;
                    pque.offer(new Node(node.nodeNum, dist[node.nodeNum]));
                }
            }
        }

        return dist;
    } // End of dijkstra

    private static int resultCheck(int[] dist) {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                sum++;
            }
        }

        return sum;
    } // End of resultCheck

    private static void init() {
        nodeList = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            nodeList.add(new ArrayList<>());
        }

    } // End of init
} // End of Main class
