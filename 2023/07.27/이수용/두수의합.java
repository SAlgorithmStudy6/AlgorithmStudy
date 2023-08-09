import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9024

public class 두수의합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int test = 0; test < t; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);
            int left = 0;
            int right = arr.length - 1;
            int minValue = Integer.MAX_VALUE;
            int count = 1;

            while (true) {
                int sum = arr[left] + arr[right];
                if (Math.abs(sum - k) == minValue) {
                    count++;
                } else if (Math.abs(sum - k) < minValue) {
                    count = 1;
                    minValue = Math.abs(sum - k);
                }

                if (sum == k) {
                    left++;
                    right--;
                } else if (sum < k) {
                    left++;
                } else {
                    right--;
                }

                if (left >= right) {
                    break;
                }
            }
            System.out.println(count);
        }
    }
}
