import java.util.*;

public class Main {
	static Scanner scan=new Scanner(System.in);
	static ArrayList<ArrayList<Integer>> list =new ArrayList<>();
	static int[] visited;
	static int cnt;
	public static void main(String[] args) {
		int n=scan.nextInt();
		int m=scan.nextInt();
		visited=new int[n+1];
		
		for(int i=0;i<n+1;i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0;i<m;i++) {	//arraylist에 연결된 노드 기록
			int a=scan.nextInt();
			int b=scan.nextInt();
			list.get(a).add(b);
			list.get(b).add(a);
		}
		dfs(1);
		
		for(int i=0;i<n+1;i++) {
			if(visited[i]==1) {
				cnt++;
			}
		}
		System.out.println(cnt-1);
	}
	static void dfs(int i) {
			visited[i]=1;
			for(int m:list.get(i)) {
				if(visited[m]==0) {
					dfs(m);
				}
		}
	}
	
		}
