import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 처음 애벌래는 0열 아래에서 위로, 0행 왼쪽에서 오른쪽으로 성장함
 * 그 이후 (1,1) 부터 (M-1 , M-1)의 애벌래들은 자신의 좌, 좌상, 상의 애벌래 성장량만 확인
 * 0열, 0행에 주어지는 성장량은 0, 1, 2가 순차적으로 주어지므로 성장량을 배열에 담는다면 배열의 뒤로 갈 수록 값이 커짐
 * 즉 (M-1, 0)의 성장량이 (0, M-1)의 성장량 보다 큰 경우의 수는 없음(같을 수는 있음)
 * 따라서 나머지 애벌래들이 체크해야하는 애벌래는 좌, 좌상, 상 중 상에 위치한 애벌래만 체크하면 됨(최초 애벌래가 성장할 때 방향이 (M-1, 0)에서 시작하여 (0, M-1)에서 끝나므로)
 * 주어진 성장량을 0열과 0행에 한 번에 성장시킨 후 나머지 애벌래는 각 열의 0번째 행 값을 복사
 * */
public class 여왕벌_10836 {
    static int M, N;
    static int[][] growth, larva;
    static int[] curGrowth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        growth = new int[N][3];
        larva = new int[M][M];
        curGrowth = new int[2 * M - 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                growth[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getResult();

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(larva[i][j] + " ");
            }
            System.out.println();
        }

    }

    static void getResult() {

        //애벌래 1로 초기화
        for (int i = 0; i < M; i++) {
            Arrays.fill(larva[i], 1);
        }

        //0열, 0행을 모든 날짜에 대해 성장시킴
        for (int day = 0; day < N; day++) {
//            makeCurGrowth(day); //현재 날짜의 성장량을 배열에 담아줌
            initGrowLarva(day);    //0열, 0행을 성장시킴
        }

        growLarva();    //나머지 성장시킴
    }

    static void makeCurGrowth(int day) {
        int idx = 0;
        Arrays.fill(curGrowth, 0);
        for (int i = 0; i < 3; i++) {
            if (growth[day][i] == 0) continue;

            switch (i) {
                case 0: {
                    curGrowth[idx] = 0;
                    break;
                }
                case 1: {
                    curGrowth[idx] = 1;
                    break;
                }
                case 2: {
                    curGrowth[idx] = 2;
                    break;
                }
            }

            growth[day][i]--;
            i--;
            idx++;
        }
    }

    static void initGrowLarva(int day) {
        int r = M - 1;
        int c = 0;

        while (r != 0 || c != M) {
            int value = 0;
            if(growth[day][0] > 0){
                growth[day][0]--;
            } else if(growth[day][1] > 0) {
                value = 1;
                growth[day][1]--;
            } else {
                value = 2;
                growth[day][2]--;
            }

            larva[r][c] = larva[r][c] + value;

            if (r != 0) {
                r--;
            } else {
                c++;
            }
        }
    }

    static void growLarva() {
        for (int i = 1; i < M; i++) {
            int value = larva[0][i];
            for (int j = 1; j < M; j++) {
                larva[j][i] = value;
            }
        }
    }
}
