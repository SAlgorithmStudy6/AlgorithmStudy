import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 타임머신 {
    static int N;
    static int M;
    static long[] distList;
    static int[] startList;
    static int[] endList;
    static int[] timeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        startList = new int[M];
        endList = new int[M];
        timeList = new int[M];
        distList = new long[N + 1];
        // 도시 500개에 대하여 노선 6천개, 최대시간 10000이므로 모든 버스노선 소요시간이 10000일때 30_000_000_000 이므로 최대값을 long타입 지정
        Arrays.fill(distList, Long.MAX_VALUE);
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            startList[i] = Integer.parseInt(st.nextToken());
            endList[i] = Integer.parseInt(st.nextToken());
            timeList[i] = Integer.parseInt(st.nextToken());
        }
        distList[1] = 0;
        boolean negativeCycle = false;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(distList[startList[j]] != Integer.MAX_VALUE && distList[endList[j]] > distList[startList[j]] + timeList[j]){
                    distList[endList[j]] = distList[startList[j]] + timeList[j];
                    if(i == N - 1){
                        negativeCycle = true;
                    }
                }
            }
        }
        if(negativeCycle){
            System.out.println(-1);
        }else{
            for(int i = 2; i <= N; i++){
                if(distList[i] == Long.MAX_VALUE)
                    System.out.println(-1);
                else System.out.println(distList[i]);
            }
        }
    }
}
