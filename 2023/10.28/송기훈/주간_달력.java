package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://vapor3965.tistory.com/78

public class 주간_달력 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, T;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        T = N * 7;

        int[] times = new int[50001 + (N*7)];
        int[] prefix = new int[50001 + (N*7)];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            times[start] += 1;
            times[end + 1] -= 1;
            prefix[end + 1] += 1;
        }

        for (int i = 1; i <= 50000; i++) {
            times[i] = times[i - 1] + times[i];
        }
        for (int i = 1; i <= 50000; i++) {
            times[i] = times[i - 1] + times[i];
        }

        ArrayList<int[]> schedules = new ArrayList<>();
        for (int i = 1; i <= 50000; i++) {
            schedules.add(new int[]{i, times[i + (N * 7) - 1] - times[i - 1]});
        }

        schedules.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] t0, int[] t1) {
                if (t0[1] == t1[1]) {
                    return t0[0] - t1[0];
                } else {
                    return t1[1] - t0[1];
                }
            }
            // 뒤의 내림차순
            // 뒤가 같으면 앞은 오름차순
        });

        for (int[] d : schedules) {
            System.out.println(Arrays.toString(d));
        }

        int date = schedules.get(0)[0];

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int start = (i * 7) + date;
            for (int j = start + 1; j < start + 7; j++) {
                answer += prefix[j];
            }
            answer += times[start + 6] - times[start + 5];
        }
        System.out.println(answer);
    }

}
