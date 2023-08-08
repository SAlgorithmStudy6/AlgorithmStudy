import java.io.*;
import java.util.*;


class Main{

    static class Node{
        int x;
        int y;
        int cost;

        public Node(int x,int y,int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }


    }
    static int[][] map;
    static boolean[][] visited;
    static int[][] dist;
    static int N;
    static int[] dx={-1,0,1,0};
    static int[] dy={0,1,0,-1};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];
        dist = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dist[i],Integer.MAX_VALUE);
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(dist[N-1][N-1]);
    }
    static void bfs(){
        PriorityQueue<Node> q = new PriorityQueue<>(((o1, o2) -> o1.cost-o2.cost));
        q.offer(new Node(0,0,0));
        dist[0][0] = 0;
        while(!q.isEmpty()){
            Node now = q.poll();

            visited[now.x][now.y] = true;
            for(int i=0;i<4;i++){
                int nx = now.x+dx[i];
                int ny = now.y+dy[i];
                if(isRange(nx,ny)){
                    int calc = Math.max(now.cost,Math.abs(map[nx][ny]-map[now.x][now.y]));
                    if(!visited[nx][ny]&&dist[nx][ny]>calc){
                        dist[nx][ny] =calc ;
                        q.offer(new Node(nx,ny,calc));
                    }
                }
            }

        }
    }
    static boolean isRange(int x,int y){
        if(x>=0&&y>=0&&x<N&&y<N) return true;
        else return false;
    }

}
