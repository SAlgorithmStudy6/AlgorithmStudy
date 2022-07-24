import java.util.*;
import java.io.*;

public class Main_2422_한윤정이_이탈리아에_가서_아이스크림을_사먹는데 {
	static int N, M;
	static int arr[];
	static boolean visit[];
	static boolean comb[][];
	
	static int ans[];
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/2422.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		visit = new boolean[N+1];
		
		comb = new boolean[N+1][N+1]; // 조합을 담는 배열
		ans = new int[3]; // 백트래킹 정답 담는 배열
		
		for(int i=1; i<=N; i++) arr[i-1] = i;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			
			comb[num][num2] = true;
			comb[num2][num] = true;
		}		

		DFS(0, 0);
		System.out.println(result);
	} // End of main
	
	private static void DFS(int idx, int depth) { // 백트래킹
		
		if(depth == 3) {
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					if(comb[ans[i]][ans[j]]) {
						return;
					}
				}
			}
			
			result++;
			return;
		}
		
		for(int i=idx; i<N; i++) {
			if(visit[i]) continue;
			visit[i] = true;
			ans[depth] = arr[i];
			DFS(i+1, depth+1);
			visit[i] = false;
		}
		
	} // End of DFS
} // End of Main class