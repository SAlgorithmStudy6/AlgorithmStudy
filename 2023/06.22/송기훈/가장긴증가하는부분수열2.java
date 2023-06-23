package baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://dragon-h.tistory.com/2
// https://loosie.tistory.com/376

public class 가장긴증가하는부분수열2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] numbers = new int[n];
        List<Integer> LIS = new ArrayList<>();
        LIS.add(0);

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int value = numbers[i] = Integer.parseInt(st.nextToken());
            if (value > LIS.get(LIS.size() - 1)) LIS.add(value);
            else {
                int left = 0;
                int right = LIS.size() - 1;

                while (left < right) {
                    int mid = (int)Math.floor((left + right) / 2);
                    //int mid = (left + right) >> 1;
                    if (LIS.get(mid) >= value) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                }
                LIS.set(right, value);
            }
        }
        System.out.println(LIS.size() - 1);
    }
}
