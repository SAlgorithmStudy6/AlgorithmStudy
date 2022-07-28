import java.util.*;
import java.io.*;

public class Main_2502_떡_먹는_호랑이 {
	static int memo[];
	static int D; static int K;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/2502.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		D = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		memo = new int[D]; // 넘어논 날 만큼 배열 생성
		memo[D - 1] = K; // 배열 떡의 마지막 부분은 마지막 날 호랑이에게 준 떡의 개수
		DP(1);
	} // End of main

	private static void DP(int ans) {
		memo[0] = ans;

		for (int i=ans; i<K; i++) {
			memo[1] = i;
			for (int j=2; j<D-1; j++) {
				memo[j] = memo[j-1] + memo[j-2];
			}

			if (memo[D-1] == memo[D-2] + memo[D-3]) {
				System.out.println(memo[0]);
				System.out.println(memo[1]);
				return;
			}
		}

		DP(ans + 1);
	} // End of DP
} // End of Main class