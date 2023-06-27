import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 지름길_1446 {
    static int N, D;
    static int[][] roadInfo;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        roadInfo = new int[N][3];
        dist = new int[10001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                roadInfo[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(roadInfo, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);

        int now = 0;
        int idx = 0;
        while (now < D) {
            if (idx < roadInfo.length) {
                if (now == roadInfo[idx][0]) {
                    dist[roadInfo[idx][1]] = Math.min(dist[now] + roadInfo[idx][2], dist[roadInfo[idx][1]]);
                    idx++;
                } else {
                    dist[now + 1] = Math.min(dist[now + 1], dist[now] + 1);
                    now++;
                }
            } else {
                dist[now + 1] = Math.min(dist[now + 1], dist[now] + 1);
                now++;
            }
        }

        System.out.println(dist[D]);
    }

    public static class Road {
        int start, end, distance;

        public Road(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }
}
