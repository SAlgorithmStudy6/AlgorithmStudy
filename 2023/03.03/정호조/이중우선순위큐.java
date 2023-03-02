import java.util.Collections;
import java.util.PriorityQueue;

public class 이중우선순위큐 {
    public static void main(String[] args) {
        String[] operations = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        Solution so = new Solution();
        System.out.println(so.solution(operations)[0] + " , " + so.solution(operations)[1]);
    }

    static class Solution {
        public int[] solution(String[] operations) {
            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < operations.length; i++) {
                if(operations[i].charAt(0) == 'I'){
                    minHeap.add(Integer.parseInt(operations[i].substring(2)));
                    maxHeap.add(Integer.parseInt(operations[i].substring(2)));
                } else if(operations[i].charAt(0) == 'D'){
                    if(maxHeap.size() > 0){
                        if(operations[i].charAt(2) == '1'){
                            int max = maxHeap.peek();
                            maxHeap.remove(max);
                            minHeap.remove(max);
                        } else {
                            int min = minHeap.peek();
                            maxHeap.remove(min);
                            minHeap.remove(min);
                        }
                    }
                }
            }
            int[] answer = new int[2];

            if(maxHeap.size() > 0){
                answer[0] = maxHeap.peek();
                answer[1] = minHeap.peek();
            } else {
                answer[0] = 0;
                answer[1] = 0;
            }

            return answer;
        }
    }
}
