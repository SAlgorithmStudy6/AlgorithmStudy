import java.util.*;
import java.io.*;

// https://www.acmicpc.net/problem/16953

public class Main_16953_AtoB_일반구현 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_16953.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());		
		int count = 1;
			
		// B가 A보다 크면 계속 반복
		while(B != A) {
			String str = Long.toString(B);	
		
			// B가 짝수 일때
			if(B % 2 == 0) {
				B /= 2;
			}
			else if(str.charAt(str.length() -1) == '1') {
				B = Long.parseLong( str.substring(0, str.length() - 1) );
			}
			// 홀수일 경우 -1 출력
			else {
				count = -1;
				break;
			}
			
			
			// 탈출 조건
			// 반복하면서 B가 계속 작아지면서, A보다 작아질 경우, 틀린것으로 판단
			// -1을 출력
			if(B < A) {
				count = -1;
				break;
			}
			
			count ++;
		}		

		System.out.println(count);
	}
}
