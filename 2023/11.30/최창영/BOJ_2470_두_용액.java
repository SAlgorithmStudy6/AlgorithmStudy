package BOJ_2470;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470_두_용액 {

    // https://www.acmicpc.net/problem/2470
    // input
    private static BufferedReader br;

    // variables
    private static int N;
    private static long leftIdx, rightIdx, ans;
    private static long[] arr;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("C:\\Users\\bigyo\\Desktop\\알고리즘\\JavaAlgorithm\\src\\BOJ_2470\\res.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input();

        bw.write(solve());
        bw.close();
    } // End of main()

    private static String solve() {
        StringBuilder sb = new StringBuilder();

        Arrays.sort(arr);

        for (int i = 0; i < N - 1; i++) {
            binarySearch(i, i + 1, N - 1);
        }


        sb.append(arr[(int) leftIdx]).append(' ').append(arr[(int) rightIdx]);
        return sb.toString();
    } // End of solve()

    private static void binarySearch(int idx, int low, int high) {
        if (low > high) {
            return;
        }

        int mid = (low + high) / 2;
        long sum = arr[idx] + arr[mid];

        if (Math.abs(sum) < Math.abs(ans)) {
            ans = sum;
            leftIdx = idx;
            rightIdx = mid;
        }

        if (sum < 0) {
            binarySearch(idx, mid + 1, high);
        } else {
            binarySearch(idx, low, mid - 1);
        }
    } // End of binarySearch()

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        ans = Long.MAX_VALUE;
        leftIdx = 0;
        rightIdx = 0;
        arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    } // End of input()
} // End of Main class
