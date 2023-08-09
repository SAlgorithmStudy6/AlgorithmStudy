import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22116

public class 창영이형퇴근 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int[][] grid = new int[N][N];
        int[][] visited = new int[N][N];
        PriorityQueue<Info> pq = new PriorityQueue();
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited[0][0] = 0;
        // slope, row, col
        pq.add(new Info(0, 0, 0));
        while (!pq.isEmpty()) {
            Info info = pq.poll();
            int slope = info.slope;
            int row = info.row;
            int col = info.col;
            if (visited[row][col] < slope) continue;
            for (int i = 0; i < 4; i++) {
                int nRow = row + dx[i];
                int nCol = col + dy[i];
                if ((0 <= nRow && nRow < N) && (0 <= nCol && nCol < N)) {
                    int nSlope = Math.max(Math.abs(grid[row][col] - grid[nRow][nCol]), slope);
                    if (visited[nRow][nCol] > nSlope) {
                        visited[nRow][nCol] = nSlope;
                        pq.add(new Info(nSlope, nRow, nCol));
                    }
                }
            }
        }
        System.out.println(visited[N - 1][N - 1]);
    }
}

class Info implements Comparable<Info> {
    int slope;
    int row;
    int col;

    public Info(int slope, int row, int col) {
        this.slope = slope;
        this.row = row;
        this.col = col;
    }

    @Override
    public int compareTo(Info o) {
        if (this.slope < o.slope) return -1;
        else if (this.slope == o.slope) return 0;
        else return 1;
    }
}