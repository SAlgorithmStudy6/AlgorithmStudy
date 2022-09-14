import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class swea2117 {
    public static class position{
        int x,y;
        position(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
    static int N,M,ans;
    static int[][] arr;
    static boolean[][]visited;
    static int[] dx= {-1,1,0,0};
    static int[] dy= {0,0,-1,1};
    static Queue<position> q;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan=new Scanner(System.in);
        int T=scan.nextInt();
        for(int o=1;o<=T;o++) {
            ans=0;
            N=scan.nextInt();
            M=scan.nextInt();
            arr=new int[N][N];
            q=new LinkedList<>();
            visited=new boolean[N][N];
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    arr[i][j]=scan.nextInt();
                }
            }
            
            for(int i=0;i<N;i++) {
                for(int j=0;j<N;j++) {
                    
                    q.clear();
                    for(int a=0;a<N;a++)Arrays.fill(visited[a], false);
                    bfs(i,j);
                }
            }
            System.out.println("#"+o+" "+ ans);  
        }
           

    }
    public static void bfs(int x,int y) {
        q.offer(new position(x,y));
        visited[x][y]=true;
        // 1일 때 초기화
        int k=1;
        int house=0;
        if(arr[x][y]==1) {
            house=1;
        }
        int cost=k*k+(k-1)*(k-1);
        if(cost<=house*M) {
            if(k>ans) {
                ans=k;
            }
        }
       
        while(!q.isEmpty()) {
            int size=q.size();
            k++;
            for(int i=0;i<size;i++) {
                position next=q.poll();
                for(int a=0;a<4;a++) {
                    int nx=next.x+dx[a];
                    int ny=next.y+dy[a];
                    
                    if(nx>=0&&nx<N&ny>=0&&ny<N&&!visited[nx][ny]) {
                     
                            if(arr[nx][ny]==1) house++;
                            visited[nx][ny]=true;
                            q.offer(new position(nx,ny));
                      
                    }
                }      
            }
            int cost2=k*k+(k-1)*(k-1);
            if(cost2<=house*M) {
                if(house>ans) {
                    ans=house;
                }
            }
        }

    }

}
