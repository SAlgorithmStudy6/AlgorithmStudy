package baekjoon.gold.five;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://kunduz.tistory.com/entry/BAEKJOON-%EB%B0%B1%EC%A4%80-3020-%EA%B0%9C%EB%98%A5%EB%B2%8C%EB%A0%88-JAVA
// https://jjong2.tistory.com/60
public class 개똥벌레 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, H;
    static int[] lowObstacles, highObstacles;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 동굴의 길이
        H = Integer.parseInt(st.nextToken());   // 동굴의 높이
        lowObstacles = new int[H + 1];          // 석순
        highObstacles = new int[H + 1];         // 종유석

        // 석순과 종유석
        for (int i = 1; i <= N; i++) {
            int obstacleHeight = Integer.parseInt(br.readLine());

            // 홀수는 석순
            if (i % 2 == 1) {
                lowObstacles[obstacleHeight] += 1;
            }
            // 짝수는 종유석
            else {
                highObstacles[obstacleHeight] += 1;
            }
        }

        // 누적합으로 전환
        for (int i = 2; i <= H; i++) {
            // 높이 i 이하인 것의 석순, 종유석
            lowObstacles[i] += lowObstacles[i - 1];
            highObstacles[i] += highObstacles[i - 1];
        }

        // 각 높이마다 부딪히는 곳을 계산
        int minValue = Integer.MAX_VALUE;
        int count = 1;
        for (int i = 1; i <= H; i++) {
            int temp = 0;
            // 높이 i의 장애물 갯수
            temp += lowObstacles[H] - lowObstacles[i - 1];
            temp += highObstacles[H] - highObstacles[H - i];

            if (minValue > temp) {
                minValue = temp;
                count = 1;
            } else if (minValue == temp) {
                count += 1;
            }
        }
        System.out.println(minValue + " " + count);
    }
}