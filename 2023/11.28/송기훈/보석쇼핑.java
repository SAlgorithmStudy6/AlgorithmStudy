package programmers.lv3;

import java.util.*;

public class 보석쇼핑 {

    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        HashSet<String> gemSet = new HashSet<>(Arrays.asList(gems));
        int kind = gemSet.size();

        int start = 0;
        int size = Integer.MAX_VALUE;

        Map<String, Integer> gemMap = new HashMap<>();

        for (int end = 0; end < gems.length; end++) {
            gemMap.put(gems[end], gemMap.getOrDefault(gems[end], 0) + 1);

            // 1개보다 많으면 1개 빼고 start index를 하나 올린다
            while (gemMap.get(gems[start]) > 1) {
                gemMap.put(gems[start], gemMap.get(gems[start]) - 1);
                start++;
            }

            // gemMap의 크기와 종류가 같다 & 더 작은 사이즈면 갱신
            if (gemMap.size() == kind && size > (end - start)) {
                size = end - start;
                answer[0] = start + 1;
                answer[1] = end + 1;
            }
        }

        return answer;
    }

}
