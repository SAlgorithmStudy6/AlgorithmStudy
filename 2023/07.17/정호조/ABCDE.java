import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ABCDE_13023 {
    static int N, M, ans;
    static boolean check;
    static int[] dist;
    static boolean[] vis;
    static ArrayList<ArrayList<Integer>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = 0;
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        for (int i = 0; i < N; i++) {
            vis = new boolean[N];
            check = false;
            vis[i] = true;
            getResult(i, 0);
            if (check) {
                ans = 1;
                break;
            }
        }

        System.out.println(ans);
    }

    static void getResult(int start, int cnt) {
        if (cnt >= 4) {
            check = true;
            return;
        }

        for (int i : list.get(start)) {
            if(!vis[i]){
                vis[i] = true;
                getResult(i, cnt + 1);
                vis[i] = false;
            }
        }

    }
}
