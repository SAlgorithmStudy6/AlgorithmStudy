import java.util.*;

class Test {
    public static void main(String[] args) {
        String[] s = {"1110", "100111100", "0111111010"};
        new Solution().solution(s);
    }
}

class Solution {
    public String[] solution(String[] s) {
        int sLen = s.length;
        String[] answer = new String[sLen];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < sLen; i++) {
            String str = s[i];
            Stack<Character> stack = new Stack<>();
            int cnt = 0;

            for (int j = 0; j < str.length(); j++) {
                char c = str.charAt(j);

                if (stack.size() > 1) { // 스택의 크기가 2이상일 경우 "110" 문자열 체크
                    char b = stack.pop();
                    char a = stack.pop();

                    if (a == '1' && b == '1' && c == '0') cnt++;
                    else { // "110"이 아니라면 다시 스택에 넣기
                        stack.push(a);
                        stack.push(b);
                        stack.push(c);
                    }
                } else {
                    stack.push(c);
                }
            }

            // 문자열에 "110"이 존재할 경우
            if (cnt > 0) {
                int insertIdx = stack.size(); // 삽입할 위치
                boolean isZero = false;

                // 마지막 0 위치 찾기
                while (!stack.isEmpty()) {
                    if (!isZero) {
                        if (stack.peek() == '1') insertIdx--;
                        else isZero = true;
                    }

                    sb.insert(0, stack.pop());
                }

                // 마지막 0 위치 뒤에 "110" 삽입
                while (cnt-- > 0) {
                    sb.insert(insertIdx, "110");
                    insertIdx += 3;
                }

                answer[i] = sb.toString();

            } else answer[i] = s[i];

            sb.setLength(0);
        }

        return answer;
    }
}