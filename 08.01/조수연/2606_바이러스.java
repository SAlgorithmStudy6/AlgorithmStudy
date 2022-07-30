import java.io.*;
import java.util.*;

public class Main {

	static int computer;
	static int pair;
	static boolean[] infected = new boolean[101]; //감염되었다면 true
	static Queue<Integer> last = new LinkedList<Integer>(); 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		computer = Integer.parseInt(br.readLine());
		pair = Integer.parseInt(br.readLine());
		int[][] pairs = new int[pair][2];
		
		infected[0] = true;
		infected[1] = true;
		
		last.add(1); //1부터 시작
		
		
		for (int i = 0; i < pair; i++) { //감염된 컴퓨터 쌍
			StringTokenizer token = new StringTokenizer(br.readLine());
			pairs[i][0] = Integer.parseInt(token.nextToken());;
			pairs[i][1] = Integer.parseInt(token.nextToken());
		}
		
		while(true) {
			if (last.isEmpty()) break;
			
			int last_index = last.poll();
			
			for (int i = 0; i < pairs.length; i++) { //마지막으로 감염된 컴퓨터가 쌍에 있으면 last에 추가 이미 감염되었으면 continue
				
				if (pairs[i][0] == last_index) {
					if (infected[pairs[i][1]] == false) {
						infected[pairs[i][1]] = true;
						last.add(pairs[i][1]);
					}
				}else if(pairs[i][1] == last_index) {
					if (infected[pairs[i][0]] == false) {
						infected[pairs[i][0]] = true;
						last.add(pairs[i][0]);
					}
				}
			}
		}
		
		int count = 0;
		
		
		for (int i = 2; i < infected.length; i++) { //감염된 컴퓨터 찾기
			if (infected[i]) {
				count++;
			}
		}
		
		System.out.println(count);
	}
}
