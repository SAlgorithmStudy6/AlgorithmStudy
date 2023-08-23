package baekjoon.gold.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 친구네트워크 {
    // 유니온 파인드
    // https://ssungkang.tistory.com/entry/Algorithm-%EC%9C%A0%EB%8B%88%EC%98%A8-%ED%8C%8C%EC%9D%B8%EB%93%9CUnion-Find

    static int T, F;
    static int[] parent, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            F = Integer.parseInt(br.readLine());
            HashMap<String, Integer> names = new HashMap<>();
            parent = new int[F * 2];
            count = new int[F * 2];
            Arrays.setAll(parent, i -> i);
            Arrays.fill(count, 1);
            int index = 0;

            for (int f = 0; f < F; f++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if (!names.containsKey(a)) {
                    names.put(a, index);
                    index += 1;
                }
                if (!names.containsKey(b)) {
                    names.put(b, index);
                    index += 1;
                }
                union(names.get(a), names.get(b));
                System.out.println(count[find(names.get(b))]);
            }
        }
    }

    static int find(int index) {
        // 타고 타고 올라가서 부모 노드를 찾는다
        if (parent[index] == index) {
            return index;
        } else {
            return parent[index] = find(parent[index]);
        }
    }

    static void union(int idxA, int idxB) {
        int parentA = find(idxA);
        int parentB = find(idxB);

        // 부모가 같으므로 이미 연결됨
        if (parentA == parentB) {
            return;
        }

        parent[Math.max(parentA, parentB)] = Math.min(parentA, parentB);
        count[Math.min(parentA, parentB)] += count[Math.max(parentA, parentB)];
    }

}