import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (stack.size() < 3) stack.add(str.charAt(i));
            else {
                if (str.charAt(i) == 'P'
                        && stack.get(stack.size() - 3) == 'P'
                        && stack.get(stack.size() - 2) == 'P'
                        && stack.get(stack.size() - 1) == 'A') {
                    for (int j = 0; j < 2; j++) {
                        stack.pop();
                    }
                } else stack.add(str.charAt(i));
            }
        }

        if (stack.size() == 1 && stack.peek() == 'P') bw.write("PPAP");
        else bw.write("NP");

        bw.flush();
        bw.close();
    }
}