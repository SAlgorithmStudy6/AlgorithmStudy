import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] arr = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        System.out.println(Arrays.toString(solution.solution(arr)));
    }

    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());

        for (String oper : operations) {
            String[] input = oper.split(" ");
            int num = Integer.parseInt(input[1]);
            switch (input[0]) {
                case "I": {
                    // 큐에 데이터 넣기
                    maxQueue.offer(num);
                    minQueue.offer(num);
                    break;
                }
                case "D": {
                    // 큐에서 최솟값 삭제
                    if (!maxQueue.isEmpty() && !minQueue.isEmpty()) {
                        int deleteNum;
                        if (num < 0) {
                            deleteNum = minQueue.peek();
                        }
                        // 큐에서 최대값 삭제
                        else {
                            deleteNum = maxQueue.peek();
                        }
                        minQueue.remove(deleteNum);
                        maxQueue.remove(deleteNum);
                    }
                    break;
                }
            }
        }
        int[] answer = new int[2];

        if(!maxQueue.isEmpty() && !minQueue.isEmpty()){
            answer[0] = maxQueue.peek();
            answer[1] = minQueue.peek();
        }

        return answer;
    }
}