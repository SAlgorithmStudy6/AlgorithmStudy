import java.util.*;
import java.io.*;

// 각 날짜별 나머지가 되는 값을 구하면 됨
// 1 2 3일 경우 15의 나머지가 1이 되는 값 && 28의 나머지가 2가 되는 값 && 19의 나머지가 3이 되는 값
// 이 조건이 부합하는 값을 찾으면 됨
// 15 28 19 의 최대 공배수는 7980임

public class Main_1476_날짜계산 {
	private static final int earth = 15;
	private static final int sun = 28;
	private static final int moon = 19;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/1476.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int E = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// https://j1w2k3.tistory.com/1340 참고
		//1. 식의 설계 
		int n = earth * sun * moon;
		int N1 = n/earth;
		int N2 = n/sun;
		int N3= n/moon;
		
		//2. 선형 합동식 특수해
		int x1 = find_mod(N1, earth);
		int x2 = find_mod(N2, sun);
		int x3 = find_mod(N3, moon);
		
		//3. 연립합동식의 해
		int x = (E*N1*x1) + (S*N2*x2) + (M*N3*x3);
		
		//4. 결과 출력
		int result = x % n;
		System.out.print( result == 0 ? n : result);
	} // End of main
	
	// 나머지가 1이 되는 값을 찾아야 함
	private static int find_mod(int num1, int num2) {
		for(int i=1; i<100; i++) {
			if((num1*i) % num2 == 1) {
				return i;
			}
		}
		return 0;
	} // End of find_mod
} // End of Main class