import java.util.*;
import java.io.*;

public class Main_2468_안전영역 {
	static List<Integer> list = new ArrayList<>();
	static int dirX[] = {0, 0, -1, 1}; // 상 하 좌 우
	static int dirY[] = {-1, 1, 0, 0}; // 상 하 좌 우
	static boolean visit[][];
	static int arr[][];
	
	static int N, nowX, nowY;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2468.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if( !list.contains(arr[i][j])) {
					list.add(arr[i][j]);
				}
			}
		}
		
		// 아무지역도 물에 잠기지 않을 수도 있을 때를 생각해서 0을 삽입
		list.add(0);
		Collections.sort(list, Collections.reverseOrder());
		
		int max = Integer.MIN_VALUE / 16;
		for(int num : list) {
			visit = new boolean[N][N];
			int safeArea = 0;
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					
					if( !visit[i][j] && arr[i][j] >= num) {
						DFS(i, j, num);
						safeArea++;
					}
				}
			}

			max = Math.max(max, safeArea);
		}
		
		System.out.println(max);
	} // End of main
	
	static void DFS(int x, int y, int num) {
		visit[x][y] = true;
		
		for(int i=0; i<4; i++) {
			nowX = x + dirX[i];
			nowY = y + dirY[i];
			
			if(range_check() && visit[nowX][nowY] == false && arr[nowX][nowY] >= num) {
				DFS(nowX, nowY, num);
			}
		}
		
	} // End of DFS
	
	static boolean range_check() {
		return (nowX >= 0 && nowY >= 0 && nowX < N && nowY < N); 	
	} // End range_check

} // End of Main class 