package baekjoon.gold.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cubeditor {

    // 접두사와 접미사가 같은 문자열의 최대 길이
    // https://loosie.tistory.com/192
    // https://youtu.be/UcjK_k5PLHI

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();
        int length = inputString.length();
        int answer = 0;

        for (int i = 0; i < length; i++) {
            String subString = inputString.substring(i, length);
            answer = Math.max(answer, kmp(subString));
        }

        System.out.println(answer);
    }

    static int kmp(String str) {
        int left = 0;
        int length = str.length();
        int maxValue = 0;
        int[] table = new int[length];

        for (int right = 1; right < length; right++) {

            while (left > 0 && str.charAt(right) != str.charAt(left)) {
                left = table[left - 1];
            }

            if (str.charAt(left) == str.charAt(right)) {
                left += 1;
                table[right] = left;
                maxValue = Math.max(maxValue, left);
            }

        }

        return maxValue;
    }

}
