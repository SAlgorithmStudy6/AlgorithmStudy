import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] solutions = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(solutions);

        int[] answer = new int[2];
        int start = 0;
        int end = n - 1;
        int minSum = Integer.MAX_VALUE; // 0에 가장 가까운 특성값

        while (start < end) {
            int sum = solutions[start] + solutions[end]; // 특성값

            if (Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);
                answer[0] = solutions[start];
                answer[1] = solutions[end];
            }

            if (sum > 0) end--; // 산성용액값을 낮추기
            else start++; // 알칼리성용액값 낮추기
        }

        bw.write(answer[0] + " " + answer[1]);

        bw.flush();
        bw.close();
    }
}