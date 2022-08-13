package second;


public class Question2 {

	public static void main(String[] args) {
		Solution2 s2 = new Solution2();
		int[] topping = { 1, 2, 1, 3, 1, 4, 1, 2 };
		System.out.println(s2.solution(topping));
	}

}

class Solution2 {
	public int solution(int[] topping) {
		int answer = 0;

		boolean[] cChecked = new boolean[10001]; //10000종류 토핑
		int[] chulsu = new int[1000000]; //topping길이 1,000,000

		for (int i = 0; i < topping.length; i++) { //철수영역
			if (i == 0) {
				chulsu[i] = 1;
				cChecked[topping[i]] = true;
			} else {
				if (!cChecked[topping[i]]) { 
					cChecked[topping[i]] = true;
					chulsu[i] = chulsu[i - 1] + 1;
				} else {
					chulsu[i] = chulsu[i - 1];
				}
			}
		}

		int[] brother = new int[1000000];
		boolean[] bChecked = new boolean[10001];

		for (int i = topping.length - 1; i >= 0; i--) { //동생영역
			if (i == topping.length - 1) {
				brother[i] = 1;
				bChecked[topping[i]] = true;
			} else {
				if (!bChecked[topping[i]]) {
					bChecked[topping[i]] = true;
					brother[i] = brother[i + 1] + 1;
				} else {
					brother[i] = brother[i + 1];
				}
			}
		}

		for (int i = 0; i < topping.length - 1; i++) { //값 비교
			int cCount = chulsu[i];
			int bCount = brother[i + 1];
			
			
			if (cCount == bCount) {
				answer++;
			}

		}

		return answer;
	}
}