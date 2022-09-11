import java.util.*;
import java.io.*;
public class 벌꿀채취_2115 {

	static int N, M, C;
	static int[][] arr;
	static int[] aworker, bworker;
	static boolean[][] vis;
	static boolean[] avis;
	static boolean[] bvis;
	static int amax, bmax, ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=t; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			arr = new int[N][N];
			aworker = new int[M];
			bworker = new int[M];
			ans = Integer.MIN_VALUE;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
		
			for(int i=0; i<N; i++) {
				for(int j=0; j<=N-M; j++) {
					int index = 0;
					vis = new boolean[N][N];
					for(int p=j; p<j+M; p++) {	//a일꾼 벌꿀 담아주고
						aworker[index] = arr[i][p];
						vis[i][p] = true;
						index++;
					}
					selectbwoker(i+1,j+M);
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}
	
	//b일꾼 벌꿀 줌
	static void selectbwoker(int i, int j) {
		for(int k=i; k<N; k++) {
			for(int h=0; h<=N-M; h++) {
				if(vis[k][h] == true) continue;
				int index = 0;
				for(int p=h; p<h+M; p++) {
					if(index == M) break;
					bworker[index] = arr[k][p];
					index++;
				}
				avis = new boolean[M];
				acal(0);
			}
		}
	}
	
	static void acal(int index) {
		if(index == M) {
			amax = 0;
			for(int i=0; i<M; i++) {
				if(avis[i] == true) {
					amax += aworker[i];
				}
			}
			if(amax <= C) {
				amax = 0;
				for(int i=0; i<M; i++) {
					if(avis[i] == true) {
						amax += aworker[i]*aworker[i];
					}
				}
				bvis = new boolean[M];
				bcal(0);
			}
			return;
		}
		avis[index] = true;
		acal(index+1);
		avis[index] = false;
		acal(index+1);
	}
	
	static void bcal(int index) {
		if(index == M) {
			bmax = 0;
			for(int i=0; i<M; i++) {
				if(bvis[i] == true) bmax += bworker[i];
			}
			if(bmax <= C) {

				bmax = 0;
				for(int i=0; i<M; i++) {
					if(bvis[i] == true) {
						bmax += bworker[i]*bworker[i];
					}
				}
				ans = Math.max(ans, amax+bmax);
			}
			return;
		}
		bvis[index] = true;
		bcal(index+1);
		bvis[index] = false;
		bcal(index+1);
	}
}
