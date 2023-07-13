package baekjoon.gold.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class 개미굴 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;


    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        TreeMap<String, Object> root = new TreeMap<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());

            TreeMap<String, Object> targetNode = root;

            for (int j = 0; j < K; j++) {
                String node = st.nextToken();

                if (!targetNode.containsKey(node)) {
                    targetNode.put(node, new TreeMap<>());
                }

                targetNode = (TreeMap<String, Object>) targetNode.get(node);

            }
        }
        printResult(root, 0);

    }

    static void printResult(TreeMap<String, Object> treeMap, int depth) {
        for (Object object : treeMap.keySet()) {
            for (int i = 0; i < depth; i++) {
                System.out.print("--");
            }
            System.out.println(object);
            printResult((TreeMap<String, Object>) treeMap.get(object), depth + 1);
        }
    }

}
