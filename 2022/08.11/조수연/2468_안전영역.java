import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int count;
	static int maxCount = 0;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int[][] area = new int[N][N];
		int maxHeight = 0;
		
		for (int i = 0; i < area.length; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int j = 0; j < area.length; j++) {
				area[i][j] = Integer.parseInt(token.nextToken());
				maxHeight = Math.max(maxHeight,area[i][j]);
			}
		}
		
		for (int i = 0; i <= maxHeight-1; i++) {
			search(i,area);
		}
		
		System.out.println(maxCount);
		
	}
	
	static void search(int base, int[][] area) {
		visited = new boolean[N][N];
		count = 0;
		
		for (int i = 0; i < area.length; i++) {
			for (int j = 0; j < area.length; j++) {
				if (area[i][j] <= base) {
					visited[i][j] = true; 
				}
			}
		}
		
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited.length; j++) {
				if (!visited[i][j]) {
					bfs(i,j);
				}
			}
		}
		
		maxCount = Math.max(maxCount,count);
		
		
		
	}
	
	static void bfs(int row, int col) {
		
		Queue<Point> safe = new LinkedList<Point>();
		
		safe.add(new Point(row,col));
		visited[row][col] = true;
		
		while(!safe.isEmpty()) {
			Point point = safe.poll();
			for (int i = 0; i < dx.length; i++) {
				int x = point.x + dx[i];
				int y = point.y + dy[i];
				
				if (x >= 0 && x < N && y >= 0 && y < N) {
					if (!visited[x][y]) {
						visited[x][y] = true;
						safe.add(new Point(x,y));
					}
				}
			}
		}
		count++;
	}
}
