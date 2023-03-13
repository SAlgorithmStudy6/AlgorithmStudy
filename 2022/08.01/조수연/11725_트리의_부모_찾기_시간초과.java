import java.io.*;
import java.util.*;

public class Main2 {

	static int N;
	static int[][] pair;
	static boolean[] visited = new boolean[100001];
	static int[] node = new int[100001];
	static Queue<Integer> current_floor = new LinkedList<Integer>(); 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		pair = new int[N - 1][2];
		visited[1] = true;
		node[1] = 1;

		for (int i = 0; i < pair.length; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			pair[i][0] = Integer.parseInt(token.nextToken());
			pair[i][1] = Integer.parseInt(token.nextToken());
		}

		current_floor.add(1);

		while (!current_floor.isEmpty()) {
			int parent = current_floor.poll();
			
			for (int i = 0; i < pair.length; i++) {
				if (pair[i][0] == parent) {
					if (!visited[pair[i][1]]) {
						visited[pair[i][1]] = true;
						node[pair[i][1]] = parent;
						current_floor.add(pair[i][1]);
					}
				} else if (pair[i][1] == parent) {
					if(!visited[pair[i][0]]) {
						visited[pair[i][0]] = true;
						node[pair[i][0]] = parent;
						current_floor.add(pair[i][0]);
					}
				}
			}
		}

		for (int i = 2; i <= N ; i++) {
			bw.write(String.valueOf(node[i])+"\n");
		}
		
		bw.flush();
		bw.close();
		
	}

}
