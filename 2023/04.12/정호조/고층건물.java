import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 고층건물_1027 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] cnt = new int[N];
        int ans = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N - 1; i++) {
            cnt[i]++;
            cnt[i + 1]++;
            double slope = arr[i + 1] - arr[i];
            for (int j = i + 2; j < N; j++) {
                double nextSlope = (double)(arr[j] - arr[i])/(j-i);
                if(slope >= nextSlope) continue;
                slope = nextSlope;
                cnt[i]++;
                cnt[j]++;
            }
        }

        Arrays.sort(cnt);
        System.out.println(cnt[cnt.length-1]);
    }
}


