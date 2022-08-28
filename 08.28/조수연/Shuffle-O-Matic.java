import java.awt.Point;
import java.io.*;
import java.util.*;

public class Solution3 {

	static int N;
	static int[][] field; // 필드
	static boolean[] hunted; // 몬스터가 잡혔는지 체크 배열
	static boolean[] existed; // 몬스터가 존재하는지 체크 배열
	static int[][] customers; // 고객 좌표
	static int[][] monsters; // 몬스터 좌표
	static int[] dx = { 0, 0, -1, 1 }; // 좌표이동
	static int[] dy = { -1, 1, 0, 0 };
	static int hunterX; // 헌터의 x좌표
	static int hunterY; // 헌터의 y좌표
	static List<Integer> list; // 몬스터 + 고객 리스트
	static boolean[] checked; // 순열 조합 체크 배열
	static int minTime; // 최소 시간

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			field = new int[N + 1][N + 1];
			monsters = new int[5][2];
			existed = new boolean[5];
			list = new ArrayList();
			customers = new int[5][2];
			int size = 0; // 최대 크기 ex 몬스터가 1,2 있으면 최대크기 2 * 2 = 4

			for (int i = 1; i <= N; i++) {
				StringTokenizer token = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					field[i][j] = Integer.parseInt(token.nextToken());

					if (field[i][j] != 0) {
						size = Math.max(size, Math.abs(field[i][j]));
						if (field[i][j] < 0) { // 고객좌표
							customers[Math.abs(field[i][j])][0] = i;
							customers[Math.abs(field[i][j])][1] = j;
							list.add(field[i][j]);

						} else { // 몬스터 좌표
							monsters[field[i][j]][0] = i;
							monsters[field[i][j]][1] = j;
							existed[field[i][j]] = true; // 몬스터 존재 체크
							list.add(field[i][j]);
						}
					}

				}
			}

			hunted = new boolean[5];

			int orderSize = size * 2; // 순열 크기

			minTime = Integer.MAX_VALUE;

			checked = new boolean[list.size()];
			int[] order = new int[orderSize];
			combination(0, orderSize, order); // 헌팅시작

			bw.write("#" + String.valueOf(tc) + " " + String.valueOf(minTime) + "\n");
		}
		bw.flush();
		bw.close();

	}

	static void combination(int index, int size, int[] order) {
		if (index == size) {
			int time = 0;
			hunterX = 1;
			hunterY = 1;
			for (int i = 0; i < order.length; i++) {
				if (order[i] < 0) { //방문 좌표가 고객일 때
					for (int j = 1; j < customers.length; j++) {
						if (j == Math.abs(order[i])) { 
							int x = Math.abs(hunterX - customers[j][1]); //x 거리
							int y = Math.abs(hunterY - customers[j][0]); //y 거리
							time += x + y; //시간은 현재 헌터 위치에서 목표 위치까지의 거리
							hunterX = customers[j][1]; //헌터 위치 갱신
							hunterY = customers[j][0];
						}
					}
				} else {
					for (int j = 1; j < monsters.length; j++) {
						if (j == order[i]) {//방문 좌표가 몬스터일 때
							int x = Math.abs(hunterX - monsters[j][1]);
							int y = Math.abs(hunterY - monsters[j][0]);
							time += x + y;
							hunterX = monsters[j][1];
							hunterY = monsters[j][0];
						}
					}
				}
				if (time > minTime) { //소요시간이 최소시간보다 길 경우 중간에 빠져나오기
					break;
				}
			}
			minTime = Math.min(minTime, time);
			return;
		}

		for (int i = 0; i < list.size(); i++) {
			if (index == 0) { //첫번째 방문 타겟 설정
				if (list.get(i) > 0 || hunted[Math.abs(list.get(i))]) { //몬스터이거나 해당 번호의 몬스터를 잡은 고객의 위치
					order[index] = list.get(i);
					checked[i] = true;
					hunted[Math.abs(list.get(i))] = true;
					existed[Math.abs(list.get(i))] = false;
					combination(index + 1, size, order);
					checked[i] = false;
					hunted[Math.abs(list.get(i))] = false;
					existed[Math.abs(list.get(i))] = true;
				}
			} else {
				if (!checked[i]) { //중복 체크
					if (list.get(i) < 0 && hunted[Math.abs(list.get(i))]) { //해당 번호를 잡은 고객의 위치이면 add
						order[index] = list.get(i);
						checked[i] = true;
						combination(index + 1, size, order);
						checked[i] = false;
					} else if (list.get(i) > 0 && existed[list.get(i)]) { //잡히지 않은 몬스터이면 add
						order[index] = list.get(i);
						checked[i] = true;
						hunted[Math.abs(list.get(i))] = true;
						existed[Math.abs(list.get(i))] = false;
						combination(index + 1, size, order);
						checked[i] = false;
						hunted[Math.abs(list.get(i))] = false;
						existed[Math.abs(list.get(i))] = true;
					}
				}
			}
		}
	}

	static int hunting(int target) { // BFS로 푸는 로직

		int count = 0;

		Queue<Point> queue = new LinkedList();
		queue.add(new Point(hunterX, hunterY));

		boolean[][] visited = new boolean[N + 1][N + 1];
		visited[hunterY][hunterX] = true;

		while (!queue.isEmpty()) {
			int qSize = queue.size();
			for (int j = 0; j < qSize; j++) {
				Point point = queue.poll();
				if (field[point.y][point.x] == target) {
					hunterX = point.x;
					hunterY = point.y;
					return count;
				}
				for (int k = 0; k < dx.length; k++) {
					int x = point.x + dx[k];
					int y = point.y + dy[k];

					if (x >= 1 && x <= N && y >= 1 && y <= N && !visited[y][x]) {
						visited[y][x] = true;
						queue.add(new Point(x, y));
					}
				}
			}
			count++;
		}
		return count;
	}
}
