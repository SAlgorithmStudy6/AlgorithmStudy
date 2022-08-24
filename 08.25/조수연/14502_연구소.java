import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int M;
	static int[][] laboratory;
	static int[][] copy;
	static List<Point> virusList;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static boolean[][] visited;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());

		N = Integer.parseInt(token.nextToken());
		M = Integer.parseInt(token.nextToken());

		laboratory = new int[N][M];
		copy = new int[N][M]; //깊은 복사 배열
		virusList = new ArrayList();

		for (int i = 0; i < N; i++) {
			token = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				laboratory[i][j] = Integer.parseInt(token.nextToken());
				copy[i][j] = laboratory[i][j];
				if (laboratory[i][j] == 2) { //바이러스 좌표 넣기
					virusList.add(new Point(j, i));
				}
			}
		}

		setting(0, 0);
		
		System.out.println(max);

	}

	static void copy(int[][] temp) { //복사
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = laboratory[i][j];
			}
		}
	}

	static void setting(int size, int start) {
		if (size == 3) { //벽을 3개 쳤을 때 최댓값
			max = Math.max(virus(),max);
			return;
		}

		for (int i = start; i < N * M; i++) { //0,0부터 laboratory[i][j]가 0인 곳에 벽 설치하기
			int row = i / M; 
			int col = i % M;
			if (laboratory[row][col] == 0) {
				laboratory[row][col] = 1;
				setting(size + 1, i + 1);
				laboratory[row][col] = 0;
			}
		}
	}

	static int virus() {
		int count = 0;
		Queue<Point> queue = new LinkedList();
		visited = new boolean[N][M];
		
		int temp[][] = new int[N][M];
		
		copy(temp); //임시 배열 복사
		
		for (int i = 0; i < virusList.size(); i++) { //바이러스 좌표를 queue에 poll
			queue.add(new Point(virusList.get(i).x, virusList.get(i).y));
			visited[virusList.get(i).y][virusList.get(i).x] = true;
		}

		while (!queue.isEmpty()) { //바이러스 퍼지는 메소드
			Point point = queue.poll();
			for (int i = 0; i < dx.length; i++) {
				int x = point.x + dx[i];
				int y = point.y + dy[i];
				
				if (x >= 0 && x < M && y >= 0 && y < N && !visited[y][x] && temp[y][x] == 0) {
					visited[y][x] = true;
					temp[y][x] = 2; 
					queue.add(new Point(x,y));
				}
			}
			
				
		}
		
		for (int i = 0; i < N; i++) { //안전영역 구하기
			for (int j = 0; j < M; j++) {
				if (temp[i][j] == 0 ) {
					count++;
				}
			}
		}
		
		return count;
	}
}
