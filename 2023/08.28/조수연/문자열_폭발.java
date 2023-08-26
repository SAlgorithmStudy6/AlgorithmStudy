import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        String pattern = br.readLine();
        Stack<Character> stack = new Stack<>();
        int len = pattern.length();

        for (int i = 0; i < str.length(); i++) {
            stack.add(str.charAt(i));

            if (stack.size() >= len){
                boolean isFind = true;

                for (int j = 0; j < len; j++) {
                    if (stack.get(stack.size() - len + j) != pattern.charAt(j)){ // 패턴이 일치하지 않는 경우
                        isFind = false;
                        break;
                    }
                }

                if (isFind){
                    for (int j = 0; j < len; j++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (Character c : stack){
            sb.append(c);
        }

        if (stack.isEmpty()) bw.write("FRULA");
        else bw.write(sb.toString());

        bw.flush();
        bw.close();
    }
}