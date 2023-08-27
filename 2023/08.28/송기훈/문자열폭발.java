package baekjoon.gold.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문자열폭발 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputString = br.readLine();
        String bomb = br.readLine();
        int bombLength = bomb.length();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < inputString.length(); i++) {
            sb.append(inputString.charAt(i));
            if (sb.length() >= bombLength &&
                    sb.substring(sb.length() - bombLength).equals(bomb)
            ) {
                sb.setLength(sb.length() - bombLength);
            }
        }

        if (sb.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(sb);
        }
    }
}