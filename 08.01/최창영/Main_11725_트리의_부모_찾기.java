import java.util.*;
import java.io.*;

// 뒤가 1이면, 앞자리 x값을 1을 넣음
// 만약 y자리가 0이 아니라면 x자리에 y값이 들어감

public class Main_11725_트리의_부모_찾기 {
	static int N;
	static int node[];
	static List<List<Integer>> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/11725.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		node = new int[N+1];
		for(int i=0; i<N+1; i++) list.add(new ArrayList<>());

		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			list.get(x).add(y);
			list.get(y).add(x);
		}

		node[1] = 1;
		BFS();
		for(int i=2; i<N+1; i++) sb.append(node[i]).append('\n');

		bw.write(sb.toString()); bw.close();
	} // End of main
	
	private static void BFS() {
		Deque<Integer> que = new LinkedList<>();
		que.offer(1);
		
		while(!que.isEmpty()) {
			int nodeNum = que.poll();
			
			for(int num : list.get(nodeNum)) {
				if(node[num] == 0) {
					node[num] = nodeNum;
					que.offer(num);
				}
			}
			
		}
	
	} // End of BFS
} // End of Main class