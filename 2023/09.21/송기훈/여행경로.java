package programmers.lv3;

import java.util.*;

public class 여행경로 {

    boolean[] visited;
    ArrayList<String> answer;
    int maxDepth;

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        answer = new ArrayList<>();
        maxDepth = tickets.length;

        dfs(0, "ICN", "ICN", tickets);

        Collections.sort(answer);

        return answer.get(0).split(" ");
    }

    public void dfs(int depth, String now, String path, String[][] tickets) {
        if (depth == maxDepth) {
            answer.add(path);
            return;
        }

        for (int i = 0; i < maxDepth; i++) {
            String start = tickets[i][0];
            String end = tickets[i][1];
            if (!visited[i] && start.equals(now)) {
                visited[i] = true;
                dfs(depth + 1, tickets[i][1], path + " " + end, tickets);
                visited[i] = false;
            }
        }
    }
}
