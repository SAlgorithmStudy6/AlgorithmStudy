import java.io.*;
import java.util.*;

public class Main {
	public static boolean bad[][];
	public static int combination[];
	
	public static boolean visited[];
	public static int ic_arr[];
	
	public static int count = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(token.nextToken());
		int M = Integer.parseInt(token.nextToken());
		
		bad = new boolean[N+1][N+1]; //���� �ȵǴ� ����
		combination = new int[3]; //���̽�ũ�� ����
		
		visited = new boolean[N+1];
		ic_arr = new int[N+1];
		
		for (int i = 1; i <= N ; i++) {
			ic_arr[i-1] = i;
		}
		
		for (int i = 0; i < M; i++) {
			token = new StringTokenizer(br.readLine());
			int ic = Integer.parseInt(token.nextToken());
			int ic2 = Integer.parseInt(token.nextToken());
			
			bad[ic][ic2] = true;
			bad[ic2][ic] = true;
		}
		comb(0,0,N);
		System.out.println(count);
	}
	
	public static void comb(int index, int size,int length) {
		if (size == 3) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					//������ ������ ���ԵǾ������� pass
					if (bad[combination[i]][combination[j]]) {
						return;
					}
				}
			}
			count++;
			return;
		}
		
		for (int i = index; i < length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination[size] = ic_arr[i];
				comb(i+1,size+1,length);
				visited[i] = false;
			}
		}
	}
}
