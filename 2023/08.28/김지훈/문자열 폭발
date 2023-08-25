

import java.io.*;
import java.util.*;


class Main{


    public static void main(String[] args) throws Exception {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String pattern = br.readLine();
        int patternSize = pattern.length();

        Stack<Character> stack = new Stack<>();

        for(int i=0;i<str.length();i++){
            stack.push(str.charAt(i));

            if(stack.size()>=patternSize){
                boolean flag = true;

                for(int j=0;j<patternSize;j++){
                    if(stack.get(stack.size()-patternSize+j)!=pattern.charAt(j)){
                        flag = !flag;
                        break;
                    }
                }
                if(flag){
                    for(int j=0;j<patternSize;j++)
                        stack.pop();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(Character ch : stack)
            sb.append(ch);
        System.out.println(sb.length()==0?"FRULA":sb.toString());

    }

}
