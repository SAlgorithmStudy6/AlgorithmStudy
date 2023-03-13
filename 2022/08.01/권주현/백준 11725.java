import java.util.*;

public class Main {
	static Scanner scan=new Scanner(System.in);
	static ArrayList<ArrayList<Integer>> list =new ArrayList<>();
	static int[] visited;
	static int[] parent;
	static int cnt;
	public static void main(String[] args) {
		int n=scan.nextInt();
		visited=new int[n+1];
		parent=new int[n+1];

		for(int i=0;i<n+1;i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0;i<n-1;i++) {	//arraylist에 연결된 노드 기록
			int a=scan.nextInt();
			int b=scan.nextInt();
			list.get(a).add(b);
			list.get(b).add(a);
		}
		for(int k=1;k<n+1;k++) {
			if(visited[k]==0) {
		dfs(k);
			}
		}
		for(int i=2;i<n+1;i++) {
			System.out.println(parent[i]);
		}
	}
	static void dfs(int i) {
			visited[i]=1;
			for(int m:list.get(i)) {
				if(visited[m]==0) {
					parent[m]=i;
					dfs(m);
				}
		}
	}
	
		}
