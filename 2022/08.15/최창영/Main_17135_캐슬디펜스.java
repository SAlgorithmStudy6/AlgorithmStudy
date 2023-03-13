import java.util.*;
import java.io.*;

// 성을 적에게서 지키기 위해 궁수 3명을 배치하려고 한다.
//  궁수의 공격이 끝나면, 적이 이동한다. 적은 아래로 한 칸 이동하며,
//  성이 있는 칸으로 이동한 경우에는 게임에서 제외된다. 모든 적이 격자판에서 제외되면 게임이 끝난다.
public class Main_17135_캐슬디펜스 {
    static int N, M, D;
    static int arr[][];

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/17135.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한

        arr = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<=N-D; i++) {
            int num[] = arr[i];
            System.out.println(Arrays.toString(num));



        }



    } // End of main

    private static int distance(int x1, int x2, int y1, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    } // End of distance
} // End of Main class