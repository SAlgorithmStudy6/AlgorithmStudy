import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/8982

public class 수족관1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] depth = new int[40001];
        int[] surface = new int[40001];
        int lastIndex = 0;
        br.readLine();
        for (int i = 0; i < n / 2 - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = x1; j < x2; j++) {
                depth[j] = y1;
            }
            lastIndex = x2 - 1;
        }
        br.readLine();

        int k = Integer.parseInt(br.readLine());
        Vertex[] hole = new Vertex[k];

        for (int i = 0; i < k; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            hole[i] = new Vertex(x, y);
        }
        for (Vertex currentVertex : hole) {
            int currentDepth = currentVertex.y;
            int currentCol = currentVertex.x;

            for (int i = currentCol; i >= 0; --i) {
                currentDepth = currentDepth > depth[i] ? depth[i] : currentDepth;
                surface[i] = currentDepth > surface[i] ? currentDepth : surface[i];
            }

            currentDepth = currentVertex.y;
            currentCol = currentVertex.x;

            for (int i = currentCol; i <= lastIndex; ++i) {
                currentDepth = currentDepth > depth[i] ? depth[i] : currentDepth;
                surface[i] = currentDepth > surface[i] ? currentDepth : surface[i];
            }
        }
        int ans = 0;
        for (int i = 0; i <= lastIndex; ++i) {
            ans += depth[i] - surface[i];
        }
        System.out.println(ans);
    }

    static class Vertex {
        int x, y;

        Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x + ":" + y;
        }
    }
}
