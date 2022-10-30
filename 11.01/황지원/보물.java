import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class Boj_1026_보물 {
    static int n, a[];
    static Integer b[];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st, st2;

        n = Integer.parseInt(br.readLine());
        a = new int[n];
        b = new Integer[n];

        st = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            b[i] = Integer.parseInt(st2.nextToken());
        }

        Arrays.sort(a);
        Arrays.sort(b, Collections.reverseOrder());

        long answer = 0;
        for(int i=0; i<n; i++){
            answer += a[i] * b[i];
        }
        System.out.println(answer);
    }
}
