package programmers.lv3;

import java.util.ArrayList;

public class 양과늑대 {

    static ArrayList<ArrayList<Integer>> graph;
    static int maxValue = Integer.MIN_VALUE;
    static int[] pos;

    public int solution(int[] info, int[][] edges) {
        pos = info;
        graph = new ArrayList<>();
        for (int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edges.length; i++) {
            int parent = edges[i][0];
            int child = edges[i][1];
            graph.get(parent).add(child);
        }

        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp.add(0);
        dfs(0, 1, 0, temp);

        return maxValue;
    }

    static void dfs(int index, int sheep, int wolf, ArrayList<Integer> visited) {
        if (pos[index] == 0) {
            sheep += 1;
        } else {
            wolf += 1;
        }

        if (wolf >= sheep) {
            return;
        }

        maxValue = Math.max(sheep, maxValue);

        ArrayList<Integer> next = new ArrayList<Integer>(visited);
        next.remove(Integer.valueOf(index));
        if (!graph.get(index).isEmpty()) {
            next.addAll(graph.get(index));
        }

        for (int node : next) {
            dfs(node, sheep, wolf, next);
        }

    }

}
