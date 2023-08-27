import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 문자열폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String string = br.readLine();
        String bomb = br.readLine();
        Stack<Character> stack = new Stack();
        for(int i = 0; i < string.length(); i++){
            stack.push(string.charAt(i));
            boolean flag = true;
            if(stack.size() >= bomb.length()){
                int startIndex = stack.size() - bomb.length();
                for(int j = 0; j < bomb.length(); j++){
                    if(stack.get(startIndex + j) != bomb.charAt(j)){
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    for (int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(char chr: stack){
            sb.append(chr);
        }
        if(sb.length() > 0) System.out.println(sb);
        else System.out.println("FRULA");
    }
}
