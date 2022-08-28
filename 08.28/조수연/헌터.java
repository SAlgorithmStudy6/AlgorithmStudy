import java.io.*;
import java.util.*;

public class Solution2 {

	static int N;
	static int[] card; // 카드 배열
	static boolean isSorted; // 정렬되어있는지 확인 변수
	static int count; // 셔플 횟수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			card = new int[N + 1]; // 카드
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				card[i] = Integer.parseInt(token.nextToken());
			}

			isSorted = false;
			count = 0;

			// 0번일 경우 정렬체크
			sortCheck(card);
			reverseCheck(card);

			if (isSorted) { // 정렬되어있다면 바로 셔플
				bw.write("#" + String.valueOf(tc) + " " + String.valueOf(count) + "\n");
			} else { // 아니라면 셔플 시작
				for (int i = 1; i <= 5; i++) {
					int[] order = new int[i];
					combination(0, i, order);
					if (isSorted) { // 정렬되어있다면 반복문 종료
						break;
					}
				}
				if (isSorted) {
					bw.write("#" + String.valueOf(tc) + " " + String.valueOf(count) + "\n");
				} else {
					bw.write("#" + String.valueOf(tc) + " -1\n");
				}
			}

		}
		bw.flush();
		bw.close();

	}

	static void combination(int index, int size, int[] order) {

		if (isSorted) { // 정렬되어있다면 함수 종료
			return;
		}

		if (index == size) { // 셔플 순서 정해지면 셔플하기
			shuffle(order);
			if (isSorted) {
				count = index;
			}
			return;
		}

		for (int i = 1; i <= N - 1; i++) {
			order[index] = i;
			combination(index + 1, size, order);
		}
	}

	static void shuffle(int[] order) {
		int temp[] = new int[N + 1]; // 임시배열 초기화
		copy(temp);

		for (int i = 0; i < order.length; i++) {
			int start = N / 2; // 셔플 위치
			if (order[i] < start) { // 123 | 456 순서일 때
				for (int j = 1; j <= order[i]; j++) { // order[i]만큼 반복
					start = start - j + 1; // 셔플 인덱스
					for (int k = 1; k <= j; k++) { // 셔플이 끝나면 다음 셔플할 때 시작점이 +2인 인덱스 셔플
						swap(start, start + 1, temp);
						start += 2;
					}
					start = N / 2; // order[i]번인 셔플이 끝나면 start 변수 초기화
				}
			} else { // 456 | 123 순일때
				if (order[i] == N - 1) { // order[i]가 N-1일 때
					rCopy(temp); // 역순 배열 초기화
				} else {
					rCopy(temp);
					for (int j = 1; j <= N - 1 - order[i]; j++) { // 역순으로 정렬한 후에 위에 반복문 로직과 같음
						start = start - j + 1;
						for (int k = 1; k <= j; k++) {
							swap(start, start + 1, temp);
							start += 2;
						}
						start = N / 2;
					}
				}
			}
		}

		sortCheck(temp); // 오름차순 정렬인지 확인
		reverseCheck(temp); // 내림차순 정렬인지 확인

	}

	static void copy(int[] temp) { //원본 복사 배열(깊은 복사)
		for (int i = 1; i <= N; i++) {
			temp[i] = card[i];
		}
	}

	static void rCopy(int[] temp) { //123 456 -> 456 123
		for (int i = 1; i <= N / 2; i++) {
			swap(i, (N / 2) + i, temp);
		}
	}

	static void swap(int a, int b, int[] temp) { //배열 인덱스 교환
		int temp2 = temp[a];
		temp[a] = temp[b];
		temp[b] = temp2;
	}

	static void sortCheck(int[] arr) { //오름차순 정렬
		boolean check = true;
		for (int i = 1; i <= N; i++) {
			if (arr[i] != i) {
				check = false;
				break;
			}
		}

		if (check) {
			isSorted = true;
		}
	}

	static void reverseCheck(int[] arr) { //내림차순 정렬
		boolean check = true;
		int rCount = N;
		for (int i = 1; i <= N; i++) {
			if (arr[i] != rCount) {
				check = false;
				break;
			}
			rCount--;
		}
		if (check) {
			isSorted = true;
		}
	}
}
