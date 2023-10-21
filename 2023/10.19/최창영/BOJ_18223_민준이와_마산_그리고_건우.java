package BOJ_18223;

import java.io.*;
import java.util.*;

public class BOJ_18223_민준이와_마산_그리고_건우 {

    /*
        1번에서 마지막 노드까지
        민준이가 건우를 도와주는 경로의 길이가 최단 경로의 길이보다 길어지지 않는다면,
        민준이는 반드시 건우를 도와주러 간다.
     */

    // https://www.acmicpc.net/problem/18223
    // input
    private static BufferedReader br;

    // variables
    private static final int INF = Integer.MAX_VALUE / 4;
    private static final String saveHim = "SAVE HIM";
    private static final String goodBye = "GOOD BYE";

    private static int V, E, P, startToV, pToV, startToP;
    private static List<List<Node>> adjList;
    private static int[] dists;

    private static class Node implements Comparable<Node> {
        int num;
        int weight;

        private Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }

        @Override
        public String toString() {
            return "{ num : " + num + " weight : " + weight + " }";
        }
    } // End of Node class

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("C:\\Users\\bigyo\\Desktop\\알고리즘\\JavaAlgorithm\\src\\BOJ_18223\\res.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input();

        bw.write(solve());
        bw.close();
    } // End of main()

    private static String solve() {
        StringBuilder sb = new StringBuilder();

        // 최단 거리의 경로를 먼저 구해보기.
        dijkstra(1, V);
        startToV = dists[V];
        startToP = dists[P];

        dijkstra(P, V);
        pToV = dists[V];

        if (startToP + pToV == startToV) {
            sb.append(saveHim);
        } else {
            sb.append(goodBye);
        }
        return sb.toString();
    } // End of solve()

    private static int dijkstra(int start, int target) {
        PriorityQueue<Node> pQ = new PriorityQueue<>();
        pQ.offer(new Node(start, 0));
        dists = new int[V + 1];
        Arrays.fill(dists, INF);
        dists[start] = 0;

        while (!pQ.isEmpty()) {
            Node nowNode = pQ.poll();

            for (Node nextNode : adjList.get(nowNode.num)) {
                if (dists[nextNode.num] > dists[nowNode.num] + nextNode.weight) {
                    dists[nextNode.num] = dists[nowNode.num] + nextNode.weight;
                    pQ.offer(new Node(nextNode.num, dists[nextNode.num]));
                }
            }
        }

        /*
            1 부터 모든 노드까지의 최단 거리를 구하고,
            1에서 P까지의 거리와 P에서 V까지의 거리의 합이
            1에서 V까지의 거리 합과 같은지를 파악하기
            거리를 넘는경우에는, 불가
         */

        return dists[target];
    } // End of dijkstra

    private static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList.get(a).add(new Node(b, c));
            adjList.get(b).add(new Node(a, c));
        }
    } // End of input()
} // End of Main class
