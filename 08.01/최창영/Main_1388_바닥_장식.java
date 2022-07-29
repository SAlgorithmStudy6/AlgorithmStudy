import java.util.*;
import java.io.*;

// 기훈이의 방 바닥을 장식하는데 필요한 나무 판자의 개수를 출력하는 프로그램을 작성하시오.

// -는 인접, 같은 행 , |는 인접 같은 열

public class Main_1388_바닥_장식 {
	static int N;
	static int M;
	static char arr[][];
	static boolean visit[][];
	static int nowX; static int nowY;
	static int dirX[] = {0, 0, -1, 1};
	static int dirY[] = {-1, 1, 0, 0};
	
	static int result = 0;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/1388.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new char[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = temp.charAt(j);
			}
		}

		int result = 0;
		// 가로 모양 타일 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visit[i][j] && arr[i][j] == '-') {
					DFS(i, j, 0, 2, '-');
					result++;
				}
			}
		}
		

		// 세로
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(!visit[j][i] && arr[j][i] == '|') {
					DFS(j, i, 2, 4, '|');
					result++;
				}
			}
		}

		System.out.println(result);
	} // End of main

	private static void DFS(int x, int y, int idxStart, int idxEnd, char ch) {
		visit[x][y] = true;
		
		for(int i=idxStart; i<idxEnd; i++) {
			nowX = dirX[i] + x;
			nowY = dirY[i] + y;
			
			if(range_check() && !visit[nowX][nowY] && arr[nowX][nowY] == ch) {
				DFS(nowX, nowY, idxStart, idxEnd, ch);
			}
		}
	} // End of DFS
	
	private static boolean range_check() {
		return nowX >= 0 && nowX < N && nowY >= 0 && nowY < M;
	} // End of range_check
} // End of Main class