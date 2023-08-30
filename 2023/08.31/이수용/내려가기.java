import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 내려가기 {
    static int N;
    static int[][] lineList;
    static int[][] maxDp;
    static int[][] minDp;
    static int maxAnswer = 0;
    static int minAnswer = 900_000;
    private static final int MAX_VALUE = 900_000;
    private static final int MIN_VALUE = -900_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lineList = new int[N][3];
        maxDp = new int[N][3];
        minDp = new int[N][3];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                lineList[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < 3; i++){
            maxDp[0][i] = lineList[0][i];
            minDp[0][i] = lineList[0][i];
        }
        for(int i = 1; i < N; i++){
            int maxOneTwo = Math.max(maxDp[i-1][0], maxDp[i-1][1]);
            int maxTwoThree = Math.max(maxDp[i-1][1], maxDp[i-1][2]);
            int maxOneTwoThree = Math.max(maxDp[i-1][2], Math.max(maxDp[i-1][0], maxDp[i-1][1]));
            int minOneTwo = Math.min(minDp[i-1][0], minDp[i-1][1]);
            int minTwoThree = Math.min(minDp[i-1][1], minDp[i-1][2]);
            int minOneTwoThree = Math.min(minDp[i-1][2], Math.min(minDp[i-1][0], minDp[i-1][1]));
            maxDp[i][0] = lineList[i][0] + maxOneTwo;
            minDp[i][0] = lineList[i][0] + minOneTwo;
            maxDp[i][1] = lineList[i][1] + maxOneTwoThree;
            minDp[i][1] = lineList[i][1] + minOneTwoThree;
            maxDp[i][2] = lineList[i][2] + maxTwoThree;
            minDp[i][2] = lineList[i][2] + minTwoThree;
        }
        maxAnswer = Math.max(maxAnswer, Arrays.stream(maxDp[N-1]).max().getAsInt());
        minAnswer = Math.min(minAnswer, Arrays.stream(minDp[N-1]).min().getAsInt());
        System.out.printf("%d %d", maxAnswer, minAnswer);
    }
}
