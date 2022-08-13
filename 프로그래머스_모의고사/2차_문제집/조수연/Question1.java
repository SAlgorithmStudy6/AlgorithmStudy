package second;


public class Question1 {

	public static void main(String[] args) {
		int[] number = { -1, 1, -1, 1 };
		Solution s1 = new Solution();
		System.out.println(s1.solution(number));
	}

}

class Solution {
	static int answer;
	static boolean[] visited;
	static int[] number;

	public int solution(int[] number) {
		answer = 0;
		visited = new boolean[number.length];
		permulation(number, 0, 0, 0);
		return answer;
	}

	public void permulation(int[] number, int start, int size, int sum) { //숫자 조합
		if (size == 3) { //3개 뽑고 합이 0이면 answer++
			if (sum == 0) {
				answer++;
			}
			return;
		}

		for (int i = start; i < number.length; i++) { //start 변수를 선언해서 그 다음 반복문 실행 시 앞에 인덱스를 점프하고 실행
			if (!visited[i]) {     				      //visited를 통해 중복으로 선택 불가능
				permulation(number, i + 1, size + 1, sum + number[i]);
				visited[i] = false;
			}
		}
	}
}
