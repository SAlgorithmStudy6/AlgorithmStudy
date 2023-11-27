import java.util.*;


/**
 * 투포인터 문제
 * 1) 구매해야하는 보성 종류의 수를 구함
 * 2) 0 ~ gems.length까지 순회하면서 보석을 map에 저장
 * 3) gems[start] 보석이 2번 이상 나왔다면 그 횟수가 1이 될 때까지 -- 해주고 start는 ++
 * 3-1) end 번재에서 gems[start] 보석이 나왔기 때문에 gems[start]보석이 2 이상이 될 수 있음
 * 3-2) 따라서 현재까지 구매한 보석의 수가 한 개일 때까지 계속 start를 증가시켜줌
 * 4) 현재까지 구매한 보석의 종류(map의 크기)와 구매해야하는 보석 종류의 수가 같다면 length 갱신
 * 4-1) length를 갱신하지 않고 바로 break 해버리면 start가 더 증가했을 때 최소 길이의 경우의 수가 있음에도 불구하고 찾을 수 없음
 * 4-2) start는 0부터 시작하고 map.size() == resultSize일 때 length 크기 판별 조건식에서 length와 최소 길이(end - start)가 같은 경우를 포함하지 않으므로 시작점이 작은 것만 answer에 저장할 수 있음
 */
public class 보석쇼핑 {
    public static void main(String[] args) {
        String[][] gems = {{"A", "B", "B", "B", "B", "B", "B", "C", "B", "A"}, {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"}, {"AA", "AB", "AC", "AA", "AC"}, {"XYZ", "XYZ", "XYZ"}, {"ZZZ", "YYY", "NNNN", "YYY", "BBB"}};

        Solution s = new Solution();
        for (String[] gem : gems) {
            System.out.println(Arrays.toString(s.solution(gem)));
        }
    }

    static class Solution {
        public int[] solution(String[] gems) {
            int[] answer = new int[2];

            Set<String> gemsSet = new HashSet<>();
            Map<String, Integer> map = new HashMap<>();
            Collections.addAll(gemsSet, gems);
            int resultSize = gemsSet.size();

            int start = 0, length = gems.length + 1;

            for (int end = 0; end < gems.length; end++) {
                map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);

                while (map.get(gems[start]) > 1) {
                    map.put(gems[start], map.get(gems[start]) - 1);
                    start++;
                }

                if (map.size() == resultSize && length > end - start) {
                    length = end - start;
                    answer[0] = start + 1;
                    answer[1] = end + 1;
                }
            }

            return answer;
        }
    }
}
