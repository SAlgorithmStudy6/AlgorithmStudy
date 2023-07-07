import java.io.*;
import java.util.*;

/*문제 풀이에 도움을 주신 송기훈님께 다시 한 번 무한한 감사를 표합니다.*/

public class 총깡총깡_14618 {
    static int N, M, J, K;
    static long ansDist;
    static String ansHome;
    static int[] dist, home;
    static boolean[] vis;
    static ArrayList<ArrayList<Node>> list;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        J = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        dist = new int[N + 1];
        vis = new boolean[N + 1];
        ansDist = -1;
        ansHome = "";
        StringTokenizer stA = new StringTokenizer(br.readLine());
        StringTokenizer stB = new StringTokenizer(br.readLine());
        home = new int[N + 1];

        for (int i = 0; i < K; i++) {
            home[Integer.parseInt(stA.nextToken())] = 1;
            home[Integer.parseInt(stB.nextToken())] = 2;
        }

        list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int Z = Integer.parseInt(st.nextToken());

            list.get(X).add(new Node(Y, Z));
            list.get(Y).add(new Node(X, Z));
        }

        dijkstra(J);
    }

    static void dijkstra(int start) {
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
        findClosestHome();
    }

    static void findClosestHome() {
        int min = Integer.MAX_VALUE;
        for (int homeNum = 1; homeNum <= N; homeNum++) {
            if (homeNum == J) continue;
            if (home[homeNum] == 0) continue;

            if(min != Integer.MAX_VALUE && min == dist[homeNum] && home[homeNum] == 1){
                ansHome = "A";
                ansDist = dist[homeNum];
            } else if(min > dist[homeNum]){
                min = dist[homeNum];
                ansDist = dist[homeNum];
                if(home[homeNum] == 1){
                    //A형 집
                    ansHome = "A";
                } else if(home[homeNum] == 2) {
                    //B형 집
                    ansHome = "B";
                }
            }
        }
        if (ansHome.equals("")) {
            System.out.println(-1);
        } else {
            System.out.println(ansHome + "\n" + ansDist);
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
