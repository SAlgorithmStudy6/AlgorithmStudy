package baekjoon.gold.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 수족관1 {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        ArrayList<Point> points = new ArrayList<>();
        int width = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points.add(new Point(x, y));

            if (width < x) {
                width = x;
            }
        }


        int[] aquarium = new int[width];
        int[] water = new int[width];
        for (int i = 1; i < N - 1; i += 2) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            for (int j = p1.x; j < p2.x; j++) {
                aquarium[j] = p1.y;
            }
        }

        int K = Integer.parseInt(br.readLine());
        Point[] holes = new Point[K];
        for (int k = 0; k < K; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            holes[k] = new Point(x1, y1);
        }

        // 구멍 뚫기
        for (int k = 0; k < K; k++) {
            Point p = holes[k];

            // 왼쪽
            int index = p.x;
            int depth = p.y;

            while (index >= 0) {
                depth = Math.min(depth, aquarium[index]);
                water[index] = Math.max(depth, water[index]);
                index -= 1;
            }

            // 오른쪽
            index = p.x;
            depth = p.y;

            while (index < width) {
                depth = Math.min(depth, aquarium[index]);
                water[index] = Math.max(depth, water[index]);
                index += 1;
            }


        }

        int sum = 0;
        for (int i = 0; i < width; i++) {
            sum += aquarium[i] - water[i];
        }
        System.out.println(sum);
    }
}
