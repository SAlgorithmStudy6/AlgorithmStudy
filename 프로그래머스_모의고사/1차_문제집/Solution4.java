import java.util.Arrays;

class Solution4 {
	public static void main(String[] args) {
		Solution4 s = new Solution4();
		
		int beginning[][] = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
		int target[][] = {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};	
		System.out.println(s.solution(beginning, target));
	}
	
    public int solution(int[][] beginning, int[][] target) {
        int answer = 0;
        
        for(int num[] : beginning) {
        	System.out.println(Arrays.toString(num));
        }
        
        return answer;
    } // End of solution
} // End of Solution4