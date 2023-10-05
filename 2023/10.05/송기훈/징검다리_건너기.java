package programmers.lv3;

import java.util.Arrays;

class Test {

    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        new 징검다리_건너기().solution(stones, k);
    }

}

public class 징검다리_건너기 {
    public int solution(int[] stones, int k) {
        int answer = 0;

        int minValue = 1;
        int maxValue = 200000000;

        while (minValue <= maxValue) {
            int mid = (minValue + maxValue) / 2;

            if (check(stones, k, mid)) {
                minValue = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                maxValue = mid - 1;
            }

        }

        return answer;
    }

    boolean check(int[] stones, int k, int value) {
        int count = 0;

        for (int i = 0; i < stones.length; i++) {
            if (stones[i] < value) {
                // value 명 만큼 건널 수 없음 -> 점프
                count += 1;
            } else {
                count = 0;
            }

            if (count == k) {
                // 점프 최대거리
                return false;
            }
        }

        return true;
    }

}
