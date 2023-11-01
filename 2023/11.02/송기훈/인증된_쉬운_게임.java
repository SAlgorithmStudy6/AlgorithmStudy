package baekjoon.gold.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 인증된_쉬운_게임 {
    // K를 만드는 사람이 이긴다
    // dp[i] = i로 시작했을 때 이길 수 있는가?
    // https://velog.io/@qwerty1434/%EB%B0%B1%EC%A4%80-22846%EB%B2%88-%EC%9D%B8%EC%A6%9D%EB%90%9C-%EC%89%AC%EC%9A%B4-%EA%B2%8C%EC%9E%84
    // https://steadily-worked.tistory.com/662
    // https://kwoncorin.tistory.com/115

    static int[] dp;
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        dp = new int[K+1];

        playGame(1);

        if (dp[1] == 1) {
            System.out.println("Kali");
        } else {
            System.out.println("Ringo");
        }

        System.out.println(Arrays.toString(dp));

    }

    static int playGame(int start) {
        if (start >= K) {
            return -1;
        }
        if (dp[start] != 0) {
            return dp[start];
        }

        ArrayList<Integer> divisors = findDiv(start);
        for (int i = 0; i < divisors.size(); i++) {
            if (start + divisors.get(i) <= K && playGame(start + divisors.get(i)) == -1) {
                dp[start] = 1;
                return dp[start];
            }
        }
        dp[start] = -1;
        return dp[start];
    }

    static ArrayList<Integer> findDiv(int number) {
        ArrayList<Integer> divisors = new ArrayList<>();
        for (int i = 1; i * i <= number; i++) {
            if (number % i == 0) {
                divisors.add(i);
                if (i * i != number) {
                    divisors.add(number / i);
                }
            }
        }
        return divisors;
    }

}
