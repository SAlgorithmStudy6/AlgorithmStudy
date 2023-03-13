import java.util.Scanner;

public class Main {
    static char candy[][] = new char[50][50];
    
    static int max_candy = 0;
    
    public static void find_long_candy(int n) {
        for(int i=0; i<n; i++) { // 가로
            int count = 1;
            for(int j=0; j<n-1; j++) {
                if (candy[i][j] == candy[i][j+1])
                    count++;                    
                else
                    count = 1;
                max_candy = Math.max(max_candy, count);
            }
        }
        
        for(int i=0; i<n; i++) { // 세로
            int count = 1;
            for(int j=0; j<n-1; j++) {
                if(candy[j][i] == candy[j+1][i])
                    count++;
                else
                    count = 1;
                max_candy = Math.max(max_candy, count);
            }
        }
    }
    
    
    // c 빨, p 파, z 초, y 노
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        sc.nextLine();        

        for(int i=0; i<n; i++) {
            String s = sc.nextLine();
            for(int j=0; j<n; j++) {
                candy[i][j] = s.charAt(j);
            }
        }

        // 가로 교환
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {
                char temp = candy[i][j + 1];
                candy[i][j+1] = candy[i][j];
                candy[i][j] = temp;
                
                find_long_candy(n);
                
                temp = candy[i][j+1];
                candy[i][j+1] = candy[i][j];
                candy[i][j] = temp;
            }

        }

        // 세로 교환
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {
                
                char temp = candy[j+1][i];
                candy[j+1][i] = candy[j][i];
                candy[j][i] = temp;
                
                find_long_candy(n);
                
                temp = candy[j+1][i];
                candy[j+1][i] = candy[j][i];
                candy[j][i] = temp;
            }

        }
        System.out.println(max_candy);
    }
}
