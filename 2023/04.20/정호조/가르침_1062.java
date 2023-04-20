import java.io.*;
import java.util.*;

public class 가르침_1062 {
    static boolean[] vis;
    static int N, K, ans;
    static String[] str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        ans = 0;
        vis = new boolean[26];
        str = new String[N];

        if (K < 5) {    //K < 5 이면 읽을 수 있는 단어 없음
            System.out.println("0");
        } else if (K == 26) {   //K==26이면 모든 단어 다 읽을 수 있음
            System.out.println(N);
        } else {
            for (int i = 0; i < N; i++) {
                str[i] = br.readLine();
            }
            vis['a' - 'a'] = true;
            vis['n' - 'a'] = true;
            vis['t' - 'a'] = true;
            vis['i' - 'a'] = true;
            vis['c' - 'a'] = true;

            combination(0, 0, 26, K - 5);
            System.out.println(ans);
        }
    }

    //조합으로 K-5개의 알파벳 뽑아서 가능한 단어 추출
    static void combination(int index, int start, int n, int m) {
        if (index == m) {
            int result = 0;
            for (int i = 0; i < N; i++) {
                boolean possible = true;
                for (int j = 4; j < str[i].length() - 4; j++) {
                    if (!vis[str[i].charAt(j) - 'a']) {
                        possible = false;
                        break;
                    }
                }
                if (possible) {
                    result++;
                }
            }
            ans = Math.max(ans, result);
            return;
        }

        for (int i = start; i < n; i++) {
            if (!vis[i]) {
                vis[i] = true;
                combination(index + 1, i+1, n, m);
                vis[i] = false;
            }
        }

    }
}
