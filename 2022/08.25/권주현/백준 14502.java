package etc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
class pos{
	int x;
	int y;
	pos(int x,int y){
		this.x=x;
		this.y=y;
	}
}
public class 백준14502 {
	static int max=Integer.MIN_VALUE;
	static int n,m;
	static int wall_num=0;
	static int[][] arr;
	static ArrayList<pos> vir;
	static int [] dx= {-1,1,0,0};
	static int [] dy= {0,0,-1,1};
	public static void main(String[] args) {
		// 1: 벽 2: 바이러스 벽 세개 세울수 있음 안전영역의 최댓값
		Scanner scan=new Scanner(System.in);
		n=scan.nextInt();
		m=scan.nextInt();
		arr=new int[n][m];
		vir=new ArrayList<pos>();
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				arr[i][j]=scan.nextInt();
				if(arr[i][j]==2) {
					vir.add(new pos(i,j));
				}
				if(arr[i][j]==1) {
					wall_num++;
				}
			}
		}
	
		boolean[][] visited=new boolean[n][m];
		wall(0,visited);
		System.out.println(max);
	}
	public static void wall(int cnt,boolean[][] visited) {
		if(cnt ==3) {
			boolean[][] visited2=new boolean[n][m];
			int virus_num=0;
			for(int i=0;i<vir.size();i++) {
				pos now=vir.get(i);	
				virus_num+=virus_spread(now.x,now.y,visited2);
			}
			//안전영역 : 전체 격자 수- (벽수 +3)-(virus_spread 후 2개수)
			int safe_zone=(n*m)-(wall_num+3)-(virus_num+vir.size());
			max=Math.max(max, safe_zone);
			
			return;
		}
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(arr[i][j]==0&&!visited[i][j]) {
					visited[i][j]=true;
					arr[i][j]=1;
					wall(cnt+1,visited);
					arr[i][j]=0;
					visited[i][j]=false;
				}
			}
		}
	}
	
	
	public static int virus_spread(int x,int y,boolean[][] visited) {
		int virus_num=0;

		Queue<pos> q=new LinkedList<>();
		q.offer(new pos(x,y));
		
		while(!q.isEmpty()) {
			pos now=q.poll();	
			for(int i=0;i<4;i++) {
				int nx=now.x+dx[i];
				int ny=now.y+dy[i];
				if(nx>=0&&ny>=0&&nx<n&&ny<m) {
					if(arr[nx][ny]==0&&!visited[nx][ny]) {
						visited[nx][ny]=true;
						virus_num++;
						q.offer(new pos(nx,ny));
					}
				}
			}
		}
		
		return virus_num++;
	}

}
