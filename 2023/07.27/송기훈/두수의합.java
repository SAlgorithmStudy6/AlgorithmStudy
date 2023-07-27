package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두수의합 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] numbers = new int[N];
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(numbers);

            int minDiff = Integer.MAX_VALUE;
            int count = 0, start = 0, end = N - 1;

            while (start < end) {
                int sum = numbers[start] + numbers[end];

                if (minDiff == Math.abs(sum - K)) {
                    count += 1;
                } else if (minDiff > Math.abs(sum - K)) {
                    minDiff = Math.abs(sum - K);
                    count = 1;
                }

                if (sum >= K) {
                    end -= 1;
                } else {
                    start += 1;
                }
            }
            System.out.println(count);
        }

    }

}