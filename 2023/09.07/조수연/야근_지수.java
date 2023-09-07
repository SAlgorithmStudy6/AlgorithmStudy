import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        if (Arrays.stream(works).sum() <= n) return 0; // 남은 작업량보다 퇴근까지 남은 시간이 더 많으면

        long answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue(Collections.reverseOrder());

        for (Integer work : works) {
            pq.add(Long.valueOf(work));
        }

        for (int i = 0; i < n; i++) {
            long work = pq.poll();

            if (work - 1 != 0) pq.add(work - 1);
        }

        while (!pq.isEmpty()) {
            answer += Math.pow(pq.poll(), 2);
        }
        return answer;
    }
}