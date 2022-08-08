import java.io.*;
import java.util.*;

public class Main {

	static int time = 0;
	static int[] walk = { -1, 1 };
	static Queue<Integer> distance = new LinkedList<Integer>();
	static boolean[] visited = new boolean[100001];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(token.nextToken());
		int K = Integer.parseInt(token.nextToken());
		
		bfs(N,K);

	}

	static void bfs(int N, int K) {

		distance.offer(N);
		visited[N] = true;

		while (!distance.isEmpty()) {
			
			int size = distance.size();
			
			for (int i = 0; i < size; i++) {
				
				int currentN = distance.poll();
				
				if (currentN == K) {
					System.out.println(time);
					return;
				}

				if (currentN - 1 >= 0 && !visited[currentN - 1]) { // N위치가 K위치보다 클 때
					visited[currentN - 1] = true;
					distance.offer(currentN - 1);
				}

				if (currentN + 1 <= 100000 && !visited[currentN + 1]) { // 걷기
					visited[currentN + 1] = true;
					distance.offer(currentN + 1);
				}

				if (currentN * 2 <= 100000 && !visited[currentN * 2]) { // 순간이동
					visited[currentN * 2] = true;
					distance.offer(currentN * 2);
				}
			}

			time++;
		}
	}

}
