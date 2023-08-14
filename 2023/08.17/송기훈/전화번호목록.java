package baekjoon.gold.four;

import java.io.*;
import java.util.*;

public class 전화번호목록 {
    // 포함 관계인가?
    // 포함 관계이면 NO

    static int T, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            String[] numbers = new String[N];
            for (int i = 0; i < N; i++) {
                numbers[i] = br.readLine();
            }
            Arrays.sort(numbers);

            boolean flag = true;
            for (int i = 0; i < N - 1; i++) {
                if (numbers[i + 1].startsWith(numbers[i])) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

    }

}
