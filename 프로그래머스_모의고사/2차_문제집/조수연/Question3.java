package second;

import java.util.*;

public class Question3 {

	public static void main(String[] args) {
		Solution3 s3 = new Solution3();
		int n = 5;
		int[][] roads = { { 1, 2 }, { 1, 4 }, { 2, 4 }, { 2, 5 }, { 4, 5 } };
		int[] sources = { 1, 3, 5 };
		int destination = 5;
		System.out.println(Arrays.toString(s3.solution(n, roads, sources, destination)));
	}

}

class Solution3 {
	boolean[] visited; //그래프 방문 체크
	int[] answer;
	LinkedList<Integer>[] graph; //그래프

	public int[] solution(int n, int[][] roads, int[] sources, int destination) {
		answer = new int[sources.length];

		graph = new LinkedList[n+1];;        //인접리스트로 구현, 인접배열 사용시 메모리초과
		
		for (int i = 0; i <= n; i++) {
			graph[i] = new LinkedList<Integer>();
		}

		for (int i = 0; i < roads.length; i++) { //양방향
			graph[roads[i][0]].add(roads[i][1]);
			graph[roads[i][1]].add(roads[i][0]);
		}

		for (int i = 0; i < sources.length; i++) { //visited초기화 후에 bfs시작
			visited = new boolean[n + 1];
			answer[i] = (bfs(sources[i], destination,n));
		}

		return answer;
	}

	public int bfs(int source, int destination,int n) {

		int count = 0;
		boolean isReturn = false; //목적지 찾으면 바로 빠져나오는 flag
		
		Queue<Integer> queue = new LinkedList(); 
		queue.offer(source);       //초기 위치 세팅
		visited[source] = true;
		
		while (!queue.isEmpty()) {
			
			int qSize = queue.size();
			
			for (int i = 0; i < qSize; i++) { //queue사이즈 만큼 반복
				int node = queue.poll();
				
				if (node == destination) { //poll값이 목적지만 return
					return count;
				}
				
				for (Integer link : graph[node]) { //graph[node]에서 목적지 찾기
					if (!visited[link]) {
						
						if (link == destination) { //찾으면 바로 return -> 문제의 핵심
							count++;
							return count;
						}
						visited[link] = true; //없으면 해당요소 queue에 추가
						queue.add(link);
					}
				}
			}
			count++;
			
		}
		
		if (!isReturn) { //찾지 못하면 -1 return
			count = -1;
		}
		return count;
	}

}