import java.util.HashSet;
import java.util.Set;

public class 연속부분수열합의개수2 {
	static int[] elements = { 7, 9, 1, 1, 4 };
	static int answer = 0;

	public static void main(String[] args) {

		Set<Integer> set = new HashSet<>();

		int[] ele = new int[elements.length * 2];
		for (int i = 0; i < elements.length * 2; i++) {
			ele[i] = elements[i % elements.length];
		}

		for (int len = 1; len <= elements.length; len++) {
			for (int start = 0; start < elements.length; start++) {
				int temp = 0;
				for (int point = start; point < start + len; point++) {
					temp += ele[point];
				}
				set.add(temp);
			}
		}
		answer = set.size();

		System.out.println(answer);
	}
}
