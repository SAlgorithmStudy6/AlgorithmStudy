package etc;

import java.util.Scanner;

public class 백준1388 {
	static int n,m,cnt;
	
	static char arr[][];
	static int visited[][];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		
		n=scan.nextInt();
		m=scan.nextInt();
		arr=new char[n][m];
		visited=new int[n][m];
		for(int i=0;i<n;i++) {
			String str=scan.next();
			for(int j=0;j<m;j++) {
				arr[i][j]=str.charAt(j);
			}
		}
		//가로 
		for(int i=0;i<n;i++) {
			int num=0;
			for(int j=0;j<m;j++) {
				if(arr[i][j]=='-') {
					cnt++;
					while(j<m&&arr[i][j]=='-') {//-인동안 한조각 cnt안함
						j++;
					}
				}
			}
		}
		//세로
		for(int i=0;i<m;i++) {
			int num=0;
			for(int j=0;j<n;j++) {
				if(arr[j][i]=='|') {
					cnt++;
					while(j<n&&arr[j][i]=='|') {//-인동안 한조각 cnt안함
						j++;
					}
				}
				
			}
		}
		System.out.println(cnt);
		
	}
}
