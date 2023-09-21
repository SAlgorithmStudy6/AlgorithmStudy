package programmers.lv3;

import java.util.Arrays;

public class 에어컨 {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        // a가 100 이하, onboard 길이가 1000 이하
        int maxValue = 100 * 1000;
        // -10도에서 40도까지, t1과 t2의 좌표도 그에 맞게 바꾸어준다
        t1 += 10;
        t2 += 10;
        temperature += 10;
        int[][] dp = new int[onboard.length][51];
        for (int[] d : dp) {
            Arrays.fill(d, maxValue);
        }

        // 에어컨을 켜지 않았을 때 생기는 온도 변화
        int delta = -1;
        if (temperature > t2) {
            delta = 1;
        }
        dp[0][temperature] = 0;
        for (int i = 1; i < onboard.length; i++) {
            for (int t = 0; t < 51; t++) {
                int minValue = 50000;
                if ((onboard[i] == 1 && t1 <= t && t <= t2) || onboard[i] == 0) {

                    // 에어컨을 켜지 않음 + 실외온도와 다름
                    if (0 <= t - delta && t - delta <= 50) {
                        minValue = Math.min(minValue, dp[i - 1][t - delta]);
                    }

                    // 에어컨을 켜지 않음 + 실외온도와 같음
                    if (t == temperature) {
                        minValue = Math.min(minValue, dp[i - 1][t]);
                    }

                    // 에어컨을 켬 + 적정 온도가 아님
                    if (0 <= t + delta && t + delta <= 50) {
                        minValue = Math.min(minValue, dp[i - 1][t + delta] + a);
                    }

                    // 에어컨을 켬 + 적정 온도임
                    if (t1 <= t && t <= t2) {
                        minValue = Math.min(minValue, dp[i - 1][t] + b);
                    }

                    dp[i][t] = minValue;
                }
            }
        }

        int answer = maxValue;
        for (int t = 1; t < 51; t++) {
            answer = Math.min(dp[onboard.length - 1][t], answer);
        }

        return answer;
    }

}