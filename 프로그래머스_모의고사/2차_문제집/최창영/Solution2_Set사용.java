import java.util.HashSet;

// HashSet => 테스트 1 〉 통과 (1788.48ms, 395MB)
// TreeSet => 테스트 1 〉 통과 (9572.95ms, 402MB)

// HashSet O(1)
// TreeSet O(logn) 
// 성능은 HashSet이 더 좋음
// 여기서는 Set을 쓰면 시간 초과

public class Solution2 {
	public static void main(String[] args) {
		Solution2 s = new Solution2();

		int topping[] = { 1, 2, 1, 3, 1, 4, 1, 2 };
		System.out.println(s.solution(topping));
	} // End of main

	public int solution(int[] topping) {
		int len = topping.length;
		int result = 0;
		int cheolsuArr[] = new int[len + 1];
		int brotherArr[] = new int[len + 1];

		HashSet<Integer> cheolsuSet = new HashSet<>();
		HashSet<Integer> brotherSet = new HashSet<>();

		for (int i = 0; i < len; i++) {
			cheolsuSet.add(topping[i]);
			cheolsuArr[topping[i]]++;
		}
		
		for (int i = 0; i < len; i++) {
			brotherSet.add(topping[i]);

			brotherArr[topping[i]]++;
			cheolsuArr[topping[i]]--;
			
			if (cheolsuArr[topping[i]] == 0) {
				cheolsuSet.remove(topping[i]);
			}

			if (cheolsuSet.size() == brotherSet.size())
				result++;
		}

		return result;
	} // End of solution
} // End of Main class