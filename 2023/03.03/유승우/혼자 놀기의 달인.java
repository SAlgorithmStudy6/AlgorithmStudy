import java.util.*;

class Solution {
    public int solution(int[] cards) {
        // 방문처리
        boolean[] visited = new boolean[cards.length];
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < cards.length; i++) {
            int pos = i;
            int cnt = 0;
            // 한 점에서 계속출발
            while (!visited[pos]) {
                visited[pos] = true;
                pos = cards[pos] - 1;
                cnt++;
            }
            if (cnt > 0) list.add(cnt);
        }
        list.sort(Collections.reverseOrder());

        // 카드를 다 뽑았으면 서로 개수를 센다.
        if (list.size() > 1) return list.get(0) * list.get(1);
        else return 0;
    }
}