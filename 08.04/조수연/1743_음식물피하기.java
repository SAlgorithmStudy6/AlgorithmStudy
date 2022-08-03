import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int M;
	static int K;
	static boolean[][] visited = new boolean[101][101];
	static boolean[][] passThrough = new boolean[101][101];
	static Queue<Point> trash = new LinkedList<>();
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int count = 0;
	static int max = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());
		
		for (int i = 0; i < K; i++) {
			token = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(token.nextToken());
			int y = Integer.parseInt(token.nextToken());
			passThrough[x][y] = true; 
			
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (!visited[i][j] && passThrough[i][j] ) {
					count = 0;
					bfs(i,j);
					max = Math.max(max, count);
				}
			}
		}
		
		System.out.println(max);
		
	}
	
	static void bfs(int x, int y) {
		Queue<Point> trash = new LinkedList<>();
		trash.add(new Point(x,y));
		visited[x][y] = true;
		count++;
		while(!trash.isEmpty()) {
			Point point = trash.poll();
			for (int i = 0; i < 4; i++) {
				int pointX = point.x + dx[i];
				int pointY = point.y + dy[i];
				
				if (pointX >= 1 && pointX <= N && pointY >= 1 && pointY <= M ) {
					if (!visited[pointX][pointY] && passThrough[pointX][pointY]) {
						visited[pointX][pointY] = true;
						trash.add(new Point(pointX,pointY));
						count++;
					}
				}
				
			}
			
		}
	}
}
