import java.util.*;

public class Main {
    static int zzeli[][];
    static boolean visited[][];
    static int count;
    static int n;
    static int dx[] = {0, 1};
    static int dy[] = {1, 0};

    static void dfs(int i, int j) {
        if(zzeli[i][j] == -1) {
            System.out.println("HaruHaru");
            System.exit(0);
        }
        for(int k=0; k<2; k++) {
            int nx = i + dx[k] * zzeli[i][j];
            int ny = j + dy[k] * zzeli[i][j];
            if(nx>=n || ny>=n || visited[nx][ny]) continue;
            
            visited[nx][ny] = true;
            dfs(nx, ny);
            
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        visited = new boolean[n][n];
        zzeli = new int[n][n];

        // 입력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = sc.nextInt();
                zzeli[i][j] = a;
            }
        }

        dfs(0, 0);
        
        System.out.println("Hing");
    }
}
