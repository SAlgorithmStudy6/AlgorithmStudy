package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빗물 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] walls = new int[W];
        int[] maxWalls = new int[W];
        int temp = Integer.MIN_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            walls[i] = Integer.parseInt(st.nextToken());
            temp = Integer.max(temp, walls[i]);

            // 현재까지 제일 높은 벽 기록
            maxWalls[i] = temp;
        }

        int answer = 0;
        for (int i = 1; i < W - 1; i++) {
            int rightMax = walls[i];
            int leftMax = maxWalls[i];

            for (int j = i + 1; j < W; j++) {
                rightMax = Math.max(rightMax, walls[j]);
            }

            if (Math.min(leftMax, rightMax) > walls[i]) {
                answer += Math.min(leftMax, rightMax) - walls[i];
            }
        }

        System.out.println(answer);

    }

}
