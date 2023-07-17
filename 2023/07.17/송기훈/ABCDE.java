package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ABCDE {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static ArrayList<ArrayList<Integer>> nodeList;
    static boolean flag;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nodeList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            nodeList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodeList.get(a).add(b);
            nodeList.get(b).add(a);
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            dfs(i, 0);
            visited[i] = false;
            if (flag) {
                break;
            }
        }

        if (flag) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static void dfs(int index, int depth) {
        if (depth == 4) {
            flag = true;
            return;
        }

        ArrayList<Integer> tempList = nodeList.get(index);
        for (int tempIndex : tempList) {
            if (!visited[tempIndex]) {
                visited[tempIndex] = true;
                dfs(tempIndex, depth + 1);
                visited[tempIndex] = false;
            }
        }
    }

}
