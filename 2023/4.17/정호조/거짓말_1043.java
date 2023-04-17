import java.io.*;
import java.util.*;

public class 거짓말_1043 {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int ans = 0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        boolean[] knowReal = new boolean[N + 1];
        HashSet<Integer>[] party = new HashSet[M + 1];

        for (int i = 1; i <= M; i++) {
            party[i] = new HashSet<>();
        }

        //진실을 아는 사람
        st = new StringTokenizer(br.readLine());
        int knowCnt = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= knowCnt; i++) {
            knowReal[Integer.parseInt(st.nextToken())] = true;
        }

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        //사람들마다 연관관계 지어줌(union-find)
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int partyCnt = Integer.parseInt(st.nextToken());

            if (partyCnt <= 1) {
                party[i].add(Integer.parseInt(st.nextToken()));
            } else {
                int[] temp = new int[partyCnt];
                for (int j = 0; j < partyCnt; j++) {
                    temp[j] = Integer.parseInt(st.nextToken());
                }

                for (int j = 0; j < partyCnt-1; j++) {
                    int a = temp[j];
                    int b = temp[j + 1];
                    if (find(a) != find(b)) { //둘의 parent가 같지 않으면, 즉 연관 관계가 없으면 관계 만들어줌
                        union(a, b);
                    }
                    party[i].add(a);
                    party[i].add(b);
                }
            }
        }

        //진실을 아는 사람과 함께 파티에 참석한적 있는지 체크
        boolean[] vis = new boolean[N + 1];   //불필요한 탐색 방지
        for (int i = 1; i <= N; i++) {
            if (knowReal[i] && !vis[i]) { //진실을 아는 사람이면
                int root = find(i); //연관관계 중 가장 최상위 부모
                for (int j = 1; j <= N; j++) {
                    if (find(j) == root) {    //j번째 사람이 root와 관계가 있으면 진실을 아는 사람과 같이 파티에 참석한 적 있는 것
                        knowReal[j] = true;
                        vis[j] = true;
                    }
                }
            }
        }

        //가능한 파티 수를 구함
        for (int i = 1; i <= M; i++) {
            boolean check = true;
            for (int j : party[i]) {
                if (knowReal[j]) {
                    check = false;
                    break;
                }
            }
            if (check) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    static int find(int num) {
        if (parents[num] == num) {
            return parents[num];
        }
        parents[num] = find(parents[num]);
        return parents[num];
    }

    static void union(int a, int b) {
        parents[find(b)] = a;
    }
}
