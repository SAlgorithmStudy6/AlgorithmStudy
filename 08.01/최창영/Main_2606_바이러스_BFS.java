import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/2606

// 목표 : 1번 컴퓨터가 웜 바이러스에 걸렸을 때, 
// 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.

public class Main_2606_바이러스_BFS {
	static Queue<Integer> que = new LinkedList<>();
	static int arr[][];
	static boolean visit[];
	
	static int N, M;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2606.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		M = Integer.parseInt(br.readLine()); // 컴퓨터 쌍의 수
		
		// 1을 기준으로 한번만 탐색하면 됨.
		// visit 배열은 1차원 배열로 생성.
		visit = new boolean[N+1];
		arr	= new int[N+1][N+1];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			arr[y][x] = 1;
			arr[x][y] = 1;
		}
		
		BFS();
		
		System.out.println(result);
	}// End of main
	
	// k를 통해 갈 수 있는 곳을 모두 연결
	private static void BFS() {
		
		que.offer(1);
		visit[1] = true;
		
		while( !que.isEmpty() ) {
			int node = que.poll();
			
			for(int i=1; i<N+1; i++) {
				// 1이 아니거나 visit[]이 true일 경우, 그냥 통과
				if(arr[node][i] != 1 || visit[i] == true) {
					continue;
				}
				
				que.offer(i);
				visit[i] = true;
				result++;
			}
		}
		
	} // End of BFS
	
	
} // End of class
