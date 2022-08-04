import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {

	static int M;
	static int N;
	static int K;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visitied = new boolean[101][101];
	static boolean[][] area = new boolean[101][101];
	static int count = 0;
	static List<Integer> list = new ArrayList<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer token = new StringTokenizer(br.readLine());

		M = Integer.parseInt(token.nextToken());
		N = Integer.parseInt(token.nextToken());
		K = Integer.parseInt(token.nextToken());
		
		for (int i = 0; i < K; i++) {
			token = new StringTokenizer(br.readLine());
				int x1 = Integer.parseInt(token.nextToken());
				int y1 = Integer.parseInt(token.nextToken());
				int x2 = Integer.parseInt(token.nextToken());
				int y2 = Integer.parseInt(token.nextToken());
				
				for (int y = M - y2 +1; y<= M - y1 ; y++) { //영역채우기
					for (int x = x1+1; x <= x2; x++) {
						area[y][x] = true; 
					}
				}
		}
		
		for (int i = 1; i <= M ; i++) { //방문하지 않고 영역이 색칠되지 않았을때 시작
			for (int j = 1; j <= N; j++) {
				if (!visitied[i][j] && !area[i][j]) {
					count = 0;
					bfs(i,j);
					list.add(count);
				}
			}
		}
		
		Collections.sort(list); //정렬
		
		bw.write(list.size()+"\n");
		
		for (int i = 0; i < list.size(); i++) {
			bw.write(list.get(i)+" ");
		}

		bw.flush();
		bw.close();
		
	}
	
	static void bfs(int x, int y) {
		Queue<Point> squarePoint = new LinkedList<>();
		squarePoint.add(new Point(x,y));
		visitied[x][y] = true;
		count++;
		
		while(!squarePoint.isEmpty()) {
			
			Point point = squarePoint.poll();
			
			for (int i = 0; i < 4; i++) {
				
				int pointX = point.x + dx[i];			
				int pointY = point.y + dy[i];
				
				if (pointX >= 1 && pointX <= M && pointY >= 1 && pointY <= N) {
					
					if (!visitied[pointX][pointY] && !area[pointX][pointY]) {
						visitied[pointX][pointY] = true;
						squarePoint.add(new Point(pointX,pointY));
						count++;
					}
				}
			}
		}
	}
}
