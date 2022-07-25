package etc;

import java.util.Scanner;

public class 백준3085 {
	static int max=0;
	static int n;
	static char[][] arr;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		n=scan.nextInt();
		arr=new char[n][n];
	
		for(int i=0;i<n;i++) {
			String str=scan.next();
			for(int j=0;j<str.length();j++) {
				arr[i][j]=str.charAt(j);
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				sol(i,j);
			}
		}
		
		System.out.println(max);
	}
	static void sol(int i,int j) {
		//가로 확인후 바꾸기
		
		if(j+1<n&&arr[i][j]!=arr[i][j+1]) {
			char tmp= arr[i][j];
			arr[i][j]=arr[i][j+1];
			arr[i][j+1]=tmp;
			count();
			//원상복구
			char tmp1= arr[i][j];
			arr[i][j]=arr[i][j+1];
			arr[i][j+1]=tmp1;
			
		}
		//세로 확인 후 바꾸기
		if(i+1<n&&arr[i][j]!=arr[i+1][j]) {
			char tmp= arr[i][j];
			arr[i][j]=arr[i+1][j];
			arr[i+1][j]=tmp;
			count();
			//원상복구
			char tmp1= arr[i][j];
			arr[i][j]=arr[i+1][j];
			arr[i+1][j]=tmp1;
			
		}
		
	}
	static void count() {
		//최대 사탕 갯수 세기

		//가로 세기
		for(int m=0;m<n;m++) {
			int cnt=1;
			for(int k=0;k<n-1;k++) {
			if(arr[m][k]==arr[m][k+1]) {	
				cnt++;		
			}
			else {
			cnt=1;
			}
			
			max=Math.max(max, cnt);
			}
		}
			
		//세로 세기
		for(int m=0;m<n;m++) {
			int cnt=1;
			for(int k=0;k<n-1;k++) {
			if(arr[k][m]==arr[k+1][m]) {	
				cnt++;		
			}
			else {
			cnt=1;
			}
			
			max=Math.max(max, cnt);
			}
		}
		return;
	}
}
