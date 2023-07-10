package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 트리의지름 {

    static class Node {
        int point;
        int distance;

        public Node(int point, int distance) {
            this.point = point;
            this.distance = distance;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static ArrayList<ArrayList<Node>> connections;
    static boolean[] visited;
    static int maxValue, maxIndex;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        connections = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            connections.add(new ArrayList<Node>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            connections.get(parent).add(new Node(child, distance));
            connections.get(child).add(new Node(parent, distance));
        }

        maxValue = Integer.MIN_VALUE;
        maxIndex = -1;
        // 어떤 경로든 1을 거쳐가야 하므로
        // 1에서 제일 먼 곳 탐색하면 답을 구할 수 있다.
        visited = new boolean[N + 1];
        visited[1] = true;
        dfs(1, 0);

        // maxIndex에서 제일 먼 곳 탐색
        maxValue = Integer.MIN_VALUE;
        visited = new boolean[N + 1];
        visited[maxIndex] = true;
        dfs(maxIndex, 0);

        System.out.println(maxValue);

    }


    static void dfs(int index, int distance) {
        if (maxValue < distance) {
            maxValue = distance;
            maxIndex = index;
        }

        ArrayList<Node> nodeList = connections.get(index);

        for (Node node : nodeList) {
            if (!visited[node.point]) {
                visited[node.point] = true;
                dfs(node.point, distance + node.distance);
            }
        }

    }

}
