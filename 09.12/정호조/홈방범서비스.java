import java.util.*;
import java.io.*;

public class 홈방범서비스_2117 {
	static int[][] map;
	static int ans;
	static int N, M; 
	
	static int[] di = {0,0,1,-1};
	static int[] dj = {1,-1,0,0};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		int TC = sc.nextInt();
		for(int tc=1; tc<=TC; tc++) {
			N = sc.nextInt();
			M = sc.nextInt();
			
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j] = sc.nextInt();
				}
			}
			
			ans = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					bfs(i, j);
				}
			}
			System.out.println("#"+tc+" "+ans);
		}
	}

	static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visit = new boolean[N][N];
		
		q.add(new Point(r, c));
		visit[r][c] = true;
		
		int K = 1;
		int house = 0; 
		while(!q.isEmpty()) {
			int size = q.size();
			for(int s=0; s<size; s++) {
				Point temp = q.poll();
				if(map[temp.i][temp.j]==1) { 
					house++;
				}
				for(int d=0; d<4; d++) { 
					int rr = temp.i + di[d];
					int cc = temp.j + dj[d];
					if(rr>=0 && rr<N && cc>=0 && cc<N && !visit[rr][cc]) {
						q.add(new Point(rr, cc));
						visit[rr][cc]=true;
					}
				}
			}
			int cost = K*K + (K-1)*(K-1); 
			int income = house*M; 
			if(cost <= income) { 
				ans = Math.max(ans, house);
			}
			K++;
		}
	}
	
	static class Point{
		int i, j;
		Point(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
}
