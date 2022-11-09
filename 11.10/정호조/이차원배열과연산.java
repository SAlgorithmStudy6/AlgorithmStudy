import java.util.*;
import java.io.*;

public class 이차원배열과연산_17140 {

	static int r, c, k, time, ans, R, C;
	static int[][] arr;
	static ArrayList<Value> list;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken()) - 1;
		c = Integer.parseInt(st.nextToken()) - 1;
		k = Integer.parseInt(st.nextToken());

		arr = new int[100][100];
		list = new ArrayList<>();
		ans = -1;
		time = 0;
		R = 3;
		C = 3;

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (time <= 100) {
			checkRC();

			if (checkResult()) {
				ans = time;
				break;
			}

			if (R >= C) {
				calR();
			} else {
				calC();
			}

			time++;

			checkRC();
		}

		System.out.println(ans);
	}

	static boolean checkResult() {
		if (arr[r][c] == k) {
			return true;
		} else {
			return false;
		}
	}

	static void checkRC() {
		// 행 수 체크
		Loop1: for (int i = 0; i < 100; i++) {
			int rTemp = 0;
			for (int j = 0; j < 100; j++) {
				if (arr[0][i] == 0) {
					break Loop1;
				}
				if (arr[j][i] == 0) {
					break;
				} else if (arr[j][i] != 0) {
					rTemp++;
					R = Math.max(R, rTemp);
				}
			}
		}

		// 열 수 체크
		Loop1: for (int i = 0; i < 100; i++) {
			int cTemp = 0;
			for (int j = 0; j < 100; j++) {
				if (arr[i][0] == 0) {
					break Loop1;
				}
				if (arr[i][j] == 0) {
					break;
				} else if (arr[i][j] != 0) {
					cTemp++;
					C = Math.max(C, cTemp);
				}
			}
		}
	}

	static void calR() {
		for (int i = 0; i < R; i++) {
			list.clear();
			for (int j = 0; j < C; j++) {
				if (arr[i][j] != 0) { // list에 입력
					if (!checkListDuplicate(arr[i][j])) {// list에 arr[i][j]가 없으면 list에 넣어줌
						list.add(new Value(arr[i][j], 1));
					} else { // list에 이미 arr[i][j]가 있으면 등장 회수 증가
						for (int t = 0; t < list.size(); t++) {
							if (list.get(t).value == arr[i][j]) {
								list.get(t).times++;
							}
						}
					}
				}
			}
			// 수 오름차순으로 먼저 정렬하고 등장회수 오름차순으로 정렬 -> 등장회수 오름차순이 1순위
			list.sort(new Comparator<Value>() {
				
				@Override
				public int compare(Value o1, Value o2) {
					// TODO Auto-generated method stub
					return o1.value - o2.value;
				}
			});
			list.sort(new Comparator<Value>() {

				@Override
				public int compare(Value o1, Value o2) {
					// TODO Auto-generated method stub
					return o1.times - o2.times;
				}
			});


			// 배열 갱신
			int idx = 0;
			int size = list.size();
			if(size > 100) size = 100;
			for (int k = 0; k < size; k++) {
				arr[i][idx++] = list.get(k).value;
				arr[i][idx++] = list.get(k).times;
			}
			for(int k=idx; k<100; k++) {	//뒤쪽 전부 0으로 만들어줌
				arr[i][k] = 0;
			}
		}
	}

	static void calC() {
		for (int i = 0; i < C; i++) {
			list.clear();
			for (int j = 0; j < R; j++) {
				if (arr[j][i] != 0) { // list에 입력
					if (!checkListDuplicate(arr[j][i])) {// list에 arr[j][i]가 없으면 list에 넣어줌
						list.add(new Value(arr[j][i], 1));
					} else { // list에 이미 arr[i][j]가 있으면 등장 회수 증가
						for (int t = 0; t < list.size(); t++) {
							if (list.get(t).value == arr[j][i]) {
								list.get(t).times++;
							}
						}
					}
				}
			}
			// 수 오름차순으로 먼저 정렬하고 등장회수 오름차순으로 정렬 -> 등장회수 오름차순이 1순위
			list.sort(new Comparator<Value>() {

				@Override
				public int compare(Value o1, Value o2) {
					// TODO Auto-generated method stub
					return o1.value - o2.value;
				}
			});
			list.sort(new Comparator<Value>() {

				@Override
				public int compare(Value o1, Value o2) {
					// TODO Auto-generated method stub
					return o1.times - o2.times;
				}
			});

			// 배열 갱신
			int idx = 0;
			int size = list.size();
			if(size > 100) size = 100;
			for (int k = 0; k < size; k++) {
				arr[idx++][i] = list.get(k).value;
				arr[idx++][i] = list.get(k).times;
			}
			for(int k=idx; k<100; k++) {
				arr[k][i] = 0;
			}
		}
	}

	static boolean checkListDuplicate(int num) {
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).value == num) {
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	public static class Value {
		int value, times;

		public Value(int value, int times) {
			this.value = value;
			this.times = times;
		}
	}
}
