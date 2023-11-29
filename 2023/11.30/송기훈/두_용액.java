package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두_용액 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int start = 0;
        int end = N - 1;
        int diff = Integer.MAX_VALUE;

        int temp = 0;
        int min = 0, max = 0;
        while (start < end) {
            temp = numbers[start] + numbers[end];

            if (Math.abs(temp) < diff) {
                diff = Math.abs(temp);
                min = numbers[start];
                max = numbers[end];
            }

            if (temp > 0) end -= 1;
            else start += 1;
        }

        System.out.println(min + " " + max);

    }

}
