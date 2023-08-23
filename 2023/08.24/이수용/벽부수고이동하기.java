import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2206
// BFS 탐색시 벽을 부쉈던 이력이 있는지 저장
// visited를 3차원으로 정의하여 벽을 부순상태에서의 이동경로와 벽을 부수지 않은상태에서 이동경로를 구분.

public class 벽부수고이동하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        LinkedList<Position> ll = new LinkedList();
        int[] dRow = {-1, 1, 0, 0};
        int[] dCol = {0, 0, -1, 1};
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        boolean[][][] visited = new boolean[N][M][2];
        int minValue = 1_000_000;
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j);
            }
        }
        ll.add(new Position(0, 0, 1, false));
        visited[0][0][0] = true;
        while (!ll.isEmpty()) {
            Position currentPosition = ll.pollFirst();
            // 출구 위치면 최소 거리 갱신
            if (currentPosition.row == N - 1 && currentPosition.col == M - 1) {
                System.out.println(currentPosition.dist);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nRow = dRow[i] + currentPosition.row;
                int nCol = dCol[i] + currentPosition.col;
                if (!(0 <= nRow && nRow < N) || !(0 <= nCol && nCol < M)) {
                    continue;
                }
                if (map[nRow][nCol] == '0') {
                    // 벽부순적 없고 다음 공간이 방문한 적 없는 곳일때
                    if (!currentPosition.isBroken && !visited[nRow][nCol][0]) {
                        visited[nRow][nCol][0] = true;
                        ll.add(new Position(nRow, nCol, currentPosition.dist + 1, false));
                    }
                    // 이미 벽을 부순적이 있고 다음 공간이 방문한 적 없을 때
                    else if (currentPosition.isBroken && !visited[nRow][nCol][1]) {
                        visited[nRow][nCol][1] = true;
                        ll.add(new Position(nRow, nCol, currentPosition.dist + 1, true));
                    }
                } else {
                    // 다음 위치가 벽이라면 부순적 없을때만 이동 가능
                    if (!currentPosition.isBroken) {
                        visited[nRow][nCol][1] = true;
                        ll.add(new Position(nRow, nCol, currentPosition.dist + 1, true));
                    }
                }
            }
        }
        System.out.println(-1);
    }
}

class Position {
    int row;
    int col;
    int dist;
    boolean isBroken;

    public Position(int row, int col, int dist, boolean isBroken) {
        this.row = row;
        this.col = col;
        this.dist = dist;
        this.isBroken = isBroken;
    }
}
