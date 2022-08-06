import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1697
// 목표 : 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.

public class Main_1697_숨바꼭질 {
	static Queue<Integer> que = new LinkedList<>();
	static int arr[] = new int[100001];
	
	static int N, K;
	static int min_time;
	static int next_time;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1697.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 순간이동은 *2 로 증가만 가능하므로, 뒤로가는 방법은 - 1밖에 없다.
		// 뒤로가는 것은 N - K로 계산하면됨. 방법의 수는 1가지
		if(N >= K) {
			System.out.println(N-K);
			return;
		}
		
		BFS();
		
		System.out.println(min_time);
		
	} // End of main
	
	private static void BFS() {
		min_time = Integer.MAX_VALUE/16; // 최단 시간
		que.offer(N);
		arr[N] = 1;
		
		while( !que.isEmpty() ) {
			int time = que.poll();
			
			if(min_time < arr[time]) {
				return;
			}
			
			for(int i=0; i<3; i++) {
				
				switch(i) {
					case 0: next_time = time + 1;
					 	break;
					case 1: next_time = time - 1;
						break;
					default: next_time = time * 2;
				}
				

				if(next_time == K) {
					min_time = arr[time];
				}
				

				if( Range_check() && (arr[next_time] == 0 || arr[next_time] == arr[time] + 1) ) {
					que.offer(next_time);
					arr[next_time] = arr[time] + 1;
				}

			}
		}
	}// End of BFS
	

	// 범위 체크
	static boolean Range_check() {
		return (next_time >= 0 && next_time <= 100000);
	}

	// 숨바꼭질2랑 범위가 달라서 체크해야됨
	// 숨바꼭질2은 테스트케이스 중에 10만이 없는데
	
	// 숨바꼭질1에서는 테스트 케이스 중에 next_time 범위가 10만 인 경우가 있기 때문에 
	// 신경써야됨.
	
} // End of class
