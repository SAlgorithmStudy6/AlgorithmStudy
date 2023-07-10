package baekjoon.gold.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 총깡총깡 {

    static class Node implements Comparable<Node> {
        int point;
        int distance;

        public Node(int point, int distance) {
            this.point = point;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node node) {
            return this.distance - node.distance;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N, M, J, K;
    static int[] houseType;
    static ArrayList<ArrayList<Node>> nodeList;
    static int[] distances;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        J = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        houseType = new int[N + 1];
        StringTokenizer stA = new StringTokenizer(br.readLine());
        StringTokenizer stB = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int indexA = Integer.parseInt(stA.nextToken());
            int indexB = Integer.parseInt(stB.nextToken());
            houseType[indexA] = 1;
            houseType[indexB] = 2;
        }

        nodeList = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            nodeList.add(new ArrayList<Node>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            nodeList.get(x).add(new Node(y, z));
            nodeList.get(y).add(new Node(x, z));
        }

        distances = new int[N + 1];
        dijkstra(J);

        // 최솟값 찾기
        int minValue = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 1; i < N + 1; i++) {
            // 진서 집 넘기기
            if (i == J) continue;

            // 아무것도 아닌 집도 넘기기
            if (houseType[i] == 0) continue;

            if (minValue == distances[i]) {
                // 최솟값과 같은데 A형이면 minIndex 교체
                if (houseType[i] == 1) {
                    minIndex = i;
                }
            } else if (minValue > distances[i]) {
                minValue = distances[i];
                minIndex = i;
            }
        }

        // 결과값 출력
        if (minValue == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            if (houseType[minIndex] == 1) {
                System.out.println("A");
            } else {
                System.out.println("B");
            }
            System.out.println(minValue);
        }

    }

    static void dijkstra(int index) {
        Arrays.fill(distances, Integer.MAX_VALUE);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(index, 0));

        distances[index] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            ArrayList<Node> nodes = nodeList.get(now.point);

            for (Node node : nodes) {
                if (distances[node.point] > distances[now.point] + node.distance) {
                    distances[node.point] = distances[now.point] + node.distance;
                    queue.add(new Node(node.point, distances[node.point]));
                }
            }
        }
    }

}
