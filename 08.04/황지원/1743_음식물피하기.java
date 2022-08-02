import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class App {

    static int N, M, K, cnt, ans;
    static int map[][];
    static boolean visited[][];
    static int dx[] = { -1, 1, 0, 0 };
    static int dy[] = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 세로길이
        M = sc.nextInt(); // 가로길이
        K = sc.nextInt();

        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];

        for (int i = 0; i < K; i++) {
            int a = sc.nextInt(); // r 세로
            int b = sc.nextInt(); // c 가로

            map[a][b] = 1;
        }


        for (int i = 1; i <= N; i++) { // 세로
            for (int j = 1; j <= M; j++) { // 가로
                if (!visited[i][j] && map[i][j] == 1) {
                    cnt = 0;
                    bfs(i, j);
                    ans = Math.max(cnt, ans); // 최대값 갱신
                }
            }
        }

        System.out.println(ans);
    }

    static int max_check = 1;
    static int max = 0;

    static void bfs(int x, int y) {
        Queue <point> q = new LinkedList<>();
        q.add(new point(x, y));
        visited[x][y] = true;
        cnt++;
        
        while(!q.isEmpty()){
            point tmp = q.poll();
            for(int k=0; k<4; k++){
                int nx = tmp.x + dx[k];
                int ny = tmp.y + dy[k];
                if(1 <= nx && nx <= N && 1 <= ny && ny <= M){
                    if(!visited[nx][ny] && map[nx][ny] == 1){
                        q.add(new point(nx, ny));
                        visited[nx][ny] = true;
                        cnt++;
                    }
                }
            }
        }
    }
    static class point{
        int x;
        int y;
        public point(int x, int y){
            super();
            this.x = x;
            this.y = y;
        }
    }
}
