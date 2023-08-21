

import java.io.*;
import java.util.*;


class Main{

    static int N;
    static String[] op = {"+","-"," "};
    static int[] nums;
    static ArrayList<String> res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        while(TC-->0){
            N = Integer.parseInt(br.readLine());
            nums = new int[N];
            for(int i=0;i<N;i++)
                nums[i] = i+1;
            res = new ArrayList<>();
            solve(1,String.valueOf(nums[0]));
            Collections.sort(res);
            for(String s : res)
                System.out.println(s);
            System.out.println();
            res.clear();
        }
    }
    static void solve(int depth,String expression){
        if(depth==N){
            String str = expression.replaceAll(" ","");
            if(isZero(str)){
                res.add(expression);
            }
            return ;
        }
        for(int i=0;i<3;i++)
            solve(depth+1,expression+op[i]+nums[depth]);

    }
    static boolean isZero(String str){
        String[] list = str.split("\\+|-");
        int opIndex = 1;
        int sum = Integer.parseInt(list[0]);
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='+'){
                sum+=Integer.parseInt(list[opIndex++]);
            }
            else if(str.charAt(i)=='-'){
                sum-=Integer.parseInt(list[opIndex++]);
            }
        }
        boolean result = sum==0?true:false;
        return result;
    }
}
