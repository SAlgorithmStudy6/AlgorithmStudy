import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int E = sc.nextInt();
        int S = sc.nextInt();
        int M = sc.nextInt();
        
        int i=1;
        while(true) {
            if((i-E)%15 == 0 &&
                (i-S)%28 == 0 &&
                (i-M)%28 == 0){
                    break;
                }
            i++;
        }
        System.out.println(i);
    }
}
