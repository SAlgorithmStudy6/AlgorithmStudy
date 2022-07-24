
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {       
        Scanner sc = new Scanner(System.in);
        String f = sc.nextLine();
        String f2 = sc.nextLine();
        
        char file[] = new char[f.length()];
        char find[] = new char[f2.length()];
        
        for(int i=0; i<f.length(); i++) {
            file[i] = f.charAt(i);
        }
        for(int i=0; i<f2.length(); i++) {
            find[i] = f2.charAt(i);
        }
      
        int count = 0;
        
        int i=0; int j=0;
        while(i < file.length) {
            j = 0;
            while(j < find.length && i < file.length) {
                if(file[i] == find[j]) { // 같다면
                    i++;
                    j++;
                }
                else { // 같지 않다면
                    i=i-j+1;
                    break;
                }
            }
            if(j == find.length) {
                count++;
            }
            
        }
        System.out.println(count);
    }
}
