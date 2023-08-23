import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        int[][] map = new int[n][m];
        for(int i=0; i<n; i++) {
            String line = br.readLine();
            for(int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(line.charAt(j) + "");
            }
        }
        
        boolean visit[][][] = new boolean[n][m][2];
        int dist[][] = new int[n][m];
        dist[0][0] = 1;
        Queue<int[]> q = new ArrayDeque<int[]>();
        q.offer(new int[] {0, 0, 0});//x, y, wall
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            int wall = now[2];
            boolean condition = false;
            for(int k=0; k<4; k++) {
                int nx = now[0] + dx[k];
                int ny = now[1] + dy[k];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                
                if(map[nx][ny] == 1) {
                    if(now[2] == 0 && !visit[nx][ny][1]) {
                        visit[nx][ny][1] = true;
                        dist[nx][ny] = dist[x][y] + 1;
                        q.offer(new int[] {nx, ny, 1});
                    }
                }
                else {
                    if(!visit[nx][ny][wall]) {
                        visit[nx][ny][wall] = true;
                        dist[nx][ny] = dist[x][y] + 1;
                        q.offer(new int[] {nx, ny, wall});
                    }
                }
                if(nx == n-1 && ny == m-1) {
                    System.out.println(dist[nx][ny]);
                    condition = true;
                    break;
                }
            }
            if(condition) {
                break;
            }
        }
        if(n == 1 && m == 1) {
            System.out.println(1);
        } else if(q.isEmpty()) {
            System.out.println(-1);
        }
    }
}
