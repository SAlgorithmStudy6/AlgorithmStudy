import java.io.*;
import java.util.*;

public class Main {

	static int[][] paper; //종이
	static int[] papers; //색종이 카운트
	static int result; //최소값

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		paper = new int[10][10];

		for (int i = 0; i < paper.length; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			for (int j = 0; j < paper.length; j++) {
				paper[i][j] = Integer.parseInt(token.nextToken());
			}
		}

		papers = new int[6];
		for (int i = 1; i < papers.length; i++) {
			papers[i] = 5;
		}

		result = Integer.MAX_VALUE;

		search(0, 0, 0);

		if (result == Integer.MAX_VALUE) { //제한된 갯수보다 많이 붙인경우
			result = -1;
		}

		System.out.println(result);
	}

	static void search(int row, int col, int count) {
		if (row >= 9 && col > 9) { //마지막점에 도달했을 때 result값 갱신
			result = Math.min(result, count);
			return;
		}

		if (result <= count) { //result값이 count값보다 작거나 같으면 탐색할 필요없으므로 다음 탐색
			return;
		}

		if (col > 9) { //다음 행 검색
			search(row + 1, 0, count);
			return;
		}

		if (paper[row][col] == 1) {
			for (int i = 5; i > 0; i--) { //5크기 종이부터 탐색
				if (papers[i] > 0 && attach(row, col, i)) { //남아있는 종이 수 && 배열의 범위 벗어나지 않는지 체크 + 다음 인덱스 값이 0인지 체크
					init(row, col, i, 0);
					papers[i]--; //i 크기의 종이 수 감소
					search(row, col + 1, count + 1);
					init(row, col, i, 1);
					papers[i]++;
				}
			}
		} else {
			search(row, col + 1, count); //0이므로 다음 검색
		}

	}

	static void init(int row, int col, int size, int state) { //value 값 0으로 갱신 후 재귀함수 종료후에 1로 채우기 
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				paper[i][j] = state;
			}
		}
	}

	static boolean attach(int row, int col, int size) { 
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (i < 0 || i >= 10 || j < 0 || j >= 10) {
					return false;
				}

				if (paper[i][j] != 1) {
					return false;
				}
			}
		}
		return true;
	}
}
