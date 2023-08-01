package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 축구 {
    // 적어도 한 팀이 골을 소수로 득점할 확률을 그냥 구하자
    // 18세트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());

        int N = 18;
        long[] factorials = new long[N + 1];
        for (int i = 0; i <= N; i++) {
            factorials[i] = factorial(i);
        }

        int[] goalList = {2, 3, 5, 7, 11, 13, 17};
        long[] combi = new long[goalList.length];

        for (int i = 0; i < goalList.length; i++) {
            int goal = goalList[i];
            combi[i] = factorials[18] / factorials[goal] / factorials[18 - goal];
        }

        double goalA = A / 100.0;
        double noGoalA = 1.0 - goalA;
        double goalB = B / 100.0;
        double noGoalB = 1.0 - goalB;

        double pA = 0.0;
        double pB = 0.0;
        for (int i = 0; i < goalList.length; i++) {
            // 확률 = p^goal * (1-p)^nogoal * combi
            pA += Math.pow(goalA, goalList[i]) * Math.pow(noGoalA, (N - goalList[i])) * combi[i];
            pB += Math.pow(goalB, goalList[i]) * Math.pow(noGoalB, (N - goalList[i])) * combi[i];
        }

        System.out.println(pA + pB - pA * pB);
    }

    static long factorial(long a) {
        if (a == 0) {
            return 1;
        }
        long result = 1;
        for (long i = 1; i <= a; i++) {
            result *= i;
        }
        return result;
    }
}

/*
public class 축구 {
    // 적어도 한 팀이 골을 소수로 득점할 확률 = 1 - 두 팀 모두 소수로 득점 못할 확률
    // 18세트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());

        long[] factorials = new long[19];
        for (int i = 0; i < 19; i++) {
            factorials[i] = factorial(i);
        }

        int[] goalList = {0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18};
        long[] combi = new long[goalList.length];

        for (int i = 0; i < goalList.length; i++) {
            int goal = goalList[i];
            combi[i] = factorials[18] / factorials[goal] / factorials[18 - goal];
        }

        double goalA = (double) A / 100;
        double noGoalA = 1.0 - goalA;
        double goalB = (double) B / 100;
        double noGoalB = 1.0 - goalB;

        double pA = 0.0;
        double pB = 0.0;
        for (int i = 0; i < goalList.length; i++) {
            // 확률 = p^goal * (1-p)^nogoal * combi
            pA += Math.pow(goalA, goalList[i]) * Math.pow(noGoalA, (18 - goalList[i])) * combi[i];
            pB += Math.pow(goalB, goalList[i]) * Math.pow(noGoalB, (18 - goalList[i])) * combi[i];
        }

        System.out.println(1 - pA * pB);
    }

    static long factorial(long a) {
        if (a == 0) {
            return 1;
        }
        long result = 1;
        for (long i = 1; i <= a; i++) {
            result *= i;
        }
        return result;
    }
}
*/
