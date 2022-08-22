package etc;

import java.util.Scanner;

public class 백준17406 {
static int k,min=Integer.MAX_VALUE,n,m;
static boolean [] visited;
static int[] tmp_order;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		n=scan.nextInt();
		m=scan.nextInt();
		k=scan.nextInt();		//회전 연산의 개수
		visited=new boolean[k];
		tmp_order=new int[k];
		int arr[][]=new int[n][m];
		int rot[][]=new int[k][3];
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				arr[i][j]=scan.nextInt();
//				System.out.print(arr[i][j]);
			}
//			System.out.println();
		}
		for(int a=0;a<k;a++) {
			rot[a][0]=scan.nextInt();
			rot[a][1]=scan.nextInt();
			rot[a][2]=scan.nextInt();
		}
		order(0,arr,rot);
		System.out.println(min);
	}
	public static void order(int cnt,int[][] arr,int[][] rot) {
		if(cnt==k) {	//basecase
			int[][] copy_arr=new int[n][m];
			//배열 복사
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					copy_arr[i][j]=arr[i][j];
				}
			}
			for(int i=0;i<k;i++) {
				//r-s c-s r+s r+s
				int x1 = rot[tmp_order[i]][0] - rot[tmp_order[i]][2] - 1;
				int y1 = rot[tmp_order[i]][1] - rot[tmp_order[i]][2] - 1;
				int x2 = rot[tmp_order[i]][0] + rot[tmp_order[i]][2] - 1;
				int y2 = rot[tmp_order[i]][1] + rot[tmp_order[i]][2] - 1;
				rotation(x1,y1,x2,y2,copy_arr);
			}
			for(int i=0;i<n;i++) {
				int sum=0;
				for(int j=0;j<m;j++) {
					sum+=copy_arr[i][j];
				}
				min=Math.min(min, sum);
			}
			
			return;
		}
		for(int i=0;i<k;i++) {
			if(!visited[i]) {
				visited[i]=true;
				tmp_order[cnt]=i;		//지금 순열의 순서 들어있는 배열
				order(cnt+1,arr,rot);
				visited[i]=false;		//초기화
			}
		}
	}
	public static void rotation(int x1,int y1,int x2,int y2,int [][]copy_arr) {
        if(x1 == x2 && y1 == y2) { 	//basecase	
            return;
        }

        int a = copy_arr[x1][y2];
        int b= copy_arr[x2][y2];
        int c = copy_arr[x2][y1];
        
        for(int i = y2; i > y1; i--) {
            copy_arr[x1][i] = copy_arr[x1][i - 1];
        }

        for(int i = x2; i > x1; i--) {
            if(i == x1 + 1) {	//마지막
            	copy_arr[i][y2] = a;
            }
            else copy_arr[i][y2] = copy_arr[i - 1][y2];
        }

        for(int i = y1; i < y2; i++) {
            if(i == y2 - 1) {
            	copy_arr[x2][i] = b;
            }
            else copy_arr[x2][i] = copy_arr[x2][i + 1];
        }

        for(int i = x1; i < x2; i++) {
            if(i == x2 - 1) {
            	copy_arr[i][y1] = c;
            }
            else copy_arr[i][y1] = copy_arr[i + 1][y1];
        }    
        
        rotation(x1 + 1, y1 + 1, x2 - 1, y2 - 1, copy_arr);
	}

}
