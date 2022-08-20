package third;

public class Question3 {

	public static void main(String[] args) {
		Solution3 s3 = new Solution3();
		int distance = 12;
		int[][] scope = { { 7, 8 }, { 4, 6 }, { 11, 10 } };
		int[][] times = { { 2, 2 }, { 2, 4 }, { 3, 3 } };
		System.out.println(s3.solution(distance, scope, times));
	}
}

class Solution3 {
	public int solution(int distance, int[][] scope, int[][] times) {
		int answer = distance;
		int[] hwarang = new int[distance + 1]; // 위치가 1부터 시작이기 때문에 +1

		for (int i = 0; i < scope.length; i++) {
			int start = Math.min(scope[i][0], scope[i][1]); // 정렬되어있지 않기 때문에 시작점 종점 세팅
			int end = Math.max(scope[i][0], scope[i][1]);
			for (int j = start; j <= end; j++) { //범위 만큼 반복
				int location = j % (times[i][0] + times[i][1]); //시간범위 넘어가면 다시 0부터 초기화
				if (location != 0) { //location이 0이되면 해당 시간때는 휴식상태
					if (location - times[i][0] <= 0) { //근무시간보다 작거나 같을 경우에는 근무상태
						hwarang[j] = 1;
					}
				}
			}
		}

		for (int i = 1; i < hwarang.length; i++) { //해당 m일때 근무하고 있는지 체크
			if (hwarang[i] == 1) { 
				answer = i;
				break;
			}
		}
		return answer;
	}
}