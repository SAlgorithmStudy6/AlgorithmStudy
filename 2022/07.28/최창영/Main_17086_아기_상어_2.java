import java.util.*;
import java.io.*;

public class Main_17086_아기_상어_2 {
	static int arr[][];
	static int dirX[] = {0, 0, -1, 1, -1, 1, 1, -1}; // 상 하 좌 우
	static int dirY[] = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int N, M;
	static int nowX, nowY;
	static int max = -1;
	
	public static class Node {
		int x;
		int y;
		int dist;
		
		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	} // End of Node class
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/17086.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(arr[i][j] == 0) {
					BFS(i, j);
				}
			}
		}
		
		System.out.print(max);
	} // End of main
	
	private static void BFS(int x, int y) {
		Queue<Node> que = new LinkedList<>();
		que.offer(new Node(x, y, 0));
		boolean visit[][] = new boolean[N][M];
		visit[x][y] = true;
		
		while( !que.isEmpty() ) {
			Node node = que.poll();
			
			for(int i=0; i<8; i++) {
				nowX = dirX[i] + node.x;
				nowY = dirY[i] + node.y;
				
				if(!range_check() || visit[nowX][nowY]) continue;
				if(arr[nowX][nowY] == 1) {
					max = Math.max(max, node.dist + 1);
					return;
				}
				
				visit[nowX][nowY] = true;
				que.offer(new Node(nowX, nowY, node.dist + 1));
			}	
		}
	} // End of BFS
	
	private static boolean range_check() {
		return nowX >= 0 && nowX < N && nowY >= 0 && nowY < M;
 	} // End of range_check
} // End of Main class