import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] consult;
	static int max = Integer.MIN_VALUE;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		consult = new int[N + 1][2]; //퇴사날이 1일부터이므로 N+1배열 생성

		for (int i = 1; i <= N; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			consult[i][0] = Integer.parseInt(token.nextToken());
			consult[i][1] = Integer.parseInt(token.nextToken());
		}
		int[] arr = new int[8];
		
		for (int i = 1; i <= N; i++) {
			combination(i,0);
		}
		
		
		System.out.println(max);

	}

	static void combination(int day, int sum) {
		int T = consult[day][0]; 
		int P = consult[day][1]; //해당 날에 상담 걸리는 날
		
		if (day + T > N + 1) { //상담하는데 필요한 시간이 퇴사전 날을 넘어가면 그 전날까지 상담요소 걸리는 합을 max에 갱신
			max = Math.max(max,sum);
			return;
		} else if (day + T == N + 1) {//상담마지막이 퇴사전날이라면 해당 날에 비용까지 더해서 max에 갱신
			max = Math.max(max,sum+P);
			return;
		}

		for (int i = day + T; i <= N; i++) { //i는 해당 날짜 + 상담하는게 걸리는시간으로 시작
			combination(i,sum+P);
		}
	}

}