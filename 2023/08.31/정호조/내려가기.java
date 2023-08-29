import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 'RGB거리(1149번)과 비슷한 유형의 문제
 * 최댓값과 최솟값을 저장할 배열을 만들고, i행 값에 i-1행의 값을 조건에 따라 누적
 * */

public class 내려가기_2096 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] max = new int[N][3];
        int[][] min = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int value = Integer.parseInt(st.nextToken());
                max[i][j] = value;
                min[i][j] = value;
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 0 || j == 2) {
                    int maxA = max[i][j] + max[i - 1][j];
                    int maxB = max[i][j] + max[i - 1][1];

                    int minA = min[i][j] + min[i - 1][j];
                    int minB = min[i][j] + min[i - 1][1];

                    max[i][j] = Math.max(maxA, maxB);
                    min[i][j] = Math.min(minA, minB);
                } else {
                    int maxTemp = max[i][j];    //아래 for문 돌 때 max와 min 값이 변하므로 임시 저장해둠
                    int minTemp = min[i][j];
                    min[i][1] += min[i - 1][0]; //최초 한 번 더해줘야 최솟값 비교 가능

                    for (int k = 0; k < 3; k++) {
                        max[i][j] = Math.max(max[i][j], maxTemp + max[i - 1][k]);
                        min[i][j] = Math.min(min[i][j], minTemp + min[i - 1][k]);
                    }
                }
            }
        }

        int maxResult = 0;
        int minResult = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            maxResult = Math.max(maxResult, max[N - 1][i]);
            minResult = Math.min(minResult, min[N - 1][i]);
        }

        System.out.println(maxResult + " " + minResult);
    }
}
