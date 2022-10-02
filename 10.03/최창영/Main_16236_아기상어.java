import java.util.*;
import java.io.*;

public class Main_16236_아기상어 {
	static int N;
	static int startX; static int startY;
	static int nowX, nowY;
	static int sharkSize = 2;
	static int eatCount = 0;
	static int result = 0;
	
	static int dirX[] = {-1, 0, 0, 1}; // 상 하 좌 우  ( 상 -> 좌 -> 우 -> 하)
	static int dirY[] = {0, -1, 1, 0};
	static int arr[][];
	
	static class Node implements Comparable<Node>{
		int x;
		int y;
		int dist;
		
		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
	        if(dist == o.dist) {
	            if(x == o.x) return Integer.compare(y, o.y);
	            return Integer.compare(x, o.x);
	        }
	        return Integer.compare(dist, o.dist);
		}
	} // End of Node
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/16236.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];

		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 9) {
					startX = i; 
					startY = j;
					arr[i][j] = 0;
				}
			}
		}
		
		BFS(startX, startY);
		System.out.print(result);
	} // End of main
	
	private static void BFS(int x, int y) {
		PriorityQueue<Node> que = new PriorityQueue<>();
		
		boolean visit[][] = new boolean[N][N];
		que.offer(new Node(x, y, 0));
		visit[x][y] = true;
		
		while(!que.isEmpty()) {
			Node node = que.poll();

			for(int i=0; i<4; i++) {
				nowX = dirX[i] + node.x;
				nowY = dirY[i] + node.y;
				
				if(!range_check() || visit[nowX][nowY] ) continue;
				visit[nowX][nowY] = true;
				
				if( arr[nowX][nowY] <= sharkSize ) {
					que.offer(new Node(nowX, nowY, node.dist+1));
				}
			}	
			
			if(!que.isEmpty()) {
				Node peekNode = que.peek();
				
				if(arr[peekNode.x][peekNode.y] < sharkSize && arr[peekNode.x][peekNode.y] > 0) {
					eatCount++;
					if(eatCount == sharkSize) {
						sharkSize++;
						eatCount = 0;
					}
					arr[peekNode.x][peekNode.y] = 0;
					
					que.clear();
					que.offer(new Node(peekNode.x, peekNode.y, 0));
					result += peekNode.dist;
					visit = new boolean[N][N];
					visit[peekNode.x][peekNode.y] = true;
				}
			}
		}
	} // End of BFS

	private static boolean range_check() {
		return nowX >= 0 && nowX < N && nowY >= 0 && nowY < N;
	} // End of range_check
} // End of Main class