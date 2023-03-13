import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[][] area;
	static int[] dx = {1,0}; //x축
	static int[] dy = {0,1}; //y축
	static Queue<Point> jelly = new LinkedList<Point>(); //젤리 좌표
	static boolean[][] visited;
	static String result = "Hing";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		
		area = new int[N][N];
		jelly.add(new Point(0,0)); // 0,0부터 시작
		visited = new boolean[N][N];
		visited[0][0] = true;
		
		for (int i = 0; i < area.length; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int j = 0; j < area.length; j++) {
				area[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		move();
		System.out.println(result);
		
	}
	
	static void move() {
		while(!jelly.isEmpty()) {
			Point point = jelly.poll();
			
			for (int i = 0; i < 2; i++) {
				int x = point.x + ( dx[i] * area[point.x][point.y]);
				int y = point.y + ( dy[i] * area[point.x][point.y]);
				if (x >= 0 && x < N && y >= 0 && y < N) {
					if (area[x][y] == -1) {
						result = "HaruHaru";
						jelly.clear();
						break;
					}else {
						if (!visited[x][y]) {
							jelly.add(new Point(x,y));
							visited[x][y] = true;
						}
					}
				}
			}
			System.out.println("-------"+jelly);
		}
	}
}
