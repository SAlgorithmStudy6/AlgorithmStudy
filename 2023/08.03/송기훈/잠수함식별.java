package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 잠수함식별 {

    //  우리가 식별하고자 하는 잠수함의 엔진소리의 패턴은 다음과 같다.
    //  (100~1~|01)~
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String regex = "(100+1+|01)+";

        String inputString = br.readLine();

        if (inputString.matches(regex)) {
            System.out.println("SUBMARINE");
        } else {
            System.out.println("NOISE");
        }
    }
}
