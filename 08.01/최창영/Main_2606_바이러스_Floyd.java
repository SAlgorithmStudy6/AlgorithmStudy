import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/2606

// 목표 : 1번 컴퓨터가 웜 바이러스에 걸렸을 때, 
// 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.

public class Main_2606_바이러스_Floyd {
	static int arr[][];
	
	static int N, M;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_2606.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
		M = Integer.parseInt(br.readLine()); // 컴퓨터 쌍의 수
		
		arr	= new int[N+1][N+1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			arr[y][x] = 1;
			arr[x][y] = 1;
		}
		
		floyd();

		// 0과 자신인 1은 제외, 2부터 시작
		for(int i=2; i<N+1; i++) {
			if(arr[1][i] == 1) {				
				result++;
			}
		}
		
		System.out.println(result);
		
	}// End of main
	
	// k를 통해 갈 수 있는 곳을 모두 연결
	static void floyd() {
		
		for(int k=0; k<N+1; k++) {
			for(int i=0; i<N+1; i++) {
				for(int j=0; j<N+1; j++) {
										
					if(arr[i][k] == 1 && arr[k][j] == 1) {
						arr[i][j] = 1;
					}
					
				}
			}
		}
		
	} // End of floyd
	
} // End of class
