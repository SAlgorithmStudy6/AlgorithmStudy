import java.util.*;
import java.io.*;

public class Main_1149_RGB거리_BottomUp {
	final static int Red = 0;
	final static int Green = 1;
	final static int Blue = 2;
		
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/1149.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int memo[][] = new int[N][3];

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			memo[i][Red] = Integer.parseInt(st.nextToken());
			memo[i][Green] = Integer.parseInt(st.nextToken());
			memo[i][Blue] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<N; i++) {
			memo[i][Red] += Math.min(memo[i-1][Green], memo[i-1][Blue]);
			memo[i][Green] += Math.min(memo[i-1][Red], memo[i-1][Blue]);
			memo[i][Blue] += Math.min(memo[i-1][Red], memo[i-1][Green]);
		}
	
		System.out.println(Math.min(memo[N-1][Red], Math.min(memo[N-1][Green], memo[N-1][Blue])));
	} // End of main
} // End of Main