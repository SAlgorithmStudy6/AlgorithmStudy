import java.util.Scanner;


public class 백준17070 {
    static int n=0, cnt=0;
    static int arr[][];
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan=new Scanner(System.in);
        n=scan.nextInt();
        arr=new int [n+1][n+1];
        for(int i=1;i<=n;i++) {
        	for(int j=1;j<=n;j++) {
        		arr[i][j]=scan.nextInt();
        	}
        }
        dfs(1,2,"hor");
        System.out.println(cnt);
    }
   public static void dfs(int x, int y, String dir) {
	   if(x==n&&y==n) {
		   cnt++;
		   return;
	   }
	   
	   if(dir.equals("hor")) {
		   int nx=x;
		   int ny=y+1;
		   int nx2=x+1;
		   int ny2=y+1;
		   if(nx<=n&&ny<=n&&arr[nx][ny]==0) {
			   dfs(nx,ny,"hor");
			  
		   }
		   if(nx2<=n&&ny2<=n&&arr[x+1][y]==0&&arr[x][y+1]==0&&arr[nx2][ny2]==0) {
			   dfs(nx2,ny2,"dia");
		   }
	   }
	   else if(dir.equals("ver")) {
		   int nx=x+1;
		   int ny=y;
		   int nx2=x+1;
		   int ny2=y+1;
		   if(nx<=n&&ny<=n&&arr[nx][ny]==0) {
			   dfs(nx,ny,"ver");
		   }		   
		   if(nx2<=n&&ny2<=n&&arr[x+1][y]==0&&arr[x][y+1]==0&&arr[nx2][ny2]==0) {
			   dfs(nx2,ny2,"dia");
		   }
	   }
	   else if(dir.equals("dia")) {
		   int nx=x;
		   int ny=y+1;
		   int nx2=x+1;
		   int ny2=y;
		   int nx3=x+1;
		   int ny3=y+1;
		   if(nx<=n&&ny<=n&&arr[nx][ny]==0) {
			   dfs(nx,ny,"hor");
		   }
		   if(nx2<=n&&ny2<=n&&arr[nx2][ny2]==0) {
			   dfs(nx2,ny2,"ver");
		   }
		   if(nx3<=n&&ny3<=n&&arr[x+1][y]==0&&arr[x][y+1]==0&&arr[nx3][ny3]==0) {
			   dfs(nx3,ny3,"dia");
		   }
	   }
   }

}
