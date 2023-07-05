package baekjoon.gold.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 세그먼트 트리 개념 https://yoongrammer.tistory.com/103
public class 최솟값과최댓값 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[] numbers, minTree, maxTree;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        minTree = new int[N * 4];
        maxTree = new int[N * 4];

        initMin(1, N, 1);
        initMax(1, N, 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(min(1, N, 1, a, b) + " " + max(1, N, 1, a, b));
        }
    }

    static int initMin(int start, int end, int node) {
        if (start == end) {
            return minTree[node] = numbers[start];
        }
        int mid = (start + end) / 2;

        return minTree[node] = Math.min(
                initMin(start, mid, node * 2),
                initMin(mid + 1, end, node * 2 + 1)
        );
    }

    static int initMax(int start, int end, int node) {
        if (start == end) {
            return maxTree[node] = numbers[start];
        }
        int mid = (start + end) / 2;

        return maxTree[node] = Math.max(
                initMax(start, mid, node * 2),
                initMax(mid + 1, end, node * 2 + 1)
        );
    }

    public static int min(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return Integer.MAX_VALUE;
        }
        if (start == end) {
            return numbers[start];
        }
        if (left <= start && end <= right) {
            return minTree[node];
        }
        int mid = (start + end) / 2;
        return Math.min(
                min(start, mid, node * 2, left, right),
                min(mid + 1, end, node * 2 + 1, left, right)
        );
    }

    public static int max(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
            return 0;
        }
        if (start == end) {
            return numbers[start];
        }
        if (left <= start && end <= right) {
            return maxTree[node];
        }
        int mid = (start + end) / 2;
        return Math.max(
                max(start, mid, node * 2, left, right),
                max(mid + 1, end, node * 2 + 1, left, right)
        );
    }

}
