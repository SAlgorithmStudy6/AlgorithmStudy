package baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://dingdingmin-back-end-developer.tistory.com/entry/%EB%B0%B1%EC%A4%80-14938-%EC%9E%90%EB%B0%94-java-%EC%84%9C%EA%B0%95%EA%B7%B8%EB%9D%BC%EC%9A%B4%EB%93%9C

public class 서강그라운드_BFS {

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
    static ArrayList<ArrayList<Node>> nodes;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 지역
        M = Integer.parseInt(st.nextToken());   // 탐색범위
        R = Integer.parseInt(st.nextToken());   // 길의 수

        st = new StringTokenizer(br.readLine());
        items = new int[N+1];   // 각 지역의 아이템의 수
        nodes = new ArrayList<>();
        for (int i = 1; i < N+1; i++) {
            items[i] = Integer.parseInt(st.nextToken());
            nodes.add(new ArrayList<>());
        }

        // nodes와 items index 맞춰주기 위한 작업
        nodes.add(new ArrayList<>());

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes.get(a).add(new Node(b, c));
            nodes.get(b).add(new Node(a, c));
        }

        int maxValue = Integer.MIN_VALUE;
        for (int i = 1; i < N+1; i++) {
            maxValue = Math.max(bfs(i), maxValue);
        }

        System.out.println(maxValue);
    }

    static int bfs(int index) {
        // 거리가 짧은 순으로 정렬
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[] visited= new boolean[N+1];
        int result = 0;

        queue.add(new Node(index, 0));
        while (!queue.isEmpty()) {
            Node now = queue.poll();

            // 이미 최단 거리로 방문한 노드를 거른다 (PQ 이슈)
            if (visited[now.destination]) continue;

            visited[now.destination] = true;
            result += items[now.destination];

            ArrayList<Node> nodeList = nodes.get(now.destination);

            for (Node node: nodeList) {
                if (!visited[node.destination] && (now.distance + node.distance) <= M) {
                    queue.add(new Node(node.destination, now.distance + node.distance));
                }
            }
        }

        return result;
    }

}