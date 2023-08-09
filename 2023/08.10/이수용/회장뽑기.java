import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2660

class 회장뽑기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int dist[][] = new int[N][N];
        // 자기 자신으로 가는 거리 = 0, 최대 50명 이므로 회장 최대 점수는 49점
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(i == j){
                    dist[i][j] = 0;
                }else{
                    dist[i][j] = 50;
                }
            }
        }
        // 모든 사람과의 거리는 1
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if(from == -1 && to == -1) break;
            dist[from - 1][to - 1] = 1;
            dist[to - 1][from - 1] = 1;
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    //j -> k 까지 가는 거리 vs j -> i -> k 거리
                    dist[j][k] = Math.min(dist[j][i] + dist[i][k], dist[j][k]);
                }
            }
        }

        int[] scoreList = new int[N];
        int minScore = 50;
        for(int i = 0; i < N; i++){
            // 각 회장 후보마다 점수를 구해서 최소 점수인 사람을 선출
            int score = Arrays.stream(dist[i]).max().getAsInt();
            scoreList[i] = score;
            if(minScore > score) minScore = score;
        }
        StringBuilder sb1 = new StringBuilder();
        sb1.append(minScore + " ");
        StringBuilder sb2 = new StringBuilder();
        int ansCount = 0;
        for(int i = 0; i < N; i++){
            if(scoreList[i] == minScore){
                ansCount += 1;
                sb2.append(i + 1 + " ");
            }
        }
        sb1.append(ansCount);
        System.out.println(sb1);
        System.out.println(sb2);
    }
}