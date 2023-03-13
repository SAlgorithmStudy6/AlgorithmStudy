import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1697
// 목표 : 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

// 숨바꼭질2랑 범위가 달라서 체크해야됨
// 숨바꼭질2은 테스트케이스 중에 10만이 없는데

// 숨바꼭질1에서는 테스트 케이스 중에 next_location 범위가 10만 인 경우가 있기 때문에 
// 신경써야됨.

public class Main_1697_숨바꼭질_DP {
	static int distance[] = new int[100001]; // 전체 거리
	static int N, K;	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1697.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 수빈이 위치
		K = Integer.parseInt(st.nextToken()); // 동생 위치
		
		if(N >= K) {
			System.out.println(N-K);
			return;
		}
		
		for(int i=0; i<N; i++) {
			distance[i] = N-i;
		}
		
		DP();
		System.out.print(distance[K]);
	} // End of main
	
	private static void DP() {
		for(int i=N+1; i<=K; i++) {
			int min;
			
			if(i % 2 == 0) {
				min = distance[i/2] + 1;
			}
			else {				
				min = Math.min(distance[(i+1)/2], distance[(i-1)/2]) +2;
			}
			
			distance[i] = Math.min(min,  distance[i-1]+1);
		}
	}// End of DP
} // End of class