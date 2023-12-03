package programmers.lv3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// class Test {
//     public static void main(String[] args) {
//         int n = 6;
//         int s = 4;
//         int a = 6;
//         int b = 2;
//         int[][] fares = {
//                 {4, 1, 10},
//                 {3, 5, 24},
//                 {5, 6, 2},
//                 {3, 1, 41},
//                 {5, 1, 24},
//                 {4, 6, 50},
//                 {2, 4, 66},
//                 {2, 3, 22},
//                 {1, 6, 25}
//         };

//         System.out.println(new 합승_택시_요금().solution(n, s, a, b, fares));
//     }
// }

public class 합승_택시_요금 {

    ArrayList<ArrayList<Node>> nodeList;
    int[] dpA, dpB, dpS;
    int maxValue = 200 * 100000 + 1;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = maxValue;
        nodeList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            nodeList.add(new ArrayList<>());
        }

        for (int i = 0; i < fares.length; i++) {
            int nodeA = fares[i][0];
            int nodeB = fares[i][1];
            int price = fares[i][2];

            nodeList.get(nodeA).add(new Node(nodeB, price));
            nodeList.get(nodeB).add(new Node(nodeA, price));
        }

        dpA = new int[n+1];
        dpB = new int[n+1];
        dpS = new int[n+1];

        Arrays.fill(dpA, maxValue);
        Arrays.fill(dpB, maxValue);
        Arrays.fill(dpS, maxValue);

        dijksktra(a, dpA);
        dijksktra(b, dpB);
        dijksktra(s, dpS);

        for (int i = 1; i <= n; i++) {
            int price = dpA[i] + dpB[i] + dpS[i];
            answer = Math.min(answer, price);
        }

        return answer;
    }

    public int[] dijksktra(int start, int[] dp) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        dp[start] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            ArrayList<Node> nodes = nodeList.get(now.end);

            for (Node node : nodes) {
                int nPrice = node.price + dp[now.end];

                if (nPrice < dp[node.end]) {
                    dp[node.end] = nPrice;
                    queue.offer(new Node(node.end, nPrice));
                }
            }
        }

        return dp;
    }

    class Node implements Comparable<Node> {

        int end, price;

        public Node(int end, int price) {
            this.end = end;
            this.price = price;
        }

        @Override
        public String toString() {
            return "end: " + end + " price: " + price;
        }

        @Override
        public int compareTo(Node n) {
            return this.price - n.price;
        }
    }
}
