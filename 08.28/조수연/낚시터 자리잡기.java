import java.io.*;
import java.util.*;

public class Solution {

	static int N;
	static int[][] gates = new int[3][2]; // 게이트 value 낚시꾼 value
	static int[] visited; // 낚시터
	static int[] dx = { 1, -1 }; // 방향벡터
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			for (int i = 0; i < gates.length; i++) {
				StringTokenizer token = new StringTokenizer(br.readLine());
				gates[i][0] = Integer.parseInt(token.nextToken());
				gates[i][1] = Integer.parseInt(token.nextToken());
			}

			int[][] order = { { 0, 1, 2 }, { 0, 2, 1 }, { 1, 0, 2 }, { 1, 2, 0 }, { 2, 0, 1 }, { 2, 1, 0 } }; // 3개의 게이트
																												// 순서
			min = Integer.MAX_VALUE;
			for (int i = 0; i < order.length; i++) {
				visited = new int[N + 1];
				int first = order[i][0]; // 첫번째 게이트
				int second = order[i][1]; // 두번째 게이트
				int third = order[i][2]; // 세번째 게이트
				first(0, gates[first], gates[second], gates[third]); // 탐색 시작
			}

			bw.write("#" + String.valueOf(tc) + " " + String.valueOf(min) + "\n");
		}
		bw.flush();
		bw.close();

	}

	static void copy(int[] temp, int[] original) { // 깊은복사

		for (int i = 1; i <= N; i++) {
			temp[i] = original[i];
		}
	}

	static void first(int size, int[] gate1, int[] gate2, int[] gate3) { // 첫번째 게이트 탐색
		int sum = 0;
		int distance = 0;
		int gc = gate1[0];

		if (visited[gc] == 0) { // 시작지점에 비어있으면 거리 +1 낚시터 공간-1
			visited[gc] = 1;
			sum++;
			size++;
		}

		distance++;

		while (true) {
			if (size == gate1[1]) { // 양쪽에 둘다 들어간 경우
				int[] temp = new int[N + 1];
				copy(temp, visited);
				second(0, gate2, gate3, sum, temp);
				break;
			} else if (size == gate1[1] - 1) { // 해당 거리에 낚시꾼이 한명만 남은 경우
				int right = gc + distance;
				int left = gc - distance;
				Queue<Integer> queue = new LinkedList();
				while (true) {

					for (int i = 0; i < 2; i++) { // 방향벡터를 통해 좌우에 들어갈 수 있는 위치에 add
						int location = gc + dx[i] * distance;
						if (location >= 1 && location <= N) {
							if (visited[location] == 0) {
								queue.add(location);
							}
						}
					}

					if (queue.isEmpty()) { // 만약 둘 다 못 들어가면 거리 +1
						distance++;
					} else {
						break;
					}

				}

				if (queue.size() == 1) { // 좌우중 한 자리만 비어 있는 경우
					int location = queue.poll();
					visited[location] = 1;
					sum += distance + 1;
					int[] temp = new int[N + 1];
					copy(temp, visited);
					second(0, gate2, gate3, sum, temp);
				} else { // 좌우 둘다 자리가 비어있어서 두가지 경우가 나타날 경우
					int location = queue.poll();
					sum += distance + 1;
					visited[location] = 1;
					int[] temp = new int[N + 1];
					copy(temp, visited);
					second(0, gate2, gate3, sum, temp); // 2번째 자리 탐색
					visited[location] = 0; // 해당 자리에 1을 넣어주고 0으로 리셋시키고 2번째 자리 탐색
					location = queue.poll();
					visited[location] = 1;
					temp = new int[N + 1];
					copy(temp, visited);
					second(0, gate2, gate3, sum, temp);
				}
				break;
			}

			for (int i = 0; i < 2; i++) { // 좌우에 들어갈 수 있는 위치에 낚시꾼 넣어주기
				int x = gc + dx[i] * distance;
				if (x >= 1 && x <= N) {
					if (visited[x] == 0) {
						visited[x] = 1;
						sum += distance + 1;
						size++;
					}
				}
			}
			distance++; // 한번 탐색할 때마다 기준거리 +1

		}

	}

	static void second(int size, int[] gate2, int[] gate3, int sum, int[] temp) { // 두번째 게이트 탐색 first()부분과 거의 동일

		int distance = 0;
		int gc = gate2[0];

		if (temp[gc] == 0) {
			temp[gc] = 2;
			sum++;
			size++;
		}

		distance++;

		while (true) {
			if (size == gate2[1]) {
				int[] temp2 = new int[N + 1];
				copy(temp2, temp);
				third(0, gate3, sum, temp2);
				break;
			} else if (size == gate2[1] - 1) {
				int right = gc + distance;
				int left = gc - distance;
				Queue<Integer> queue = new LinkedList();
				while (true) {

					for (int i = 0; i < 2; i++) {
						int location = gc + dx[i] * distance;
						if (location >= 1 && location <= N) {
							if (temp[location] == 0) {
								queue.add(location);
							}
						}
					}

					if (queue.isEmpty()) {
						distance++;
					} else {
						break;
					}

				}

				if (queue.size() == 1) {
					int location = queue.poll();
					temp[location] = 2;
					sum += distance + 1;
					int[] temp2 = new int[N + 1];
					copy(temp2, temp);
					third(0, gate3, sum, temp2);

				} else {
					int location = queue.poll();
					sum += distance + 1;
					temp[location] = 2;
					int[] temp2 = new int[N + 1];
					copy(temp2, temp);
					third(0, gate3, sum, temp2);
					temp[location] = 0;
					location = queue.poll();
					temp[location] = 2;
					temp2 = new int[N + 1];
					copy(temp2, temp);
					third(0, gate3, sum, temp2);

				}
				break;
			}

			for (int i = 0; i < 2; i++) {
				int x = gc + dx[i] * distance;
				if (x >= 1 && x <= N) {
					if (temp[x] == 0) {
						temp[x] = 2;
						sum += distance + 1;
						size++;
					}
				}
			}

			distance++;

		}
	}

	static void third(int size, int[] gate3, int sum, int[] temp2) { // 세번째 게이트 탐색 first()부분과 거의 동일
		int distance = 0;
		int gc = gate3[0];

		if (temp2[gc] == 0) {
			temp2[gc] = 3;
			sum++;
			size++;
		}

		distance++;

		while (true) {
			if (size == gate3[1]) {
				min = Math.min(min, sum); // 탐색이 끝나면 min값 갱신시켜주기
				break;
			} else if (size == gate3[1] - 1) {
				int right = gc + distance;
				int left = gc - distance;
				Queue<Integer> queue = new LinkedList();
				while (true) {

					for (int i = 0; i < 2; i++) {
						int location = gc + dx[i] * distance;
						if (location >= 1 && location <= N) {
							if (temp2[location] == 0) {
								queue.add(location);
							}
						}
					}

					if (queue.isEmpty()) {
						distance++;
					} else {
						break;
					}

				}

				if (queue.size() == 1) {
					int location = queue.poll();
					temp2[location] = 3;
					sum += distance + 1;
					min = Math.min(min, sum);
				} else {
					int location = queue.poll();
					sum += distance + 1;
					temp2[location] = 3;
					min = Math.min(min, sum);
					temp2[location] = 0;
					location = queue.poll();
					temp2[location] = 3;
					min = Math.min(min, sum);
				}
				break;
			}

			for (int i = 0; i < 2; i++) {
				int x = gc + dx[i] * distance;
				if (x >= 1 && x <= N) {
					if (temp2[x] == 0) {
						temp2[x] = 3;
						sum += distance + 1;
						size++;
					}
				}
			}

			distance++;

		}
	}
}