import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Coo {
    int x;
    int y;

    Coo(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int N, M, cnt;
    static int map[][] = new int[51][51];
    static int dx[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int dy[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

    public static void main(String[] args) throws Exception {
        // 16953IOExce
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 상어만(1만) 돌리기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }

        // 최대값 구하기
        int tmp = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp = Math.max(tmp, map[i][j]);
            }
        }
        System.out.println(tmp-1);
//      sb.append(tmp);
//        bw.write(sb.toString()); bw.close();

    }

    static void bfs(int i, int j) {
        Queue<Coo> q = new LinkedList<>();
        boolean visited[][] = new boolean[N][M];
        q.offer(new Coo(i, j)); // add 대신 offer
        map[i][j] = 1;

        while (!q.isEmpty()) {
            Coo p = q.poll();
            visited[p.x][p.y] = true; // 방문체크는 해줌

            for (int a = 0; a < 8; a++) {
                int nx = p.x + dx[a];
                int ny = p.y + dy[a];
                
                if (rangeCheck(nx, ny) && !visited[nx][ny]) {
                    if(map[nx][ny] != 0) { // 0이 아니라는 건 전에 한번 왔었다는 것
                        map[nx][ny] = Math.min(map[p.x][p.y] + 1, map[nx][ny]);
                    }
                    else { 
                        map[nx][ny] = map[p.x][p.y] + 1;
                    }
                    q.offer(new Coo(nx, ny));
                    visited[nx][ny] = true;
                }

            }

        }
    }
    
    static boolean rangeCheck(int nx, int ny) {
        return (0 <= nx && nx < N && 0 <= ny && ny < M);
    }

}


