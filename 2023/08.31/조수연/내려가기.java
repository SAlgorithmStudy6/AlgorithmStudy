import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 1000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] minDp = new int[n + 1][3];
        int[][] maxDp = new int[n + 1][3];

        for (int i = 0; i < 3; i++) {
            minDp[1][i] = arr[1][i];
            maxDp[1][i] = arr[1][i];
        }

        if (n >= 2) {
            for (int i = 2; i <= n; i++) {
                Arrays.fill(minDp[i], MAX);
            }

            for (int i = 2; i <= n; i++) {
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            minDp[i][j] = Math.min(minDp[i][j], minDp[i - 1][j] + arr[i][j]);
                            minDp[i][j + 1] = Math.min(minDp[i][j + 1], minDp[i - 1][j] + arr[i][j + 1]);
                            maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i - 1][j] + arr[i][j]);
                            maxDp[i][j + 1] = Math.max(maxDp[i][j + 1], maxDp[i - 1][j] + arr[i][j + 1]);
                            break;
                        case 1:
                            minDp[i][j] = Math.min(minDp[i][j], minDp[i - 1][j] + arr[i][j]);
                            minDp[i][j + 1] = Math.min(minDp[i][j + 1], minDp[i - 1][j] + arr[i][j + 1]);
                            minDp[i][j - 1] = Math.min(minDp[i][j - 1], minDp[i - 1][j] + arr[i][j - 1]);
                            maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i - 1][j] + arr[i][j]);
                            maxDp[i][j + 1] = Math.max(maxDp[i][j + 1], maxDp[i - 1][j] + arr[i][j + 1]);
                            maxDp[i][j - 1] = Math.max(maxDp[i][j - 1], maxDp[i - 1][j] + arr[i][j - 1]);
                            break;
                        case 2:
                            minDp[i][j] = Math.min(minDp[i][j], minDp[i - 1][j] + arr[i][j]);
                            minDp[i][j - 1] = Math.min(minDp[i][j - 1], minDp[i - 1][j] + arr[i][j - 1]);
                            maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i - 1][j] + arr[i][j]);
                            maxDp[i][j - 1] = Math.max(maxDp[i][j - 1], maxDp[i - 1][j] + arr[i][j - 1]);
                            break;
                    }
                }
            }
        }

        int max = Arrays.stream(maxDp[n]).max().getAsInt();
        int min = Arrays.stream(minDp[n]).min().getAsInt();

        bw.write(max + " " + min);

        bw.flush();
        bw.close();
    }
}