package pro2606;

import java.util.*;

public class 바이러스 {
		static int net[][];
		static int vis[];
		
		static void bfs() {
			Queue<Integer> queue = new LinkedList<>();
			int index = 1;
			vis[index] =1;
			queue.offer(index);
			int cnt = 0;
			
			while(!queue.isEmpty()) {
				int x = queue.poll();				
				for(int i=1;i<net.length;i++) {
					if(net[x][i]==1 && vis[i]!=1) {
						queue.offer(i);
						vis[i] = 1;
						cnt++;
					}
				}
			}
			System.out.println(cnt);
		}
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);	
			
			int n= sc.nextInt();
			int m = sc.nextInt();
			
			net=new int[n+1][n+1];
			vis = new int[n+1];
			for(int i=0;i<m;i++) {
				int a=sc.nextInt();
				int b = sc.nextInt();
				net[a][b]=1;
				net[b][a]=1;			
			}

			bfs();
		}

}
