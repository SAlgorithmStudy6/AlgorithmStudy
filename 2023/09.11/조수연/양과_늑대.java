import java.util.*;

class Test {
    public static void main(String[] args) {
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        new Solution().solution(info, edges);
    }
}

class Solution {
    int[] info;
    int[][] graph;
    int[] visited;
    int answer = 0;

    public int solution(int[] info, int[][] edges) {
        this.info = info.clone();
        graph = new int[info.length][info.length];
        visited = new int[info.length];

        for (int i = 0; i < edges.length; i++) {
            graph[edges[i][0]][edges[i][1]] = 1;
        }

        List<Integer> list = new ArrayList<>();
        list.add(0);

        if (info[0] == 0) dfs(0, 1, 0, list);
        else dfs(0, 0, 1, list);

        return answer;
    }

    public void dfs(int node, int sheep, int wolf, List<Integer> nextList) {
        if (sheep <= wolf) return; // dfs 탐색 종료

        answer = Math.max(answer, sheep);

        List<Integer> list = new ArrayList<>();

        list.addAll(nextList);
        list.remove(Integer.valueOf(node)); // 탐색할 위치에서 현재 인덱스는 뺴고 시작 -> 되돌아가기 X

        for (int i = 0; i < graph[node].length; i++) {
            if (graph[node][i] == 1) {
                list.add(i);
            }
        }

        for (int next : list) {
            if (info[next] == 0) dfs(next, sheep + 1, wolf, list);
            else dfs(next, sheep, wolf + 1, list);
        }
    }
}