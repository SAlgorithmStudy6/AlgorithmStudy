import java.util.*;

class Test {
    public static void main(String[] args) {
        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        new Solution().solution(gems);
    }
}

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        HashMap<String, Integer> gemMap = new HashMap<>();

        for (String gem : gems) {
            gemMap.put(gem, gemMap.getOrDefault(gem, 0) + 1);
        }

        int start = 0;
        int size = gemMap.size();
        int count = 100000;
        HashMap<String, Integer> map = new HashMap<>();

        // 투포인터 개념으로 접근
        for (int end = 0; end < gems.length; end++) {
            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);

            while (true) { // 시작부분을 기준으로 중복 요소 제거하고 start++
                if (map.get(gems[start]) <= 1) break;

                map.put(gems[start],map.get(gems[start]) - 1);
                start++;
            }

            if (map.size() == size && count > end - start) {
                count = end - start;
                answer[0] = start + 1;
                answer[1] = end + 1;
            }
        }
        return answer;
    }
}