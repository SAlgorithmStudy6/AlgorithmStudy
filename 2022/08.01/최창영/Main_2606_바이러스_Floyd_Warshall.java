import java.util.*;
import java.io.*;

public class Main_2606_바이러스_Floyd_Warshall {
	static int arr[][];
	static int N;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_11724.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수
		
		// 무방향 그래프
		// 노드의 개수만큼 배열 생성.
		arr = new int[N+1][N+1];
		
		while(M --> 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 무방향 그래프 특성.
			arr[x][y] = 1;
			arr[y][x] = 1;
		}
		
		// floyd_warshall (완전탐색)
		// 각 노드에서 갈 수 있는 곳을 모두 탐색.
		 floyd();
		
		int count = 0;
		boolean node[] = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			
			// 이미 등록된 노드는 탐색할 필요없음
			if(node[i] == true) {                                                                           
				continue;
			}
			
			// 방문 가능한 노드는 바로 true처리.
			for(int j=1; j<=N; j++) {
				if(arr[i][j] == 1 && node[j] == false) {
					node[j] = true;
				}
			}
			
			count ++;
		}
		
		System.out.println(count);
		
	} // End of main
	
	static void floyd() {
		
		for(int k=0; k<=N; k++) {
			for(int i=0; i<=N; i++) {
				for(int j=0; j<=N; j++) {
					
					if(arr[i][k] == 1 && arr[k][j] == 1) {
						arr[i][j] = 1;
					}
				}
			}
		}
		
	} // End of floyd

} // End of class