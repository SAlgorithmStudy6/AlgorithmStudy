import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class point{
    int x;
    int y;
    public point(int x,int y) {
        // TODO Auto-generated constructor stub
        this.x=x;
        this.y=y;
    }
}

public class Main {
    static int[] dx= {1,-1,0,0,1,1,-1,-1};
    static int[] dy= {0,0,-1,1,-1,1,-1,1};
    static int m,n;
    static int[][] visited;
    static int [][]arr;
    static Queue<point> q;
    static int max=Integer.MIN_VALUE;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan=new Scanner(System.in);
        
        n=scan.nextInt();
        m=scan.nextInt();
        arr=new int [n][m];
        visited=new int [n][m];
        q=new LinkedList<>();
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                arr[i][j]=scan.nextInt();
                if(arr[i][j]==1) {
                    q.add(new point(i,j));
                }
            }
        }
 
        sol();
        System.out.println(max);
    }
    static void sol() {
    while(!q.isEmpty()) {
        point xy=q.poll();
    
        for(int k=0;k<8;k++) {
            int nx=xy.x+dx[k];
            int ny=xy.y+dy[k];
            if(nx>=0&&ny>=0&&nx<n&&ny<m) {
               if(visited[nx][ny]==0&& arr[nx][ny]==0) {
                visited[nx][ny]=visited[xy.x][xy.y]+1;
                if(visited[nx][ny]>max) {
                    max=visited[nx][ny];
                }

                q.add(new point(nx,ny));
               }
            }
        }
    }
    
    }

}
