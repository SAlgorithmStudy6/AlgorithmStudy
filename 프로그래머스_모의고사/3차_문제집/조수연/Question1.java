package third;

public class Question1 {

	public static void main(String[] args) {
		Solution s1 = new Solution();
		int a = 3;
		int b = 1;
		int n = 20;
		System.out.println(s1.solution(a, b, n));
	}

}

class Solution {
	public int solution(int a, int b, int n) {
		int answer = 0;
		while (true) {
			int cola = (n / a) * b; //콜라로 교환
			n %= a; //빈병
			answer += cola; //콜라 갯수 만큼 더하기
			n += cola; 
			if (cola <= 0) { //교환할 수 있는 콜라가 0개면 무한루프 탈출
				break;
			}
		}
		return answer;
	}
}