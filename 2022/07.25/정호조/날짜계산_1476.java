package problum1476;

import java.util.*;

public class 날짜계산_1476 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int e = sc.nextInt();
		int s = sc.nextInt();
		int m = sc.nextInt();
		int re = 0;
		int rs = 0;
		int rm = 0;
		int ans = 0;
		
		while(true) {
			re++;
			rs++;
			rm++;
			ans++;
			if(re == 16) re = 1;
			if(rs == 29) rs = 1;
			if(rm == 20) rm = 1;
			if((e == re) && (s == rs) && (m == rm)) break;
		}
		System.out.println(ans);
	}
}
