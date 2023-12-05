package PRG_72413;

import java.util.*;

class Solution {
    static int INF = Integer.MAX_VALUE / 4;
    static int N, A, S, B;
    static List<List<Node>> adjList;

    static class Node implements Comparable<Node> {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        } // End of Node()

        @Override
        public String toString() {
            return "[num : " + num + ", " + "weight : " + weight + "]";
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    } // End of Node class

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int ans = INF;

        input(n, s, a, b, fares);

        // 1. 택시를 타고 간다고 했을 때 가장 싸게 갈 수 있는 출발지를 찾는다.
        for(int i=1; i<=N; i++) {
            if(adjList.get(i).isEmpty()) continue;
            ans = Math.min(ans, dijkstra(i));
        }

        return ans;
    } // End of solution()

    public int dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pQ = new PriorityQueue<>();
        pQ.offer(new Node(start, 0));

        while(!pQ.isEmpty()) {
            Node now = pQ.poll();

            if(dist[now.num] < now.weight) continue;

            for(Node next : adjList.get(now.num)) {
                if(dist[next.num] > dist[now.num] + next.weight ) {
                    dist[next.num] = dist[now.num] + next.weight;
                    pQ.offer(new Node(next.num, dist[next.num]));
                }
            }
        }

        int sum = dist[S] + dist[A] + dist[B];
        return sum;
    } // End of dijkstra()

    public void input(int n, int s, int a, int b, int[][] fares) {
        A = a;
        B = b;
        S = s;
        N = n;

        adjList = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            adjList.add(new ArrayList<>());
        }

        for(int[] t : fares) {
            adjList.get(t[0]).add(new Node(t[1], t[2]));
            adjList.get(t[1]).add(new Node(t[0], t[2]));
        }
    } // End of input()
} // End of Solution class