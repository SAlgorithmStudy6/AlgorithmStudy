import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 이분탐색
 * 1) 용액 배열을 오름차순으로 정렬해줌 -> 현재 두 용액을 더한 값이 0보다 크면 right--, 작으면 left++ 해주기 위함
 * 2) while 조건식이 left < right이기 때문에 출력하는 두 용액은 오름차순임이 보장
 */
public class 두용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] solution = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(solution);

        int left = 0, right = solution.length - 1, ans1 = 0, ans2 = 0;
        int result = Integer.MAX_VALUE;

        while (left < right) {
            int cur = solution[left] + solution[right];
            int absCur = Math.abs(cur);
            if (result > absCur) {
                result = absCur;
                ans1 = solution[left];
                ans2 = solution[right];
            }

            if (cur > 0) right--;
            else if (cur < 0) left++;
            else break;
        }

        System.out.println(ans1 + " " + ans2);
    }
}
