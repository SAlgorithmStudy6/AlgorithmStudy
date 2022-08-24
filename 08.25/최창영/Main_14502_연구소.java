import java.util.*;
import java.io.*;

// 문제 : https://www.acmicpc.net/problem/14502
// 목표 : 0은 빈칸, 1은 벽, 2는 바이러스가 있는 곳.
// 벽이 없으면 바이러스는 모든 빈칸으로 펴저나갈 수 있다.

public class Main_14502_연구소 {
	static int dirX[] = {0, 0, -1, 1};
	static int dirY[] = {-1, 1, 0, 0};
	static int arr[][];
	
	static int result = Integer.MIN_VALUE / 16;
	static int nowX, nowY, N, M;
	
	static class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	} // End of Node
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14502.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 가로
		M = Integer.parseInt(st.nextToken());// 세로
		
		arr = new int[N][M];
		
		// 연구소와 똑같은 지도 배열 생성.
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 세운 벽의 숫자0으로 시작.
		DFS(0);
		
		System.out.println(result);
	} // End of main
	
	// 벽을 세우는 작업은 DFS
	static void DFS(int depth) {
		
		if(depth == 3) {
			BFS();
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				
				// 벽이 없는 곳인 0의 경우, 
				// 벽울 세울 수 있으므로, 1로 수정해줌.
				if(arr[i][j] == 0) {
					arr[i][j] = 1;
					DFS(depth + 1);
					// 다음 벽을 세울때를 생각해서 다시 0으로 복구시켜놔야함
					arr[i][j] = 0;
				}
			}
		}
		
	} // End of DFS
	
	static void BFS() {
		int map[][] = new int[N][M];
		Queue<Node> que = new LinkedList<>();
		
		//벽을 만들어놓은 배열을 그대로 지도로 카피
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = arr[i][j];
			}
		}
		
		// 카피된 지도에서 2인 바이러스가 나올경우, 
		// 좌표값 que에 삽입
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 2) {
					que.offer(new Node(i, j));
				}
			}
		}
		
		// map지도에서 바이러스 퍼트리기 시작.
		while(!que.isEmpty()) {
			Node node = que.poll();
			
			for(int i=0; i<4; i++) {
				nowX = node.x + dirX[i];
				nowY = node.y + dirY[i];
				
				if(Range_check() && map[nowX][nowY] == 0) {
					map[nowX][nowY] = 2;
					que.offer(new Node(nowX, nowY));
				}
			}
				
		}	
		
		find_max(map); 
	}
	
	static void find_max(int map[][]) {
		int count = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 0) {
					count ++;
				}
			}
		}
		
		result = Math.max(result, count);
	} 
	
	static boolean Range_check() {
		return (nowX >= 0 && nowX < N && nowY >= 0 && nowY < M);
	} // End of Range_check
	
} // End of class