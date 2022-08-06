import java.util.*;
import java.io.*;

public class Main_2644_촌수계산_DFS {
	static List<List<Integer>> list;
	static int node[];
	static boolean visit[];
	static int N;
	static int a, b;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/2644.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		node = new int[N+1];
		visit = new boolean[N+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		int M = Integer.parseInt(br.readLine());
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			list.get(y).add(x);
			list.get(x).add(y);
		}
		
		DFS(a);
		if(node[b] == 0) System.out.println(-1);
		else System.out.println(node[b]);
	} // End of main
	
	private static void DFS(int num) {
		visit[num] = true;
		
		for(int nodeNum : list.get(num)) {
			
			if(!visit[nodeNum]) {
				node[nodeNum] = node[num] + 1;
				DFS(nodeNum);
			}
		}
		
	} // End of DFS
} // End of Main class