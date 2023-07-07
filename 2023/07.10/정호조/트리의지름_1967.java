import java.io.*;
import java.util.*;

/**
 * 두 노드 사이의 경로는 하나이기 때문에 트리의 특성상 각 노드에서 가장 먼 거리에 위치한 노드는 
 * 두 노드 사이의 거리가 가장 먼 노드 중 한 개를 반드시 포함하고 있음.
 * 따라서 각 노드로부터 모든 노드까지의 거리를 구할 필요가 없고(메모리 초과 발생)
 * 임의의 한 노드로부터 가장 먼 노드를 찾은 후, 그 노드로 부터 가장 먼 노드까지의 거리를 구하면 정답
 */

public class 트리의지름_1967 {
    static int n, ans;
    static ArrayList<ArrayList<Node>> list;
    static int[] dist;
    static boolean[] vis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        dist = new int[n + 1];
        vis = new boolean[n + 1];
        list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list.get(start).add(new Node(end, dist));
            list.get(end).add(new Node(start, dist));
        }

        int farthestNodeNum = dijkstra(1, true);
        ans = dijkstra(farthestNodeNum, false);

        System.out.println(ans);
    }

    static int dijkstra(int start, boolean findNodeNum) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(vis, false);
        dist[start] = 0;
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int curNodeNum = curNode.nodeNum;

            if (!vis[curNodeNum]) {
                vis[curNodeNum] = true;
                for (Node nextNode : list.get(curNodeNum)) {
                    if (!vis[nextNode.nodeNum] && dist[nextNode.nodeNum] > dist[curNodeNum] + nextNode.distance) {
                        dist[nextNode.nodeNum] = dist[curNodeNum] + nextNode.distance;
                        pq.add(new Node(nextNode.nodeNum, dist[nextNode.nodeNum]));
                    }
                }
            }
        }

        int maxLen = 0;
        int nodeNum = 0;
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] != Integer.MAX_VALUE && maxLen < dist[i]) {
                maxLen = dist[i];
                nodeNum = i;
            }
        }
        if (findNodeNum) {
            return nodeNum;
        } else {
            return maxLen;
        }
    }

    static class Node {
        int nodeNum, distance;

        public Node(int nodeNum, int distance) {
            this.nodeNum = nodeNum;
            this.distance = distance;
        }
    }
}
