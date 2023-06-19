package baekjoon.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 톱니바퀴2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[][] gears = new int[T][8];

        for (int i = 0; i < T; i++) {
            String temp = br.readLine();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = temp.charAt(j) - 48;
            }
        }
        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());
            int tempDirection = direction;

            ArrayList<Integer> rightList = check(1, index, gears, T);
            ArrayList<Integer> leftList = check(0, index, gears, T);

            // 자기 자신 돌리기
            gears[index] = rotation(tempDirection, gears[index]);

            // 오른쪽 돌리기
            for (Integer rightIndex : rightList) {
                tempDirection *= -1;
                gears[rightIndex] = rotation(tempDirection, gears[rightIndex]);
            }

            // 방향 초기화 후 왼쪽 돌리기
            tempDirection = direction;
            for (Integer leftIndex : leftList) {
                tempDirection *= -1;
                gears[leftIndex] = rotation(tempDirection, gears[leftIndex]);
            }

        }

        int count = 0;
        for (int[] gear : gears) {
            if (gear[0] == 1) {
                count += 1;
            }
        }
        System.out.println(count);
    }

    public static int[] rotation(int direction, int[] gear) {
        int save, index;

        if (direction == -1) {
            save = gear[0];
            index = 0;
        } else {
            save = gear[7];
            index = 7;
        }

        for (int i = 0; i < 7; i++) {
            gear[index] = gear[index - direction];
            index -= direction;
        }
        gear[index] = save;
        return gear;
    }

    public static ArrayList<Integer> check(int direction, int index, int[][] gears, int T) {
        ArrayList<Integer> result = new ArrayList<>();
        int i = index;

        if (direction == 1) {
            // 오른쪽
            while (i < T - 1) {
                if (gears[i][2] == gears[i + 1][6]) break;
                else result.add(i + 1);
                i += 1;
            }
        } else {
            // 왼쪽
            while (i > 0) {
                if (gears[i][6] == gears[i - 1][2]) break;
                else result.add(i - 1);
                i -= 1;
            }
        }
        return result;
    }

}
