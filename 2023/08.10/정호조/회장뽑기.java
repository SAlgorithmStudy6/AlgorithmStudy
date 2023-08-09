import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 플로이드와샬 문제
 * floyd 배열에 관계를 입력하고, 경유지에 따른 최소 거리를 갱신
 * 각 행의 최댓값이 해당 사람의 점수이고, 최저 점수를 보유한 사람들이 회장이 될 수 있음
 */
public class 회장뽑기_2660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int MAX = 1_000_000;
        int N = Integer.parseInt(br.readLine());
        int[][] floyd = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(floyd[i], MAX);
        }

        while (true) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            if (a == -2 && b == -2) break;

            floyd[a][b] = 1;
            floyd[b][a] = 1;
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j && floyd[i][j] > floyd[i][k] + floyd[k][j]) {
                        floyd[i][j] = floyd[i][k] + floyd[k][j];
                    }
                }
            }
        }

        int[] score = new int[N];   //각 행의 최댓값(해당 사람의 점수)을 저장할 배열
        int min = Integer.MAX_VALUE;    //회장 자격이 되는 사람의 점수

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (floyd[i][j] != MAX) {
                    score[i] = Math.max(score[i], floyd[i][j]);
                }
            }
            min = Math.min(score[i], min);
        }

        ArrayList<Integer> captain = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (score[i] == min) {
                captain.add(i + 1);
            }
        }
        
        Collections.sort(captain);
        
        System.out.println(min + " " + captain.size());
        for(int i : captain){
            System.out.print(i + " ");
        }
    }
}
