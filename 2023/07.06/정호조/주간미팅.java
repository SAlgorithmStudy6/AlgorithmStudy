import java.io.*;
import java.util.*;

/**
 * 기본적인 다익스트라 문제
 * 집의 수만큼 다익스트라를 돌려서 집에서부터 KIST와 씨알푸드까지의 최소 거리를 구한 후 ans에 더해줌
 * */
public class 주간미팅_12834 {
    static int N, V, E, ans, KIST, CR;
    static int[] home, dist;
    static boolean[] vis;
    static ArrayList<ArrayList<Node>> roadInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        home = new int[N];
        dist = new int[V + 1];
        vis = new boolean[V + 1];

        st = new StringTokenizer(br.readLine());
        KIST = Integer.parseInt(st.nextToken());
        CR = Integer.parseInt(st.nextToken());
        ans = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(st.nextToken());
        }

        roadInfo = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            roadInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            roadInfo.get(start).add(new Node(end, distance));
            roadInfo.get(end).add(new Node(start, distance));
        }

        for (int i = 0; i < N; i++) {
            dijkstra(home[i]);
        }

        System.out.println(ans);
    }

    static void dijkstra(int start) {
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(vis, false);

        PriorityQueue<Node> queue = new PriorityQueue<>(((o1, o2) -> o1.distance - o2.distance));
        queue.offer(new Node(start, 0));
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            int curNodeNum = curNode.nodeNum;

            if (!vis[curNodeNum]) {
                vis[curNodeNum] = true;

                for (Node nextNode : roadInfo.get(curNodeNum)) {
                    if (!vis[nextNode.nodeNum] && dist[nextNode.nodeNum] > dist[curNodeNum] + nextNode.distance) {
                        dist[nextNode.nodeNum] = dist[curNodeNum] + nextNode.distance;
                        queue.add(new Node(nextNode.nodeNum, dist[nextNode.nodeNum]));
                    }
                }
            }
        }
        ans += getDistance();
    }

    static int getDistance() {
        int distKIST = dist[KIST] != Integer.MAX_VALUE ? dist[KIST] : -1;
        int distCR = dist[CR] != Integer.MAX_VALUE ? dist[CR] : -1;

        return distKIST + distCR;
    }

    static class Node {
        int nodeNum, distance;

        public Node(int nodeNum, int distance) {
            this.nodeNum = nodeNum;
            this.distance = distance;
        }
    }
}
