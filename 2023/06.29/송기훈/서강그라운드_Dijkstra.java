package baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 1. 각 노드에 도달할 수 있는 최소 거리를 다익스트라로 구하기
// 2. 모든 노드에 대해서 조건에 부합하는 총 아이템 구하기

public class 서강그라운드_Dijkstra {

    static class Node implements Comparable<Node> {
        int destination;
        int distance;

        public Node(int destination, int distance) {
            this.destination = destination;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node node) {
            return this.distance - node.distance;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, R;
    static int[] items;
    static int[] distances;
    static ArrayList<ArrayList<Node>> nodes;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 지역
        M = Integer.parseInt(st.nextToken());   // 탐색범위
        R = Integer.parseInt(st.nextToken());   // 길의 수

        st = new StringTokenizer(br.readLine());
        items = new int[N + 1];   // 각 지역의 아이템의 수
        distances = new int[N + 1];
        nodes = new ArrayList<>();
        for (int i = 1; i < N + 1; i++) {
            items[i] = Integer.parseInt(st.nextToken());
            nodes.add(new ArrayList<>());
        }

        // nodes와 items index 맞춰주기 위한 작업
        nodes.add(new ArrayList<>());

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes.get(a).add(new Node(b, c));
            nodes.get(b).add(new Node(a, c));
        }

        int maxValue = Integer.MIN_VALUE;
        int tempSum = 0;
        for (int i = 1; i < N + 1; i++) {
            dijkstra(i);

            for (int j = 1; j < N + 1; j++) {
                int num = distances[j];

                if (num <= M) {
                    tempSum += items[j];
                }
            }

            maxValue = Math.max(maxValue, tempSum);
            tempSum = 0;
        }

        System.out.println(maxValue);
    }

    static void dijkstra(int index) {
        Arrays.fill(distances, Integer.MAX_VALUE);
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(index, 0));

        // 자기 자신으로 가는 경로는 0
        distances[index] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            ArrayList<Node> nodeList = nodes.get(now.destination);

            for (Node node : nodeList) {
                if (distances[node.destination] > distances[now.destination] + node.distance) {
                    distances[node.destination] = distances[now.destination] + node.distance;
                    queue.add(new Node(node.destination, distances[node.destination]));
                }
            }
        }

    }

}
