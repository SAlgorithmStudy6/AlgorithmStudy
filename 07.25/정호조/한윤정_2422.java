package problum2422;

import java.util.Scanner;

public class 한윤정_2422 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int ans = 0;
		boolean[][] arr = new boolean[n+1][n+1];
		
		for(int i=0; i<m; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[a][b] = true;
			arr[b][a] = true;
		}
		for(int i=1; i<n-1; i++) {
			for(int j=i+1; j<n; j++) {
				for(int k=j+1; k<n+1; k++) {
					if(arr[i][j] || arr[i][k] || arr[j][k] || arr[j][i] || arr[k][j] || arr[k][i] ) {
						continue;
					}
					ans++;
				}
			}
		}
		System.out.println(ans);
		
	}
}
