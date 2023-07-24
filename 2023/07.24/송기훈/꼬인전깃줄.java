package baekjoon.gold.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 꼬인전깃줄 {
    // 가장긴증가하는부분수열2 참고
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] line = new int[N];
        for (int i = 0; i < N; i++) {
            line[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer>LIS = new ArrayList<>();
        LIS.add(line[0]);

        for (int i = 1; i < N; i++) {
            int value = line[i];

            // LIS 마지막 원소보다 value가 크면 LIS에 추가
            if (value > LIS.get(LIS.size() - 1)) {
                LIS.add(value);
            }
            // 아니라면 이분 탐색으로 위치 찾기
            else {
                int pos = -Collections.binarySearch(LIS, value) - 1;
                // Collections.binarySearch(List, key);
                // 못 찾았을 경우엔 key가 있었을 경우 (-(삽입 위치) - 1)를 return
                LIS.set(pos, value);
            }
        }

        System.out.println(N - LIS.size());

    }
}
