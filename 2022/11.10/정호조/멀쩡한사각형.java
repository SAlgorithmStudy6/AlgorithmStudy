import java.util.*;

public class 멀쩡한사각형 {

	public static void main(String[] args) {
		System.out.println(solution(8, 12));
	}

	public static long solution(int w, int h) {
		long answer = 1;

		long tmp = Math.max(w, h);
		long tmp2 = Math.min(w, h);

		w = (int) tmp2;
		h = (int) tmp;
		answer = w * h - (w + h - getGCD(h, w));

		return answer;
	}

	static int getGCD(int x, int y) {
		if (x % y == 0)
			return y;

		return getGCD(y, x % y);
	}
}
