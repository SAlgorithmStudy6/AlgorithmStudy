import java.io.*;
import java.util.*;


class Main{
    static class Node{
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
    static int N;
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static int[] dist;
    static int[] scores;
    static ArrayList<Integer> candidates;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N+1];

        scores = new int[N];
        candidates = new ArrayList<>();
        for(int i=0;i<N+1;i++){
            graph[i] = new ArrayList<>();
        }

        while(true){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if(n1==-1&&n2==-1) break;
            graph[n1].add(new Node(n2,0));
            graph[n2].add(new Node(n1,0));
        }

        for(int i=1;i<=N;i++){
            visited = new boolean[N+1];
            dist = new int[N+1];
            Arrays.fill(dist,Integer.MAX_VALUE);
            scores[i-1]= dijkstra(i);
        }
        int min = Integer.MAX_VALUE;
        for(int i=0;i<N;i++)
            min = Integer.min(min,scores[i]);
        for(int i=0;i<N;i++)
            if(scores[i]==min)candidates.add(i+1);
        System.out.println(min+" "+candidates.size());
        for(int can:candidates) System.out.print(can+" ");
    }

    private static int dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> o1.cost-o2.cost));
        pq.offer(new Node(start,0));
        dist[start] = 0;
        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(!visited[now.v]) visited[now.v] = true;

            for(Node next : graph[now.v]){
                if(!visited[next.v]&&dist[next.v]>dist[now.v]+next.cost){
                    dist[next.v] = now.cost+1;
                    pq.offer(new Node(next.v,dist[next.v]));
                }
            }

        }
        int max = Integer.MIN_VALUE;
        for(int i=1;i<=N;i++) {
            if(dist[i]!=Integer.MAX_VALUE)
                max = Math.max(max,dist[i]);
        }
        return max;
    }
}
