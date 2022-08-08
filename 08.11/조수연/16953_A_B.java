import java.io.*;
import java.util.*;

public class Main {

	static int count = 0;
	static boolean[] visited = new boolean[1000000001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());

		long A = Long.parseLong(token.nextToken());
		long B = Long.parseLong(token.nextToken());

		bfs(A, B);
		
		System.out.println(count);

	}

	static void bfs(long A, long B) {
		Queue<Long> current = new LinkedList<Long>();
		current.add(A);
		visited[(int) A] = true;

		while (!current.isEmpty()) {
			int cSize = current.size();

			for (int i = 0; i < cSize; i++) {

				long num = current.poll();

				if (num == B) {
					count++;
					return;
				}

				if (num * 2 <= B && !visited[(int) (num * 2)]) {
					current.offer(num * 2);
					visited[(int) (num * 2)] = true;
				}

				if ((num * 10) + 1 <= B && !visited[(int) ((num * 10) + 1)]) {
					current.offer((num * 10) + 1);
					visited[(int) ((num * 10) + 1)] = true;
				}
			}
			count++;
		}

		count = -1;
	}

}
