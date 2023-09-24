package programmers.lv3;

import java.util.Arrays;
import java.util.HashSet;

class Test {
    public static void main(String[] args) {
        String[] user_Id = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id = {"fr*d*", "abc1**"};

        new 불량사용자().solution(user_Id, banned_id);
    }
}


public class 불량사용자 {

    String[] userId;
    String[] bannedId;
    boolean[] visited;
    HashSet<String> hashSet;

    public int solution(String[] user_id, String[] banned_id) {
        userId = user_id;
        bannedId = banned_id;
        visited = new boolean[user_id.length];
        hashSet = new HashSet<>();

        for (int i = 0; i < bannedId.length; i++) {
            bannedId[i] = bannedId[i].replace('*', '.');
        }

        dfs(0, "");

        return hashSet.size();
    }

    public void dfs(int depth, String ids) {
        if (depth == bannedId.length) {
            if (!ids.isBlank() && !ids.isEmpty()) {
                String[] result = ids.split(" ");
                Arrays.sort(result);

                StringBuilder sb = new StringBuilder();
                for (String r : result) {
                    sb.append(r);
                }
                hashSet.add(sb.toString());
            }

            return;
        }

        for (int i = 0; i < userId.length; i++) {
            if (visited[i]) continue;
            if (!userId[i].matches(bannedId[depth])) continue;

            visited[i] = true;
            dfs(depth + 1, ids + " " + userId[i]);
            visited[i] = false;
        }
    }

}
