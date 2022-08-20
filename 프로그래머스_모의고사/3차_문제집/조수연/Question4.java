package third;

import java.util.*;

public class Question4 {

	public static void main(String[] args) {
		Solution4 s4 = new Solution4();
		int n = 10;
		int[][] lighthouse = { { 4, 1 }, { 5, 1 }, { 5, 6 }, { 7, 6 }, { 1, 2 }, { 1, 3 }, { 6, 8 }, { 2, 9 },{ 9, 10 } };
		//{ 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, { 5, 6 }, { 5, 7 }, { 5, 8 }
		System.out.println(s4.solution(n, lighthouse));
	}

}

class Solution4 {

	static List<Integer>[] lh;
	static int[] matched;
	static boolean[] visited;
	static List<Integer> indexList;
	
	public int solution(int n, int[][] lighthouse) {
		int answer = 0;

		lh = new ArrayList[n + 1];
		matched = new int[n+1];
		Arrays.fill(matched, -1);
		
		for (int i = 1; i <= n; i++) {
			lh[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < lighthouse.length; i++) {
			lh[lighthouse[i][0]].add(lighthouse[i][1]);
			lh[lighthouse[i][1]].add(lighthouse[i][0]);
		}
		
		for (int i = 1; i <= n; i++) {
			if (lh[i].size() == 1) continue; 
			visited = new boolean[n+1];
			if (matching(i)) {
				answer++;
			}
		}
		
		
		for (int i = 0; i < matched.length; i++) {
			if (check(matched[i],n)) {
				answer--;
			}
		}
		
	
		return answer;
	}
	
	public boolean matching(int value) {
		for (Integer node : lh[value]) {
			if (visited[node]) continue;
			visited[node] = true;
			if (matched[node] == -1 || matching(matched[node])) {
				matched[node] = value;
				return true;
			}
			
		}
		return false;
	}
	
	public boolean check(int value, int n) {
		boolean[] checked = new boolean[n+1];
		if (value != -1) { 	
			
			int count = lh[value].size();
			for (Integer node : lh[value]) {
				for (int i = 0; i < matched.length; i++) {
					if (node == matched[i] && !checked[node]) {
						count--;
					}
				}
			}
			
			if (count == 0) {
				return true;
			}
		}
		
		return false;
	}
}