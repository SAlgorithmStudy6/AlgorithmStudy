import java.util.*;
import java.io.*;

public class Main_1743_음식물피하기_BFS {
	static boolean visit[][];
	static int map[][];
	static int dirX[] = {0, 0, -1, 1};
	static int dirY[] = {-1, 1, 0, 0};
	static Queue<Node> que = new LinkedList<>();
	
	static int N, M;
	static int nowX, nowY;
	static int x, y;
	static int count;
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	} // End of Node class
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1743.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken()) + 1;
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			
			map[y][x] = 1;
		}

		// 1을 만나면 탐색 시작
		int max = Integer.MIN_VALUE;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visit[i][j] == false && map[i][j] > 0) {					
					BFS(j, i);
					max = Math.max(max, count);
				}
				
			}
		}
		
		System.out.println(max);
	} // End of main
	
	static void BFS(int x, int y) {
		que.offer(new Node(x, y));
		visit[y][x] = true;
		// 자기 자신 포함
		count = 1;
		
		while( !que.isEmpty() ) {
			Node node = que.poll();

			for(int i=0; i<4; i++) {
				nowY = node.y + dirY[i];
				nowX = node.x + dirX[i];
				
				if(range_check() && visit[nowY][nowX] == false && map[nowY][nowX] == 1) {     
					que.offer(new Node(nowX, nowY));
					visit[nowY][nowX] = true;
					count ++;
				}
			}
	
		}
	} // End of BFS
	
	static boolean range_check() {
		return (nowX >= 0 && nowY >= 0 && nowX < M && nowY < N);
	} // End of range_check
} // End of Main class