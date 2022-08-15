import java.util.*;
import java.io.*;

public class Main_16637_괄호_추가하기 {
    static int result = Integer.MIN_VALUE;
    static List<Integer> numList;
    static List<Character> opList;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/16637.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        numList = new ArrayList<>();
        opList = new ArrayList<>();

        String str = br.readLine();
        for (int i = 0; i < N; i++) {
            char ch = str.charAt(i);

            if (ch == '*' || ch == '+' || ch == '-') {
                opList.add(ch);
            } else {
                numList.add(Character.getNumericValue(ch));
            }
        }

        DFS(0, numList.get(0));
        System.out.println(result);
    } // End of main

    private static int calc(int num1, int num2, char oper) {
        if (oper == '*') {
            return num1 * num2;
        } else if (oper == '+') {
            return num1 + num2;
        } else {
            return num1 - num2;
        }
    } // End of calc

    private static void DFS(int depth, int total) {
        if (depth == opList.size()) {
            result = Math.max(result, total);
            return;
        }

        DFS(depth + 1, calc(total, numList.get(depth + 1), opList.get(depth)));

        if(depth + 2 <= opList.size()) {
            DFS(depth + 2, calc(total, calc(numList.get(depth + 1), numList.get(depth + 2), opList.get(depth + 1)), opList.get(depth) ));
        }

    } // End of DFS
} // End of Main class