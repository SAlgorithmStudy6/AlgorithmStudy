import java.util.*;
import java.io.*;

public class Main_5052_전화번호목록 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_5052.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		while(T --> 0) {
			int N = Integer.parseInt(br.readLine());
			HashMap<String, Integer> map = new HashMap<>();
			String number_list[] = new String[N];
			
			for(int i=0; i<N; i++) {
				String str = br.readLine();
				map.put(str, 1);
				number_list[i] = str;
			}
			
			Arrays.sort(number_list);
			boolean bol = solution(N, number_list, map);
			
			if(bol == false) {
				sb.append("NO\n");
			}
			else {
				sb.append("YES\n");
			}
			
		}
		
		System.out.println(sb);
	} // End of main
	
	static boolean solution(int length, String[] number_list, HashMap<String, Integer> map) {
		
		for(int i=0; i<length; i++) {
			for(int j=1; j<number_list[i].length(); j++) {
				if(map.containsKey(number_list[i].substring(0, j))) {
					return false;
				}
			}
		}
		
		return true;
	} // End of solution
	
} // End of class