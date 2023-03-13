import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int no_mat[][] = new int[n+1][n+1];

        // count
        int count = 0;
        for(int i=1; i<= n; i++) {
            no_mat[i][i] = 1;
        }
        for (int i = 1; i <= m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            no_mat[a][b] = 1;
            no_mat[b][a] = 1;
        }
        
       
        for(int i=1; i<=n; i++) { // 1
            for(int j=i+1; j<=n; j++) {
                if(no_mat[i][j] == 1) { // 4
                    continue;
                }
                for(int k=j+1; k<=n; k++) {
                    if(no_mat[j][k] == 1 || no_mat[i][k] == 1) { // 
                        continue;
                    }
//                    System.out.println(i + " " + j + " " + k);
                    count++;
                    
                }
            }
        }
        System.out.println(count);
    }
}
