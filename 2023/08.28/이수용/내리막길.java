import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 내리막길 {
    static int[][] dp;
    static int[][] map;
    static int N;
    static int M;
    static int[] dRow = {1, -1, 0, 0};
    static int[] dCol = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[M][N];
        for(int i = 0; i < M; i++){
            Arrays.fill(dp[i], -1);
        }

        System.out.println(DFS(0,0));

    }
    static int DFS(int row, int col){
        if(row == M - 1 && col == N - 1) return 1;
        if(dp[row][col] != -1){
            return dp[row][col];
        }
        // 한번도 방문한적 없는 위치라면 0으로 초기화 후 계산
        dp[row][col] = 0;
        for(int i = 0; i < 4; i++){
            int nRow = row + dRow[i];
            int nCol = col + dCol[i];
            if(nRow < 0 || nRow >= M || nCol < 0 || nCol >= N) continue;
            if(map[nRow][nCol] < map[row][col]){
                dp[row][col] += DFS(nRow,nCol);
            }
        }
        return dp[row][col];
    }
}