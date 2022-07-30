import java.util.*;
import java.io.*;

public class Main_16173_점프왕_쩰리 {
	static int N;
	static int nowX; static int nowY;
	static int arr[][];
	static int dirX[] = {1, 0}; // 하 우
	static int dirY[] = {0, 1};
	
	private static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	} // End of Node class
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/16173.txt"));
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if(BFS(0, 0)) {
			System.out.println("HaruHaru");
		}
		else {
			System.out.println("Hing");
		}
	} // End of main
	
	private static boolean BFS(int x, int y) {
		Queue<Node> que = new LinkedList<>();
		boolean visit[][] = new boolean[N][N];
		visit[x][y] = true;
		que.offer(new Node(x, y));
		
		while(!que.isEmpty()) {
			Node node = que.poll();
			
			for(int i=0; i<2; i++) {
				nowX = node.x + (dirX[i] * arr[node.x][node.y]);
				nowY = node.y + (dirY[i] * arr[node.x][node.y]);
				
				if(range_check() && !visit[nowX][nowY]) {
					if(arr[nowX][nowY] == -1) return true;
					
					visit[nowX][nowY] = true;
					que.offer(new Node(nowX, nowY));
				}
			}
		}
		
		return false;
	} // End of BFS
	
	private static boolean range_check() {
		return nowX >= 0 && nowX < N && nowY >= 0 && nowY <N;
	} // End of range_check
} // End of Main class