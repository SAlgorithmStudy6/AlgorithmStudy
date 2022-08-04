import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	static int[] dx= {1,-1,0,0};
	static int[] dy= {0,0,-1,1};
	static int m,n,k,cnt=0,idx=0;
	static int[][] arr;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		m=scan.nextInt();
		n=scan.nextInt();
		k=scan.nextInt();
		arr=new int[m][n];
		ArrayList<Integer> list=new ArrayList<>();
		for(int i=0;i<k;i++) {
				int a=scan.nextInt();
				int b=scan.nextInt();
				int c=scan.nextInt();
				int d=scan.nextInt();
				for(int q=b;q<d;q++) {
					for(int p=a;p<c;p++) {
						arr[q][p]=1;
					}
				}
			
		}
		
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++) {
				if(arr[i][j]==0) {
					cnt=0;
					dfs(i,j);
					list.add(cnt);
				}
			}
		}
		
		Collections.sort(list);
		System.out.println(list.size());
		for(int i=0;i<list.size();i++) {
			
			System.out.print(list.get(i)+" ");
		}
		
	}
	
	static void dfs(int i,int j) {
		arr[i][j]=1;
		cnt++;
		
		for(int a=0;a<4;a++) {
			int nx=i+dx[a];
			int ny=j+dy[a];
			
			if(nx>=0&&ny>=0&&nx<m&&ny<n) {
				if(arr[nx][ny]==0) {
					dfs(nx,ny);
				}
			}
		}
	}

}
