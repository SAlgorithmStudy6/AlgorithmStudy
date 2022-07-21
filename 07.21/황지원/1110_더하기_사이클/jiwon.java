import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        //input
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int realnum = num;
        int count = 0;

        //logic
        while(true) {
            count++;
            int first = num / 10;
            int second = num % 10;
            
            int Fsum = (first+second)%10;
            
            num = second * 10 + Fsum;
            
            if(num == realnum) break;
               
        }
        //output
        System.out.println(count);
    }
}
