package baekjoon;

import java.util.*;
import java.io.*;
public class A와B_12904 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		
		int ans = 0;
		
		while(t.length() > s.length()) {
			StringBuilder sb = new StringBuilder();

			if(t.equals(s)) {
				ans = 1;
				break;
			}
			if(t.charAt(t.length()-1) == 'A') {
				t = t.substring(0, t.length()-1);
			} else if(t.charAt(t.length()-1) == 'B') {
				t = t.substring(0,t.length()-1);
				t = sb.append(t).reverse().toString();
			}
		}
		
		System.out.println(ans);
	}
}
