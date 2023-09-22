import java.util.*;

class Test {
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "*rodo", "******", "******"};
        System.out.println((new Solution().solution(user_id, banned_id)));
    }
}

class Solution {
    static Set<String> set;
    Map<String, Integer> map;
    List<Integer>[] banList;
    boolean[] visited;

    public int solution(String[] user_id, String[] banned_id) {
        set = new HashSet<>();
        map = new HashMap<>();
        banList = new List[banned_id.length];
        visited = new boolean[user_id.length];

        for (int i = 0; i < banned_id.length; i++) {
            banList[i] = new ArrayList<>();
        }

        for (int i = 0; i < user_id.length; i++) {
            map.put(user_id[i], i);
        }

        for (int i = 0; i < banned_id.length; i++) {
            for (int j = 0; j < user_id.length; j++) {
                if (banned_id[i].length() != user_id[j].length()) continue;

                boolean isMatch = true;

                for (int k = 0; k < banned_id[i].length(); k++) {
                    if (banned_id[i].charAt(k) == '*') continue; // *은 패스

                    if (banned_id[i].charAt(k) != user_id[j].charAt(k)) { // 문자가 일치하지 않는 경우
                        isMatch = false;
                        break;
                    }
                }

                if (isMatch) banList[i].add(map.get(user_id[j]));
            }
        }

        int[] banArr = new int[banned_id.length];

        comb(0, banned_id.length, banArr);

        return set.size();
    }

    public void comb(int idx, int size, int[] banArr) {
        if (idx == size) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < banArr.length; i++) {
                sb.append(banArr[i]);
            }

            // 중복 되는 경우의 수를 체크하기 위해 정려
            char[] charArr = sb.toString().toCharArray();
            Arrays.sort(charArr);

            String result = new String(charArr);

            set.add(result);

            return;
        }

        for (int i = 0; i < banList[idx].size(); i++) {
            if (!visited[banList[idx].get(i)]) {
                visited[banList[idx].get(i)] = true;
                banArr[idx] = banList[idx].get(i);

                comb(idx + 1, size, banArr);

                visited[banList[idx].get(i)] = false;
            }
        }
    }
}