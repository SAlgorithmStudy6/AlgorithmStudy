package etc;

import java.util.Scanner;

public class 백준1543 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);
		String str=scan.nextLine();
		String search=scan.nextLine();
		int tmp_cnt=0;
		int ans_cnt=0;
		int search_leng=search.length();
		int str_leng=str.length();
		for(int i=0;i<str_leng;i++) {
			tmp_cnt=0;
			if(str_leng-i<search_leng) {	//남은 글자수가 search_leng보다 작을 때
				break;
			}
			
			if(str.charAt(i)==search.charAt(0)) {	//첫글자가 같으면
				int j;
				for(j=i;j<i+search_leng;j++) {		//search_leng만큼 반복문 돌기
					if(str.charAt(j)==search.charAt(j-i)) {
						tmp_cnt++;					//글자수 모두 일치하는지 count
					}
				}
				if(tmp_cnt==search.length()) {	
					ans_cnt++;
					i=j-1;
				}
			}
		}
		System.out.println(ans_cnt);
	}

}
