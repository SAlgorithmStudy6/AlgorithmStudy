import java.io.*;
import java.util.*;

public class Main {

	static int N;
    static int[][] team;
    static boolean[] visited;
    static int[] start;
    static int[] link;
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        team = new int[N + 1][N + 1]; //1부터 시작인덱스

        for (int i = 1; i <= N; i++) {
            StringTokenizer token = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                team[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        visited = new boolean[N + 1];

        combination(1,0);
        
        System.out.println(min);
    }

    static void combination(int index, int size) {
       
    	if (min == 0) { //최솟값이 0이면 탐색종료
			return;
		}
    	
    	if (size == N / 2) { //N/2일 때 팀 인원 배치한것을 기준으로 계산 시작
            min = Math.min(min,getMin());
            return;
        }

        for (int i = index; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(i+1,size + 1);
                visited[i] = false;
            }
        }
    }

    static int getMin() {
    	
    	int diff = Integer.MAX_VALUE;
    	
        start = new int[N / 2];
        link = new int[N / 2];
        
        int sIndex = 0;
        int lIndex = 0;
        
        for (int i = 1; i < visited.length; i++) { //true인 곳은 start false인 곳은 link
            if (visited[i]) {
                start[sIndex] = i;
                sIndex++;
            }else {
                link[lIndex] = i;
                lIndex++;
            }
        }
        
        int sSum = 0;
        int lSum = 0;
        
        for (int i = 0; i < start.length; i++) { //team[i][i]는 0이므로 이중포문으로 팀 능력치 계산
			for (int j = 0; j < start.length; j++) { 
				sSum += team[start[i]][start[j]];
				lSum += team[link[i]][link[j]];
			}
		}
        
        diff = Math.abs(sSum - lSum);
        
        return diff;
        
    }
}
