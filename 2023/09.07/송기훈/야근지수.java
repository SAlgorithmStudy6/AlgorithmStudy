package programmers.lv3;

import java.util.Collections;
import java.util.PriorityQueue;

public class 야근지수 {

    public long solution(int n, int[] works) {
        long answer = 0;

        // 내림차순 PQ
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int work : works) {
            pq.offer(work);
        }

        while (n-- > 0) {
            int work = pq.poll();

            // 제일 큰 게 0이니까 더 줄일게 없다
            if (work == 0) break;

            work -= 1;
            pq.offer(work);
        }

        for (int work : pq) {
            long workL = (long) work;
            answer += workL * workL;
        }

        return answer;
    }

}
