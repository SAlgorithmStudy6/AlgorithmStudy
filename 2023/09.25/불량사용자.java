import java.util.*;

public class 불량사용자 {
    public static void main(String[] args) {
        String[] user_id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"*rodo", "*rodo", "******"};

        Solution s = new Solution();

        s.solution(user_id, banned_id);
    }

    static class Solution {

        String[] userIds;
        String[] bannedIds;
        Set<Set<String>> result = new HashSet<>();

        public int solution(String[] user_id, String[] banned_id) {
            userIds = user_id;
            bannedIds = banned_id;

            dfs(new HashSet<>(), 0);

            System.out.println("최종 : " + result.toString());

            return result.size();
        }

        void dfs(Set<String> set, int depth) {
            if (depth == bannedIds.length) {
//                System.out.println(Arrays.toString(set.toArray()));
                result.add(set);
                System.out.println(set);

//                System.out.println(result.toString());
                System.out.println();
                return;
            }

            for (int i = 0; i < userIds.length; i++) {
                if (set.contains(userIds[i])) {
                    continue;
                }

                if (check(userIds[i], bannedIds[depth])) {
                    set.add(userIds[i]);
                    dfs(new HashSet<>(set), depth + 1);
                    set.remove(userIds[i]);
                }
            }
        }

        boolean check(String userId, String bannedId) {
            if (userId.length() != bannedId.length()) {
                return false;
            }

            boolean match = true;
            for (int i = 0; i < userId.length(); i++) {
                if (bannedId.charAt(i) != '*' && userId.charAt(i) != bannedId.charAt(i)) {
                    match = false;
                    break;
                }
            }

            return match;
        }
    }
}
