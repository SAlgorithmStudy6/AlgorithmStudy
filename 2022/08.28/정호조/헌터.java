package swea;

import java.util.*;
import java.io.*;

public class 헌터_A형모의테스트 {
	static int[][] map;
	static ArrayList<Integer> arr; // map입력 받다가 몬스터, 집 나오면 넣어줌
	static int[] per; // 몬스터, 집 순열 돌림(단, 몬스터 고객 집 전에 몬스터가 나와야함)
	static int[][] perinfo; // 순열 돌린 몬스터, 고객 좌표
	static boolean[] pvis; // 순열 돌릴 때 사용
	static int ans, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new ArrayList<>();
			map = new int[N][N];
			ans = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0) {
						arr.add(map[i][j]);
					}
				}
			}

			per = new int[arr.size()];
			perinfo = new int[arr.size()][2];
			pvis = new boolean[arr.size()];

			permutation(0, arr.size(), arr.size());
			for (int i = 0; i < per.length; i++)
				System.out.print(per[i] + " ");
			System.out.println();
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	// arr갖고 방문할 순열 만들어줌, 조건 고려하지 않고 일단 그냥 다 만듦
	static void permutation(int index, int n, int m) {
		if (index == m) {
			check();
			return;
		}
		for (int i = 0; i < n; i++) {
			if (pvis[i] == false) {
				per[index] = arr.get(i);
				pvis[i] = true;
				permutation(index + 1, n, m);
				pvis[i] = false;
			}
		}
	}

	//만든 순열이 조건에 부합하는지 확인, 부합하면 다음 단계로 넘어감
	static void check() {
		Loop1: for (int i = 0; i < per.length; i++) {
			int check = 0;
			if(per[0] < 0) break Loop1;	//처음부터 집 방문이면 함수 종료(다음 단계 못감)
			if (per[i] < 0) {	//per[i]가 음수일 때(집일때)
				for (int j = 0; j < i; j++) {
					if (per[j] == Math.abs(per[i])) {	//앞에서 뽑 순열 중에 고객의 몬스터가 있는지 확인
						check = 1;
					}
				}
				if (check == 0)	//없으면  함수 종료(다음 단계로 못감)
					break Loop1;
			}
			if (i == per.length - 1)
				permutationinfo();
		}
	}

	// 순열 돌린 몬스터, 집의 좌표 구함
	static void permutationinfo() {
		for (int k = 0; k < per.length; k++) {
			Loop1: for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (per[k] == map[i][j]) {
						perinfo[k][0] = i;
						perinfo[k][1] = j;
						break Loop1; // 좌표 찾았으면 더 찾지말고 바로 다음 per의 좌표 찾아줌
					}
				}
			}
		}
		callength();
	}

	static void callength() {
		int len = 0;
		len = perinfo[0][0] + perinfo[0][1];
		for (int i = 0; i < per.length - 1; i++) {
			len += Math.abs(perinfo[i][0] - perinfo[i + 1][0]) + Math.abs(perinfo[i][1] - perinfo[i + 1][1]);
		}
		ans = Math.min(ans, len);
	}

}
