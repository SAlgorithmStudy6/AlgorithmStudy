import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/16953

/*
 * - 뒤에서부터 거꾸로 접근한다.
B의 일의 자리가 1이면 (B % 10 = 1) 일의 자리를 없앤다. (B /= 10)
그렇지 않고, B가 2로 나뉜다면 (B % 2 = 0) 2로 나누어준다. (B /= 2)
둘 다 못한다면, B는 절대로 A가 될 수 없다.
A가 B로 나아가는 수단은 위 두 가지 밖에 없기 때문이다.
 */

public class Main_16953_AtoB_BFS {
	static Queue<Long> que = new LinkedList<>();		
	
	static long A, B;
	static int count = 1;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_16953.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());		

		BFS();
		System.out.println(count);
	}
	
	static void BFS() {
		que.offer(B);
		
		while( !que.isEmpty() ) {
			long num = que.poll();
			String str = Long.toString(num);	
			
			// 탈출 조건
			if(num == A) {
				return;
			}
			else if(num < A) {
				count = -1;
				return;
			}
			
			if(num % 2 == 0) {
				num /= 2;
				que.offer(num);
				count ++;
			}
			else if(str.charAt(str.length() -1) == '1') {
				num = Long.parseLong( str.substring(0, str.length() - 1) );
				que.offer(num);
				count ++;
			}
			else {
				count = -1;
				return;
			}
			
	
				
		}
		
	} // End of BFS
	
} // End of class
