import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 센서_2212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] censor = new int[N];
        int[] dif = new int[N - 1];
        int ans = 0;

        if (K >= N) {
            System.out.println(0);
            return;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            censor[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(censor);

        for (int i = 0; i < N - 1; i++) {
            dif[i] = censor[i + 1] - censor[i];
        }
        Arrays.sort(dif);

        for (int i = 0; i < N - K; i++) {
            ans += dif[i];
        }

        System.out.println(ans);
    }
}
