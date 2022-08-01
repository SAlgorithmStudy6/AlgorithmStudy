package etc;

import java.util.Scanner;

public class 백준16173 {
	
	static int arr[][];
	static boolean visited[][];
	static int dx[]= {1,0};
	static int dy[]= {0,1};
	static int n;
	static int cnt=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		
		n=scan.nextInt();
		arr=new int[n][n];
		visited=new boolean[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				arr[i][j]=scan.nextInt();
			}
		}
		dfs(0,0);
		if(cnt>0) {
			System.out.println("HaruHaru");
		}
		else System.out.println("Hing");
		
		
	}
	public static void dfs(int i, int j) {
		if(i==n-1&&j==n-1) {
			cnt++;
			return;
		}
		if (arr[i][j]==0) {
			return;
		}
		int nx=i+arr[i][j];
		int ny=j;
		if(nx>=0&&ny>=0&&nx<n&&ny<n) {
			if(visited[nx][ny]==false) {
				dfs(nx,ny);
			}
		}
		
		nx=i;
		ny=j+arr[i][j];
		if(nx>=0&&ny>=0&&nx<n&&ny<n) {
			if(visited[nx][ny]==false) {
				dfs(nx,ny);
			}
		}
		
		return;
	}

}
