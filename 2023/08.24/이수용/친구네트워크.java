import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

// 유니온 파인드 알고리즘
// https://velog.io/@suk13574/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98Java-%EC%9C%A0%EB%8B%88%EC%98%A8-%ED%8C%8C%EC%9D%B8%EB%93%9CUnion-Find-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98

//https://www.acmicpc.net/problem/4195

class 친구네트워크 {
    private static int T;
    private static int F;
    private static int[] parent;
    private static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; t++){
            F = Integer.parseInt(br.readLine());
            // 3개의 친구관계에서 모두다른 이름이 나오면 6명
            parent = new int[F * 2];
            count = new int[F * 2];
            for(int i = 0; i < F * 2; i++){
                parent[i] = i;
            }
            Arrays.fill(count, 1);
            int index = 0;
            HashMap<String, Integer> map = new HashMap<>();
            for(int f = 0; f < F; f++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();
                if(!map.containsKey(friend1)){
                    map.put(friend1, index++);
                }
                if(!map.containsKey(friend2)){
                    map.put(friend2, index++);
                }
                sb.append(union(map.get(friend1), map.get(friend2)) + "\n");
            }
        }
        System.out.println(sb);

    }
    static int find(int x){
        if(parent[x] == x) return x;
        else{
            return find(parent[x]);
        }
    }

    static int union(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b){
            return count[a];
        } else if (a < b) {
            // a가 b의 루트 노드가 됨
            parent[b] = a;
            count[a] += count[b];
            return count[a];
        }else {
            parent[a] = b;
            count[b] += count[a];
            return count[b];
        }
    }
}