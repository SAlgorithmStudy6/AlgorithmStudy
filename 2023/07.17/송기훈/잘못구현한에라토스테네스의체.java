package baekjoon.gold.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 잘못구현한에라토스테네스의체 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        long result = N;
        for (int i = 2, temp; i < N; i = temp + 1) {
            int j = (N - 1) / i + 1;

            // j값을 만족하는 최대값
            temp = (N - 1) / ((N - 1) / i);
            result += (long) (temp - i + 1) * j;
        }

        if (N != 1) {
            System.out.println(result + 1);
        } else {
            System.out.println(result);
        }

    }

    /*
    (N-1) / i = (N-1) / temp
    temp = (N-1) / ((N-1) / i))
     */

}

/*
i: 2 j: 5
i: 3 j: 4
i: 4 j: 3
i: 5 j: 2
i: 6 j: 2
i: 7 j: 2
i: 8 j: 2
i: 9 j: 2
i: 10 j: 1
******************
i: 2 temp: 2
i: 3 temp: 3
i: 4 temp: 4
i: 5 temp: 9
* */