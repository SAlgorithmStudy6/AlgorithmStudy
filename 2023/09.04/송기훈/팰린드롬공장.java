package baekjoon.gold.one;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 팰린드롬공장 {

//    https://velog.io/@deserve82/DP-%ED%8C%B0%EB%A6%B0%EB%93%9C%EB%A1%AC-%EA%B3%B5%EC%9E%A5-%EB%AC%B8%EC%A0%9C%EC%97%90-%EB%8C%80%ED%95%9C-%EA%B3%A0%EC%B0%B0-python
//    문자열의 어떤 위치에 어떤 문자를 삽입 (시작과 끝도 가능)
//    어떤 위치에 있는 문자를 삭제
//    어떤 위치에 있는 문자를 교환
//    서로 다른 문자를 교환
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputString = br.readLine();

        int result = palindrome(inputString);

        String tempString;

        for (int i = 0; i < inputString.length(); i++) {
            for (int j = i + 1; j < inputString.length() - 1; j++) {
                if (inputString.charAt(i) == inputString.charAt(j)) continue;
                tempString = swap(i, j, inputString);
                result = Math.min(result, palindrome(tempString) + 1);
            }
        }
        System.out.println(result);

    }

    public static int palindrome(String str) {
        int size = str.length();
        int[][] dp = new int[size][size];

        for (int i = 0; i < size; i++) {
            dp[i][i] = 0;
            if (i != size - 1) {
                dp[i][i + 1] = str.charAt(i) == str.charAt(i + 1) ? 0 : 1;
            }
        }

        for (int i = 2; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                dp[j][j + i] = Math.min(dp[j + 1][j + i] + 1, dp[j][j + i - 1] + 1);
                if (str.charAt(j) == str.charAt(j + i)) {
                    dp[j][j + i] = Math.min(dp[j + 1][j + i - 1], dp[j][j + i]);
                } else {
                    dp[j][j + i] = Math.min(dp[j + 1][j + i - 1] + 1, dp[j][j + i]);
                }
            }
        }
        return dp[0][size - 1];
    }

    public static String swap(int a, int b, String tempString) {
        StringBuilder sb = new StringBuilder(tempString);
        char temp = tempString.charAt(a);
        sb.setCharAt(a, tempString.charAt(b));
        sb.setCharAt(b, temp);
        return sb.toString();
    }
}