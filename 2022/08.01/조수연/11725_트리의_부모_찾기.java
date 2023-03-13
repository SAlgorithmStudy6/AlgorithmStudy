import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static boolean[] visited = new boolean[100001]; //중복체크
	static int[] node = new int[100001]; //노드 배열
	static Queue<Integer> current_floor = new LinkedList<Integer>(); 

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		List<Integer> pair[] = new ArrayList [N+1]; //노드 짝 배열
		
		for (int i = 0; i < pair.length; i++) {
			pair[i] = new ArrayList<Integer>(); 
		}
		
		visited[1] = true; 
		node[1] = 1;

		for (int i = 0; i < N-1; i++) { 
			StringTokenizer token = new StringTokenizer(br.readLine());
			int pair1 = Integer.parseInt(token.nextToken());
			int pair2 = Integer.parseInt(token.nextToken());
			
			pair[pair1].add(pair2);
			pair[pair2].add(pair1);
		}
	
		current_floor.add(1);

		while (!current_floor.isEmpty()) {
			
			int parent = current_floor.poll();
			
			for (Integer i : pair[parent]) { //짝 배열에 current_floor poll값을 가져온 것에 노드 값 삽입
			
				if(!visited[i]) {
					visited[i] = true;
					node[i] = parent;
					current_floor.add(i);
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
