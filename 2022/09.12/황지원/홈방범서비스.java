import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int N, M, arr[][], ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            arr = new int[N][N];
            ans = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 1; k <= N + 1; k++) {
                        int count = find(i, j, k);
                        int num = count * M; // 집들이 주는 돈

                        int cost = (k * k + (k - 1) * (k - 1)); // 운영비용

                        // 보안회사가 이득일때
                        if (num >= cost && ans < count) {
                            ans = Math.max(ans, count);
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + ans);

        }

    }

    // 내 주위 k만큼 집이 몇개 있는지 리턴
    static int find(int x, int y, int k) {
        int home = 0;
        k = k - 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (Math.abs(x - i) + Math.abs(y - j) <= k && arr[i][j] == 1) {
                    home++;
                }
            }
        }

        return home;
    }
}
