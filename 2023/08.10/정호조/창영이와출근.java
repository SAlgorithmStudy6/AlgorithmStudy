import jdk.internal.util.xml.impl.Input;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 최대 경사의 최솟값 -> 이분탐색 문제
 * 입력 시 경사의 최댓값과 최솟값을 저장한 후 이분탐색 시작
 * 탐색 시 mid값만큼의 차이를 허용하는지 안하는지 확인
 * 탐색 완료 후 최솟값(left) 출력
 * */
public class 창영이와퇴근_22116 {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                min = Math.min(min, map[i][j]);
                max = Math.max(max, map[i][j]);
            }
        }

        int left = 0;
        int right = max - min;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (checkCase(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    static boolean checkCase(int value) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] vis = new boolean[N][N];
        boolean check = false;
        queue.add(new Point(0, 0));
        vis[0][0] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            if (cur.r == N - 1 && cur.c == N - 1) {
                check = true;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int rr = cur.r + dr[i];
                int cc = cur.c + dc[i];

                if (rr < 0 || rr >= N || cc < 0 || cc >= N || vis[rr][cc] || Math.abs(map[cur.r][cur.c] - map[rr][cc]) > value) continue;

                vis[rr][cc] = true;
                queue.add(new Point(rr, cc));
            }
        }

        return check;
    }

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
