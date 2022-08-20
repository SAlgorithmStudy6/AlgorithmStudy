package third;

import java.util.ArrayList;
import java.util.List;

public class Copy {

	public static void main(String[] args) {
		Copy4 s4 = new Copy4();
		int n = 8;
		int[][] lighthouse = {{1,2},{1,3},{1,4},{5,6},{5,7},{5,8}};
		System.out.println(s4.solution(n,lighthouse));

	}

}

class Copy4 {
	static List<Integer>[] lhList;
	static int N;
	static boolean[] visited;
	static int[] linked;
	static int answer;
	static boolean check;
	
    public int solution(int n, int[][] lighthouse) {
        answer = 0;
        lhList = new ArrayList[n+1];
        linked = new int[n+1];
        N = n;
        
        for (int i = 0; i < n+1; i++) {
			lhList[i] = new ArrayList<Integer>();
		}
        
        for (int i = 0; i < lighthouse.length; i++) {
				lhList[lighthouse[i][0]].add(lighthouse[i][1]);
				lhList[lighthouse[i][1]].add(lighthouse[i][0]);
		}
        
        check = false;
        
        for (int i = 1; i <= n; i++) {
        	int comb[] = new int[i];
        	visited = new boolean[n+1];
        	combination(1,0,comb,0);
        	
        	if (check) {
				break;
			}
        }
        
        
        return answer;
    }
    
    public void combination(int start,int size,int[] comb,int sum) {
    	
    	if(size == comb.length) {
    		if (sum == N) {
    			boolean isLight = check(comb);
    			if (isLight) {
    				answer = comb.length;
    				check = true;
    			}
			}
    		return;
    	}
    	
    	for (int i = start; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true; 
				comb[size] = i;
				combination(i+1,size+1,comb,sum+lhList[i].size()+1);
				visited[i] = false; 
			}
		}
    }
    
    public boolean check(int[] comb) {
    	linked = new int[N+1];
    	int count = 0;
    	for (int i = 0; i < comb.length; i++) {
    		if (linked[comb[i]] != 1) {
    			linked[comb[i]] = 1;
        		count++;
			}
    	
    		for (int value : lhList[comb[i]]) {
    			if (linked[value] != 1) {
        			linked[value] = 1;
            		count++;
    			}
			}
		}
    
    	if (count == N) {
			return true;
		}
    	
    	return false;
    }
}