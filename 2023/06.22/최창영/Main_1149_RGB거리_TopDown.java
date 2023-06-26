import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/1149

public class Main_1149_RGB거리_TopDown {
	final static int Red = 0;
	final static int Green = 1;
	final static int Blue = 2;
	
	static int arr[][];
	static int memo[][];
	static int N;
	
	// 각 색깔의 모든 경우의 수를 파악해야 함
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/1149.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		memo = new int[N][3];
		arr = new int[N][3];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][Red] = Integer.parseInt(st.nextToken());
			arr[i][Green] = Integer.parseInt(st.nextToken());
			arr[i][Blue] = Integer.parseInt(st.nextToken());
		}
		
		memo[0][Red] = arr[0][Red];
		memo[0][Green] = arr[0][Green];
		memo[0][Blue] = arr[0][Blue];
		
		System.out.println( Math.min( DP(N-1, Red), Math.min(DP(N-1, Green), DP(N-1, Blue))   ));
	} // End of main
	
	private static int DP(int N, int color) {
		
		if(memo[N][color] == 0) {
			
			if(color == Red) {
				memo[N][Red] = Math.min( DP(N-1, Green), DP(N-1, Blue)) + arr[N][Red];
			}
			else if(color == Green) {
				memo[N][Green] = Math.min( DP(N-1, Red), DP(N-1, Blue)) + arr[N][Green];
			}
			else if(color == Blue) {
				memo[N][Blue] = Math.min( DP(N-1, Red), DP(N-1, Green)) + arr[N][Blue];
			}
		}
		
		return memo[N][color];
	} // End of DP
} // End of Main