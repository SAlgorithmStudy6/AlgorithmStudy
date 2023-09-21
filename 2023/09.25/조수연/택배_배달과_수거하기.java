import java.util.*;

class Test {
    public static void main(String[] args) {
        int cap = 4, n = 5;
        int[] deliveries = {1, 0, 3, 1, 2};
        int[] pickups = {0, 3, 0, 4, 0};
        System.out.println((new Solution().solution(cap, n, deliveries, pickups)));
    }
}

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        Stack<Integer> dStack = new Stack<>();
        Stack<Integer> pStack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (deliveries[i] > 0) dStack.add(i);
        }

        for (int i = 0; i < n; i++) {
            if (pickups[i] > 0) pStack.add(i);
        }

        while (true) {
            if (!dStack.isEmpty() && !pStack.isEmpty()) { // 배달과 수거할 물품이 있는 경우
                answer += (Math.max(dStack.peek(), pStack.peek()) + 1) * 2L;
            }
            if (!dStack.isEmpty() && pStack.isEmpty()) { // 수거할 물품만 있는 경우
                answer += (dStack.peek() + 1) * 2L;
            }
            if (dStack.isEmpty() && !pStack.isEmpty()) { // 배달할 물품만 있는 경우
                answer += (pStack.peek() + 1) * 2L;
            }
            if (dStack.isEmpty() && pStack.isEmpty()) break;

            int dCount = cap;

            while (!dStack.isEmpty()) {
                if (dCount <= 0) break;
                if (deliveries[dStack.peek()] <= dCount) dCount -= deliveries[dStack.pop()];
                else {
                    deliveries[dStack.peek()] -= dCount;
                    break;
                }
            }

            int pCount = 0;

            while (!pStack.isEmpty()) {
                if (pCount == cap) break;
                if (pickups[pStack.peek()] + pCount <= cap) pCount += pickups[pStack.pop()];
                else {
                    pickups[pStack.peek()] -= cap - pCount;
                    break;
                }
            }
        }
        return answer;
    }
}