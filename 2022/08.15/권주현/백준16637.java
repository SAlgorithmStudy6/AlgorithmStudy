import java.util.Scanner;

public class 백준16637 {
        static String str;
        static int n;
        static int MAX=Integer.MIN_VALUE;
        public static void main(String[] args) {
            Scanner scan=new Scanner (System.in);
            n=scan.nextInt();
            str=scan.next();
            

           sol(str.charAt(0)-'0',1);        //괄호 안줄 때
           System.out.println(MAX);
            
        }
        
        public static void sol(int prev,int now){
            if(now==n){
                MAX=Math.max(prev,MAX);
                return;
            }
            
            //괄호 줄 때
            if(now<=n-4){
                int result=cal(prev,str.charAt(now),cal(str.charAt(now+1)-'0',str.charAt(now+2),str.charAt(now+3)-'0'));
                sol(result,now+4);
            }
            // 괄호 안줄 때 
            if(now<=n-2){
                int result2=cal(prev,str.charAt(now),str.charAt(now+1)-'0');
                sol(result2,now+2);
            }
            
        }
        public static int cal (int a,char s, int b){
            if(s=='+'){
                return a+b;
            }
            else if(s=='-'){
                return a-b;
            }
            else if(s=='*'){
                return a*b;
            }
             return 0;
        }
    }
