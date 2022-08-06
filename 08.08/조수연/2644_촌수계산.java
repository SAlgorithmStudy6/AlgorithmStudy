import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int p1;
	static int p2;
	static int m;
	static boolean[] visited = new boolean[101];
	static List<Integer>[] relation;
	static int distance = -1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		relation = new ArrayList[n+1];
		
		for (int i = 1; i <= n; i++) {
			relation[i] = new ArrayList<Integer>();
		}
		
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		p1 = Integer.parseInt(token.nextToken());
		p2 = Integer.parseInt(token.nextToken());
		
		m = Integer.parseInt(br.readLine());
		
		
		for (int i = 0; i < m; i++) {
			
			token = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(token.nextToken());
			int y = Integer.parseInt(token.nextToken());
			
			relation[x].add(y);
			relation[y].add(x);
		}
		
		dfs(p1,p2,0);
		
		System.out.println(distance);
				
	}
	
	static void dfs(int start, int target, int count) {
		if (start == target) {
			distance = count;
			return;
		}
		visited[start] = true;
		
		for (int i = 0; i < relation[start].size(); i++) {
			int next = relation[start].get(i);
			if (!visited[next]) {
				dfs(next,target,count+1);
			}
		}
	}
}
