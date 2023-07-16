package baekjoon.silver.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class XOR삼형제1 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N;
    static boolean flag;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        ArrayList<Integer> inputs = new ArrayList<>();
        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            inputs.add(n);
        }

        for (int input : inputs) {
            flag = false;
            N = input;
            for (int target = input; target > 0; target--) {
                visited = new boolean[input + 1];

                dfs(1, 0, target);

                if (flag) {
                    break;
                }
            }
        }


    }

    static boolean check(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 2; i++) {
            for (int j = i + 1; j < numbers.size() - 1; j++) {
                for (int k = j + 1; k < numbers.size(); k++) {
                    if ((numbers.get(i) ^ numbers.get(j) ^ numbers.get(k)) == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static void dfs(int index, int depth, int target) {
        if (flag) {
            return;
        }

        if (depth == target) {
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                if (visited[i]) {
                    numbers.add(i);
                }
            }
            if (check(numbers)) {
                flag = true;
                System.out.println(numbers.size());
                StringBuilder sb = new StringBuilder();
                for (int e : numbers) {
                    sb.append(e);
                    sb.append(" ");
                }
                sb.deleteCharAt(sb.length() - 1);
                System.out.println(sb);
            }
            return;
        }

        for (int i = index; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, depth + 1, target);
                visited[i] = false;
            }
        }
    }

}
