import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main{
   static boolean[][] visited;
   static int n,max;
   static int[] dx= {0,1,0,-1};
   static int[] dy= {1,0,-1,0};
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan=new Scanner(System.in);
     
         n=scan.nextInt();
        max=0;
        int cnt=0;
        int MAX=0;
        int min=101;
        
        int[][] arr=new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0;j<n;j++) {
                arr[i][j]=scan.nextInt();
                if(arr[i][j]>max) {
                    max=arr[i][j];
                }
                if(arr[i][j]<min) {
                    min=arr[i][j];
                }
            }
        }
        for(int k=min;k<=max;k++) {
            visited=new boolean[n][n];
            cnt=0;
            for(int i=0;i<n;i++) {
                for(int j=0;j<n;j++) {
                    if(!visited[i][j]&&arr[i][j]>k) {
//                        System.out.println("k : "+k);

                        cnt++;
                        dfs(i,j,arr,k);
                    } 
                }
            }
            if(MAX<cnt){
                MAX=cnt;
            }
//            System.out.println("cnt"+cnt);
        }
        if(MAX==0) {
            System.out.println(1);
        }
        else System.out.println(MAX);
        
       
        
    }
    static void dfs(int i,int j, int[][] arr,int depth) {
//        System.out.println("startindex"+i+""+j);
       
       visited[i][j]=true;
       for(int a=0;a<4;a++) {
           int nx=i+dx[a];
           int ny=j+dy[a];
//           System.out.println("nx ny"+ nx+" "+ny);
           if(nx>=0&&ny>=0&&nx<n&&ny<n&&!visited[nx][ny]&&arr[nx][ny]>depth) {
//               System.out.println(nx+" "+ny);
               visited[nx][ny]=true;
               dfs(nx,ny,arr,depth);
           }
       }
       
    } 
}
