import java.util.ArrayList;
import java.util.HashSet;

class 불량사용자 {
    HashSet<HashSet<String>> result;
    ArrayList<ArrayList<String>> bannedUserList;

    public int solution(String[] user_id, String[] banned_id) {
        result = new HashSet<HashSet<String>>();
        bannedUserList = new ArrayList<ArrayList<String>>();
        for (String bannedId : banned_id) {
            bannedUserList.add(getMatchesId(bannedId, user_id));
        }
        backTracking(new HashSet<String>(), 0);

        return result.size();
    }

    ArrayList<String> getMatchesId(String bannedId, String[] user_id) {
        bannedId = bannedId.replace("*", ".");

        ArrayList<String> valueList = new ArrayList<>();
        for (String userId : user_id) {
            if (userId.matches(bannedId)){
                valueList.add(userId);
            }
        }
        return valueList;
    }

    void backTracking(HashSet<String> set, int depth) {
        if (depth == bannedUserList.size()) {
            result.add(new HashSet<>(set));
            return;
        }

        for (String userId : bannedUserList.get(depth)) {
            if (!set.contains(userId)) {
                set.add(userId);
                backTracking(set, depth + 1);
                set.remove(userId);
            }
        }
    }
}