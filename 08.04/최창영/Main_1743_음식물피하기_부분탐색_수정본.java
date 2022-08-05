import java.util.*;
import java.io.*;

public class Main_1743_음식물피하기_부분탐색_수정본 {
	static boolean visit[][];
	static int map[][];
	static int dirX[] = {0, 0, -1, 1};
	static int dirY[] = {-1, 1, 0, 0};
	
	static int N, M;
	static int nowX, nowY;
	static int x, y;
	static int count = 1;
	
	static class Node{
		int x;
		int y;
		
		public Node(int x, int y){
			this.x = x;
			this.y = y;
		} 
	} // End of Node class
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/1743.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken()) + 1;
		int K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M];
		List<Node> trapList = new ArrayList<>();
		
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			trapList.add(new Node(x, y));
			map[x][y] = 1;
		}
		
		// 배열 전체 순회X
		int max = Integer.MIN_VALUE;
		for(Node node : trapList) {
			int x = node.x;
			int y = node.y;
			
			count = 0;
			DFS(x, y);
			max = Math.max(max, count);
		}

		System.out.print(max);
	} // End of main
	
	static void DFS(int x, int y) {
		visit[y][x] = true;
		count++;

		for(int i=0; i<4; i++) {
			nowX = x + dirX[i];
			nowY = y + dirY[i];
			
			if(range_check() && visit[nowY][nowX] == false && map[nowY][nowX] > 0) {
				DFS(nowX, nowY);
			}
		}
	} // End of DFS
	
	static boolean range_check() {
		return (nowX >= 0 && nowY >= 0 && nowX < M && nowY < N);
	} // End of range_check
} // End of Main class