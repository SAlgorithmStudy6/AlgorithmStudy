package baekjoon;

import java.util.*;
import java.io.*;

public class dfs스페셜저지_16964 {
	
	static ArrayList<ArrayList<Integer>> numList;
	static boolean[] vis;
	static int[] num;
	static boolean ans;
	static int N, index;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		vis = new boolean[N + 1];
		num = new int[N];
		ans = true;
		index = 1;

		numList = new ArrayList<>();
		for(int i = 0 ; i <= N ; ++i) {
			numList.add(new ArrayList<>());
		}
		
		for(int i = 0 ; i < N - 1 ; ++i) {
			st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			numList.get(node).add(value);
			numList.get(value).add(node);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; ++i) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1);
		
		if(ans) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
		
	}

	private static void dfs(int idx) {
		if(vis[idx]) {
			return;
		}
		
		vis[idx] = true;
		
		HashSet<Integer> set = new HashSet<>();
		for(int nextNum : numList.get(idx)) {
			if(vis[nextNum]) continue;
			set.add(nextNum);
		}
		
		if(set.size() == 0) {
			return;
		}
		
		if(set.contains(num[index])) {
			dfs(num[index++]);
		} else {
			ans = false;
		}
	}
}
