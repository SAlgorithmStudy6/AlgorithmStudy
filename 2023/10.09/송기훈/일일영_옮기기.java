package programmers.lv3;

import java.util.*;

//class Test {
//
//    public static void main(String[] args) {
//        String[] s = {"1110", "100111100", "0111111010"};
//        String[] sol = new 일일영_옮기기().solution(s);
//        System.out.println(Arrays.toString(sol));
//    }
//
//}

public class 일일영_옮기기 {

    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            int count = 0;
            int idx = 0;
            StringBuilder sb = new StringBuilder();

            while (idx < s[i].length()) {
                // 110 찾기
                if (s[i].charAt(idx) == '0' && sb.length() >= 2 &&
                        sb.substring(sb.length() - 2).equals("11")) {
                    sb.setLength(sb.length() - 2);
                    count += 1;
                } else {
                    sb.append(s[i].charAt(idx));
                }
                idx++;
            }

            idx = sb.indexOf("111");       // 111의 인덱스 찾기, sb에는 110이 없다

            if (idx == -1) {
                // 111이 없을 경우 마지막 0 뒤에 110을 count번만큼 넣기
                idx = sb.lastIndexOf("0");
                sb.insert(idx + 1, repeatString("110", count));
            } else {
                // 111이 있을 경우 앞에 110을 count번만큼 넣기
                sb.insert(idx, repeatString("110", count));
            }

            answer[i] = sb.toString();
        }

        return answer;
    }

    private static String repeatString(String str, int times) {
        return new String(new char[times]).replace("\0", str);
    }
}
