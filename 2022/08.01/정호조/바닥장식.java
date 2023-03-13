package problum1388;

import java.util.Scanner;

public class 바닥장식 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		String[][] arr = new String[n][m];
		int cnt = 1;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				String str = sc.next();
				arr[i][j] = String.valueOf(str.charAt(j));
			}
		}
		
		for(int i=0; i<n-1; i++) {
			for(int j=0; j<m-1; j++) {
				if(arr[i][j].equals('-')) {
					if(arr[i][j+1].equals('|') || j == m-1) cnt++;
				}
				if(arr[i][j].equals('|')) {
					if(arr[i+1][j].equals('-') || i == n-1) cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
