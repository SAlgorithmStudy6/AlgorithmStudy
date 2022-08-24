import java.util.*;
import java.io.*;

public class Main_14889_스타트와링크 {
	static int arr[][];
	static boolean visit[];
	static int N;
	
	static int result = Integer.MAX_VALUE; // 최소값 결과 값
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_14889.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visit = new boolean[N];
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		DFS(0, 0);
		System.out.println(result);
	} // End of main
	
	private static void DFS(int index, int depth) {
		
		if(depth == N/2) {
			diff();
			return;
		}
		
		// DFS에 index값을 매개변수로 줌으로써
		// 가장 처음 시작되는 i의 값이 더이상 뒤로 가지않고 무조건 앞으로 만 이동함
		// 예를 들어서 1,2 1,3 1,4에서 1의 조합은 모두 이루어졌기때문에
		// 앞자리에서 나온 1은 뒤에서도 더이상 나오지 않음
		// 조합 자체가 번갈아가면서 나오기 때문
		for(int i=index; i<N; i++) {
			
			if(visit[i]) continue;
			
			visit[i] = true;
			DFS(i + 1, depth + 1);
			visit[i] = false; 
		}
		
	} // End of DFS
	
	static void diff() {
		int team_start = 0;
		int team_link = 0;
		
		for(int i=0; i<N-1; i++) {
			for(int j=i+1; j<N; j++) {
				
				if(visit[i] == true && visit[j] == true) {
					team_start += arr[i][j];
					team_start += arr[j][i];
				}
				else if( visit[i] == false && visit[j] == false) {
					team_link += arr[i][j];
					team_link += arr[j][i];
 				}			
			}
		}
		
		int val = Math.abs(team_start - team_link);
		
		result = Math.min(val, result);
	} // End of diff

} // End of Main class