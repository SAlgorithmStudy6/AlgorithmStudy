import java.io.*;
import java.util.*;

class Coo {
	int x, y;

	Coo(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int N, cnt, maxL, minL, bigCnt;
	static int map[][];
	static boolean visited[][];
	static int dx[] = { 0, 0, -1, 1 };
	static int dy[] = { 1, -1, 0, 0, };

	public static void main(String[] args) throws Exception {
		// 안전 영역
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// N 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		// map 입력
		map = new int[N][N];
		visited = new boolean[N][N];

		maxL = Integer.MIN_VALUE; // 최고 높이
		minL = Integer.MAX_VALUE; // 최저 높이
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (maxL < map[i][j]) {
					maxL = map[i][j];
				}
				if (minL > map[i][j]) {
					minL = map[i][j];
				}
			}
		}

		for (int k = minL; k < maxL; k++) {
			// 초기화
			cnt = 0;
			for(boolean[] b : visited) {
				Arrays.fill(b, false);
			}
            // 물에 가라 앉히기
			visitCheck(k); 

			
			// 물에 가라 앉지 않은 땅 개수 세기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						bfs(i, j);
						cnt++;
					}
				}
			}
			if(cnt > bigCnt) {
				bigCnt = cnt;
			}
		}
		if (bigCnt == 0)
			System.out.println(1);
		else
			System.out.println(bigCnt);

	}

	// 가서 방문체크만 다 하고 땅 덩어리 개수 cnt에 담아
	static void bfs(int i, int j) {
		
		Queue<Coo> q = new LinkedList<>();
		q.offer(new Coo(i, j));

		while(!q.isEmpty()) {
			Coo a = q.poll();
			visited[a.x][a.y] = true;
			
			for(int f=0; f<4; f++) {
				int nx = a.x + dx[f];
				int ny = a.y + dy[f];
				
				if(0 <= nx && nx < N && 0 <= ny && ny < N) {
					if(!visited[nx] [ny]) {
						q.add(new Coo(nx, ny));
						visited[nx][ny] = true;
					}
					
				}
			}
			
		}
		
	}
	
	
	static void visitCheck(int k) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j] <= k) {
					visited[i][j] = true;
				}
			}
		}
	}
}
