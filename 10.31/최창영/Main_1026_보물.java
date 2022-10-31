import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_1026_보물 {
    static int N, result;
    static Integer[] arr, arr2, comb;
    static boolean[] isVisited;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/1026.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new Integer[N];
        arr2 = new Integer[N];
        comb = new Integer[N];
        isVisited = new boolean[N];
        result = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr2, Comparator.reverseOrder());

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += arr[i] * arr2[i];
        }

        System.out.println(ans);
    } // End of main
} // End of Main class
