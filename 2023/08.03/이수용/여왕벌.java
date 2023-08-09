import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10836

public class 여왕벌 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] honeycomb = new int[M][M];
        for (int i = 0; i < M; i++) {
            Arrays.fill(honeycomb[i], 1);
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int zeroCount = Integer.parseInt(st.nextToken());
            int oneCount = Integer.parseInt(st.nextToken());
            int twoCount = Integer.parseInt(st.nextToken());

            for (int j = M - 1; j > 0; j--) {
                if (zeroCount > 0) {
                    zeroCount--;
                } else if (oneCount > 0) {
                    oneCount--;
                    honeycomb[j][0] += 1;
                } else if (twoCount > 0) {
                    twoCount--;
                    honeycomb[j][0] += 2;
                }
            }

            for (int j = 0; j < M; j++) {
                if (zeroCount > 0) {
                    zeroCount--;
                } else if (oneCount > 0) {
                    oneCount--;
                    honeycomb[0][j] += 1;
                } else if (twoCount > 0) {
                    twoCount--;
                    honeycomb[0][j] += 2;
                }
            }
        }

        for (int i = 1; i < M; i++) {
            System.arraycopy(honeycomb[i - 1], 1, honeycomb[i], 1, M - 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++)
                sb.append(honeycomb[i][j] + " ");
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
