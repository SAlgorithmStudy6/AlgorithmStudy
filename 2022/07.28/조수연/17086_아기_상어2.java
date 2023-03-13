import java.awt.Point;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int M;
	static int distance = 0;
	static int graph[][];
	static int visited[][]; 
	static int [] dx = {-1,-1,-1,1,1,1,0,0}; //x축 방향
	static int [] dy = {-1,0,1,-1,0,1,-1,1}; //y축 방향
	static Queue<Point> shark = new LinkedList<Point>(); //상어 좌표
	
	

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/1143.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());
		graph = new int[N][M];
		visited = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(token.nextToken()); 
				visited[i][j] = Integer.MAX_VALUE; 
				if (graph[i][j] == 1 ) {
					shark.add(new Point(i,j));
					visited[i][j] =  0;
				}
			}
		}
		
		bfs();
		System.out.println(distance);
	}
	
	static void bfs() {
		while(!shark.isEmpty()) {
			Point point = shark.poll();
			
			for (int i = 0; i < dx.length; i++) {
				int x = point.x+dx[i];
				int y = point.y+dy[i];
				
				if (x >= 0 && x < N && y >= 0 && y < M ) {
					if (visited[x][y] > visited[point.x][point.y] + 1) {
						visited[x][y] =  visited[point.x][point.y] + 1;
						
						distance = Math.max(distance, visited[x][y]);
						shark.add(new Point(x,y));
					}
				}
			}
		}
	}


}
