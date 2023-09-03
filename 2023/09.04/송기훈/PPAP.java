package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class PPAP {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();
        int length = inputString.length();
        int pCount = 0;

        for (int i = 0; i < length; i++) {
            // P인 경우
            if (inputString.charAt(i) == 'P') {
                pCount += 1;
            }
            // A인 경우
            else {
                // PP가 되어야하므로 pCount는 2개 이상
                // A 뒤에 따라올 P가 있어야 하므로 index는 끝에서 두번째 이하
                // 다음 index에 P인지 확인
                if (pCount >= 2 && i < length - 1 && inputString.charAt(i+1) == 'P') {
                    pCount -= 1;
                    i += 1;
                } else {
                    pCount = 0;
                    break;
                }
            }
        }

        if (pCount == 1) {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }

    }
}