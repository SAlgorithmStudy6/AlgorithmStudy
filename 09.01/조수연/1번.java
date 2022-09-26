import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static boolean[][] map; //마을
	static int[][] house; //집 x좌표 y좌표 거리 이차원 배열
	static boolean isSupply; //보급가능 여부
	static int min; //최솟값
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine()); 
		for (int tc = 1; tc <= T; tc++) { 
			N = Integer.parseInt(br.readLine().trim()); //공백 제거 테스트케이스에 공백이 있었음.
			house = new int[N][3];
			isSupply = false;
			min = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) { 
				StringTokenizer token = new StringTokenizer(br.readLine());
				house[i][0] = Integer.parseInt(token.nextToken());
				house[i][1] = Integer.parseInt(token.nextToken());
				house[i][2] = Integer.parseInt(token.nextToken());
			}
			
			one();
			
			if (!isSupply) { //한개로 해결하지 못하였을 때
				two();
				
			}
			
			if (!isSupply) { //두개로 해결하지 못하였을 때
				min = -1;
			}
			
			bw.write("#"+String.valueOf(tc)+" "+String.valueOf(min)+"\n");
		}
		bw.flush();
		bw.close();
		
	}
	
	static void one() { //충전소를 하나 탐색
		
		for (int i = -15; i <= 15 ; i++) { // -15부터 15까지
			for (int j = -15; j <= 15; j++) {
				boolean isConnected = true; //연결되어있는지 체킹하는 변수
				int sum = 0;
				for (int k = 0; k < house.length; k++) { //
					int distance = Math.abs(j - house[k][1]) + Math.abs(i - house[k][0]); //거리 계산
					if (distance > house[k][2]) { //영역 밖이면 연결 되어있지 않음
						isConnected = false;
						break;
					}else {
						if (distance != 0) { //거리가 0인경우는 제외 왜냐하면 전기차의 좌표이므로 0이 아닌 경우에는 거리+
							
							sum += distance;
						}else { 
							isConnected = false;
							break;
						}
					}
				}
				if (isConnected) { //연결 되어있는 경우라면 min값을 최소값으로 갱신하고 isSupply를 true로 만들어서 one()에서 탐색 종료
					isSupply = true;
					min = Math.min(min,sum);
				}
			}
		}
		
	}
	
	static void two() { //2개의 충전소를 탐색
		
		for (int i = -15; i <= 15 ; i++) { //똑같이 -15부터 15까지
			for (int j = -15; j <= 15; j++) {
				for (int a = -15; a <= 15; a++) {
					for (int b = -15; b <= 15; b++) {
						if (a == i && b == j ) { //두 개의 충전소의 좌표가 같으면 안됨
							break;
						}
						else {
						
							boolean isConnected = true;
							int sum = 0;
							int count = 0;
							for (int k = 0; k < house.length; k++) {
								
								int distance1 = Math.abs(j - house[k][1]) + Math.abs(i - house[k][0]); //첫번째 충전소 거리차이
								int distance2 = Math.abs(b - house[k][1]) + Math.abs(a - house[k][0]); //두번째 충전소 거리차이
								
								if (distance1 > house[k][2] && distance2 > house[k][2]) { //두 충전소의 거리가 허용범위볻 클 경우에는 제외
									isConnected = false;
									break;
									
								}else { //둘중 거리가 허용 범위안에 들어올 때
									if (distance1 != 0 && distance2 != 0) { //거리가 0인 경우는 제외 왜냐하면 충전소의 좌표가 전기차의 좌표와 동일 아니라면 거리++ 충전소++
										sum += Math.min(distance1,distance2);
										count++;
									}
								}
							}
							
							if (isConnected && count == house.length) { //충전소가 연결되어있는 상태이고 개수가 2개일때 min값을 갱신시키고 isSupply값을 true로 반환 
								isSupply = true;
								min = Math.min(min,sum);
							}
						}
					}
				}
			}
		}
	}
}