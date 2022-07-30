import java.util.*;
import java.io.*;

public class Main_2606_바이러스_DFS {
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
		
		DFS(1);
		
		System.out.println(result);
	}
	
	static void DFS(int node) {
		visit[node] = true;
		
		for(int i=1; i<N+1; i++) {
			
			if(arr[node][i] == 1 && visit[i] == false) {
				DFS(i);
				result++;
			}
		}
		
	}
}
