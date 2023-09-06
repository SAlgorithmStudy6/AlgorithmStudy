import java.util.Collections;
import java.util.PriorityQueue;

public class 야근지수 {
    public static void main(String[] args) {
        int n = 3;
        int[] works = {1, 1};

        System.out.println(solution(n, works));
    }

    static long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < works.length; i++) {
            pq.add(works[i]);
        }

        for(int i=0; i<n; i++){
            if(pq.isEmpty()) break;

            int work = pq.poll() - 1;
            if(work != 0) pq.add(work);
        }

        int size = pq.size();
        for(int i=0; i<size; i++){
            answer += Math.pow(pq.poll(), 2);
        }

        return answer;
    }
}
