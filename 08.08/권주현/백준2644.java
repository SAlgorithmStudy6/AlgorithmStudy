import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int n,m, cnt;
	static ArrayList<ArrayList<Integer>> list=new ArrayList<>();
	static Queue<Integer> q=new LinkedList<>();
	static int[] visited;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Scanner scan=new Scanner(System.in);
        n=scan.nextInt();		//전체 사람의 수
        int a=0,b=0;			//촌수를 계산 해야하는 서로 다른 두사람의 번호
        a=scan.nextInt();		
        b=scan.nextInt();
        m=scan.nextInt();		//부모 자식간의 관계 개수
        visited=new int[n+1];
        for(int i=0;i<n+1;i++) {
        	list.add(new ArrayList<Integer>());
        }
        
        for(int i=0;i<m;i++) {
        	int q=scan.nextInt();
        	int w=scan.nextInt();
        	list.get(q).add(w);
        	list.get(w).add(q);
        }
        bfs(a);
        if(visited[b]==0) {
        	System.out.println(-1);
        }
        else System.out.println(visited[b]);
        
	}
	static void bfs(int i) {
		q.offer(i);
		while(!q.isEmpty()) {
			int now=q.poll();
//			System.out.println(list.get(now).size());
			for(int a=0;a<list.get(now).size();a++) {
				if(visited[list.get(now).get(a)]==0) {
					visited[list.get(now).get(a)]=visited[now]+1;
					q.offer(list.get(now).get(a));

				}
			}
		}
	}
}
