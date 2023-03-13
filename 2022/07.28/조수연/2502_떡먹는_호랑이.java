import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static int D; // 넘어온 날
	public static int K; // 떡 개수

	public static int[] k_arr;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/1143.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());

		D = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());

		k_arr = new int[D];

		search();

	}

	private static void search() {

		for (int i = 1; i <= K / 2; i++) { // A
			for (int j = i + 1; j < K; j++) { // B
				k_arr[0] = i;
				k_arr[1] = j;
				for (int k = 2; k < k_arr.length; k++) {
					k_arr[k] = k_arr[k - 2] + k_arr[k - 1];
				}
				if (k_arr[D - 1] == K) {
					System.out.println(k_arr[0]); // 첫째 날
					System.out.println(k_arr[1]); // 둘째 날
					System.exit(0);
				}
			}

		}
	}
}
