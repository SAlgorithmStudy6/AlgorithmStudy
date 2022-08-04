import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int n,m,k,cnt,ans=0;
    static int[] dx= {1,-1,0,0};
    static int[] dy= {0,0,-1,1};
    static boolean[][] visited;
    static int[][] list;
    static int max=Integer.MIN_VALUE;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan=new Scanner(System.in);
        n=scan.nextInt();
        m=scan.nextInt();
        k=scan.nextInt();
        
        list=new int[n+1][m+1];
        visited=new boolean[n+1][m+1];
        for(int i=0;i<k;i++) {    
           int a=scan.nextInt();
           int b=scan.nextInt();
           list[a][b]=1;
     
        }
        for(int i=1;i<n+1;i++) {
            for(int j=1;j<m+1;j++){
            if(visited[i][j]==false&&list[i][j]==1) {
                cnt=0;
                dfs(i,j);
                if(cnt>max) {
                    max=cnt;
                }
            }
           }  
        }
       System.out.println(max);
    }
    public static void dfs(int i,int j) {
            cnt++;               
            visited[i][j]=true;
            for(int a=0;a<4;a++) {
                int nx=i+dx[a];
                int ny=j+dy[a];
                
                if(nx>0&&ny>0&&nx<=n&&ny<=m&&visited[nx][ny]==false) {
                    if(list[nx][ny]==1) {
                    dfs(nx,ny);                   
                }                
                }
                else continue;
            }
    } 
}
