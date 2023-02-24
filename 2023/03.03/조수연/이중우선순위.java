import java.util.Collections;
import java.util.PriorityQueue;

public class Problem {
    public static void main(String[] args) {
        String[] operations = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        new SolutionJava().solution(operations);
    }
}

class SolutionJava {
    public int[] solution(String[] operations) {
        int[] answer = {};
        PriorityQueue<Integer> minPq = new PriorityQueue<>(); //오름차순
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder()); //내림차순

        for (String operation : operations) {
            String command = operation.split(" ")[0]; //삽입, 삭제 명령어
            int data = Integer.parseInt(operation.split(" ")[1]);

            switch (command) {
                case "I":
                    minPq.offer(data);
                    maxPq.offer(data);
                    break;
                case "D":
                    switch (data) {
                        case 1:
                            if (!maxPq.isEmpty()) {
                                minPq.remove(maxPq.peek()); //minPq에서 maxPq.peek 부분도 같이 제거해주기
                                maxPq.poll();
                            }
                            break;
                        case -1:
                            if (!minPq.isEmpty()) {
                                maxPq.remove(minPq.peek()); //maxPq에서 minPq.peek 부분도 같이 제거해주기
                                minPq.poll();
                            }
                            break;
                    }
                    break;
            }
        }
        if (maxPq.isEmpty() && minPq.isEmpty()) answer = new int[]{0, 0}; //큐가 비어있으면 0,0
        else answer = new int[]{maxPq.peek(), minPq.peek()};
        return answer;
    }
}
