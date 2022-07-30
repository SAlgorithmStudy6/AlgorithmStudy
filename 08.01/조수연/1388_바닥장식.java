import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer token = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(token.nextToken());
		int M = Integer.parseInt(token.nextToken());
		
		char[][] board = new char[N][M];
		char width = 'w';
		char height = 'h';
		int count = 0;
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				board[i][j] = input.charAt(j);
			}
		}
		
		//°¡·ÎÁÙ Å½»ö
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (width != '-') {
					if (board[i][j] == '-') {
						count++;
						width = '-';
					}else continue;
				}else {
					if (board[i][j] == '-' ) continue;
					else width = 'w';
				}
			}
			width = 'w';
			
		}
		
		//¼¼·ÎÁÙ Å½»ö
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (height != '|') {
					if (board[j][i] == '|') {
						count++;
						height = '|';
					}else continue;
				}else {
					if (board[j][i] == '|' ) continue;
					else height = 'h';
				}
			}
			height = 'h';
		}
		
		System.out.println(count);
		
	}
}
