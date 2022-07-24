import java.util.*;
import java.io.*;

public class Main_3085_사탕게임 {
	static int max = -1;
	static int N;
	static char arr[][]; // 원본
	static char temp[][]; // 복사본
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/3085.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 가장 많이 먹을 수 있는 사탕의 수는 N개
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		temp = new char[N][N];

		for (int i=0; i<N; i++) {
			String str = br.readLine();
			for (int j=0; j<N; j++) {
				temp[i][j] = arr[i][j] = str.charAt(j);
			}
		}

		// 행 탐색으로 찾은 개수
		find_row();

		// 열 탐색
		find_col();
		
		System.out.println(max);
	} // End of main

	private static void find_row() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N-1; j++) {
				if (arr[i][j] != arr[i][j+1]) {

					// 위치 변경
					temp[i][j] = arr[i][j+1];
					temp[i][j+1] = arr[i][j];
					check();
					copy(); // 원상복구
				}
			}
		}

	} // End of find_row

	private static void find_col() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N-1; j++) {

				if (arr[j][i] != arr[j+1][i]) {

					// 위치 변경
					temp[j][i] = arr[j+1][i];
					temp[j+1][i] = arr[j][i];
					check();
					copy(); // 원상복구
				}
			}
		}
		
	} // End of find_col

	private static void check() {
		
		// 가로 탐색
		int count = 1;
		for (int i=0; i<N; i++) {
			count = 1;
			
			for(int j=0; j<N-1; j++) {
				if(temp[i][j] == temp[i][j+1]) {
					count++;
				}
				else {
					count = 1;
				}
				
				max = Math.max(max, count);
			}
		}
		
		// 세로 탐색
		for (int i=0; i<N; i++) {
			count = 1;
			
			for(int j=0; j<N-1; j++) {
				if(temp[j][i] == temp[j+1][i]) {
					count++;
				}
				else {
					count = 1;
				}
				
				max = Math.max(max, count);
			}
		}

	} // End of check

	private static void copy() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				temp[i][j] = arr[i][j];
			}
		}
	} // End of copy

} // End of Main class