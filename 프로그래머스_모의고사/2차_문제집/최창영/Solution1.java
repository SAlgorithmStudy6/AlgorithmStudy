public class Solution1 {
	static int ans[] = new int[3];
	static int numArr[];
	static int N;
	static int resultCount = 0;
	
	public static void main(String[] args) {
		Solution1 s = new Solution1();

		int number[] = {-3, -2, -1, 0, 1, 2, 3 };
		System.out.println(s.solution(number));
	} // End of main

	public int solution(int[] number) {
		N = number.length;
		numArr = number;
		
		DFS(0, 0);
		return resultCount;
	} // End of solution
	
	private static void DFS(int idx, int depth) {
		if(depth == 3) {
			int sum = 0;
			for(int i=0; i<3; i++) {
				sum += ans[i];
			}
			
			if(sum == 0) {
				resultCount++;
			}
			
			return;
		}
		
		
		for(int i=idx; i<N; i++) {
			ans[depth] = numArr[i];
			DFS(i+1, depth + 1);
		}
	} // End of DFS
} // End of Main class