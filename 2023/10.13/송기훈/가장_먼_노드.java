package programmers.lv3;

import java.util.ArrayList;
import java.util.Arrays;

//class Test {
//
//    public static void main(String[] args) {
//        int n = 6;
//        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
//        System.out.println(new 가장_먼_노드().solution(n, edge));
//    }
//
//}

public class 가장_먼_노드 {

    int[] distance;
    ArrayList<ArrayList<Integer>> nodeList;

    public int solution(int n, int[][] edge) {
        int answer = 0;
        distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        nodeList = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            nodeList.add(new ArrayList<Integer>());
        }

        for (int[] e : edge) {
            nodeList.get(e[0]).add(e[1]);
            nodeList.get(e[1]).add(e[0]);
        }

        distance[1] = 1;
        dfs(1, 1);

        int maxDepth = 1;
        for (int i = 2; i < n + 1; i++) {
            if (distance[i] > maxDepth) {
                maxDepth = distance[i];
                answer = 1;
            } else if (distance[i] == maxDepth) {
                answer += 1;
            }
        }

        return answer;
    }

    public void dfs(int node, int depth) {

        ArrayList<Integer> nodes = nodeList.get(node);

        for (int n : nodes) {
            if (distance[n] > depth + 1) {
                distance[n] = Math.min(distance[n], depth + 1);
                dfs(n, depth + 1);
            }
        }

    }

}
