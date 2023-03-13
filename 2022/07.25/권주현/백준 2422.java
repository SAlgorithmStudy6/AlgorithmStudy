package etc;

import java.util.ArrayList;
import java.util.Scanner;

public class 백준2422 {
	static int [][] arr;
	static int[] visited;
	static int ans=0,n,m;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  Scanner scan=new Scanner(System.in);
		  
		  n=scan.nextInt();
		  m=scan.nextInt();
		 
		  arr=new int[n+1][n+1];
		  visited=new int[n];
		  for(int i=0;i<m;i++) {
			  int a=scan.nextInt();
			  int b=scan.nextInt();
			  arr[a][b]=arr[b][a]=1;
			  //n
		  }
		  for(int i=1;i<=n;i++) {
			for(int j=i+1;j<=n;j++) {
				if(arr[i][j]==1) continue;
				for(int k=j+1;k<=n;k++) {
					if(arr[j][k]==1||arr[i][k]==1) continue;
					else ans++;
				}
			}
		  }
		  System.out.println(ans);
		  
	}


}
