import java.util.*;
import java.io.*;

public class 보호필름_2112_모의SW테스트 {

	static int[][] film, copy;
	static int D, W, k, ans, injectioncnt;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=t; tc++) {
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			film = new int[D][W];
			copy = new int[D][W];
			ans = Integer.MAX_VALUE;
			for(int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(k==1) {
				ans = 0;
			} else {
				for(int i=0; i<D; i++) {
					for(int j=0; j<W; j++) {
						copy[i][j] = film[i][j];
					}
				}
				dfs(0, D, 0);
			}
			System.out.printf("#%d %d\n", tc, ans);

		}
	}
	
	static void dfs(int index,int m, int cnt) {
		if(index == m) {
			count(copy, cnt);
			return;
		}
		
		//약품을 안 쓸 경우
		dfs(index+1, m, cnt);
		
		//A약품을 주입할 경우
		injection(index, 0);
		dfs(index+1, m, cnt+1);
		
		//B약품을 주입할 경우
		injection(index, 1);
		dfs(index+1, m, cnt+1);
		
		//배열 원상복귀
		for(int i=0; i<W; i++) {
			copy[index][i] = film[index][i];
		}
	}
	
	//약품 주입
	static void injection(int index, int chemicals) {
		for(int i=0; i<W; i++) {
			copy[index][i] = chemicals;
		}
	}
	
	//k번 연속하는지 확인
	static void count(int[][] arr, int cnt) {
		int result = 0;
		for(int i=0; i<W; i++) {
			int kcnt = 1;
			for(int j=0; j<D-1; j++) {
				if(arr[j][i] == arr[j+1][i]) {
					kcnt++;
				} else kcnt = 1;
				
				if(kcnt == k) {
					result ++;
					kcnt=1;
					break;
				} 
			}
			if(result != i+1) return;
		}
		
		if(result == W) {
//			System.out.println("cnt : " + cnt);
			ans = Math.min(ans, cnt);	//약품 주입 횟수를  temp에 넣어준 후 반환
		}
	}
}
