

import java.io.*;
import java.util.*;


class Main{

    static class Node{
        int v;
        int w;
        int cost;

        public Node(int v, int w, int cost) {
            this.v = v;
            this.w = w;
            this.cost = cost;
        }
    }
    static ArrayList<Node> graph;
    static int N,M;
    static final int INF = 500 * 10000;
    static long[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new long[N+1];
        Arrays.fill(dist,INF);
        graph = new ArrayList<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.add(new Node(start,end,cost));

        }
        if(BellmanFord(1)){
            for(int i=1;i<=N;i++){
                if(i==1) continue;
                if(dist[i]==INF) System.out.println(-1);
                else System.out.println(dist[i]);
            }
        }else{
            System.out.println(-1);
        }
    }
    public static boolean BellmanFord(int start){
        dist[start] = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                Node now = graph.get(j);
                if(dist[now.v]!=INF&&dist[now.w]>dist[now.v]+now.cost){
                    dist[now.w] = dist[now.v]+now.cost;
                }

            }
        }
        for(int i=0;i<M;i++){
            Node now = graph.get(i);
            if(dist[now.v]!=INF&&dist[now.w]>dist[now.v]+now.cost){
                return false;
            }
        }
        return true;
    }
}
