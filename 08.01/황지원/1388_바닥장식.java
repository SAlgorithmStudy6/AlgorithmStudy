import java.util.*;

public class Main {
    static int count = 0;
    static char plank[][] = new char[50][50];
    static boolean visited[][] = new boolean[50][50];
    static int n;
    static int m;

    static void bfs(int x, int y) {
        int dx[] = { -1, 1, 0, 0}; // 상 하 , 좌 우
        int dy[] = { 0, 0, -1, 1};


        if (plank[x][y] == '-') { // 좌 우만 볼것
            for(int i=2; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if(plank[nx][ny] == plank[x][y] && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        bfs(nx, ny);
                    }
                }
            }
        }else { // 상하만 볼 것
            for(int i=0; i<2; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if(plank[nx][ny] == plank[x][y] && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        bfs(nx, ny);
                    }
                }
            }
        }
       
    }

    public static void main(String[] args) {
        // 입력받기
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt(); // 세로
        m = sc.nextInt(); // 가로
        sc.nextLine();

        // 입력
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < s.length(); j++) {
                plank[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(visited[i][j] == false) {
                    visited[i][j] = true;
                    count++;
                    bfs(i, j);
                }
            }
        }

        System.out.println(count);

    }
}
