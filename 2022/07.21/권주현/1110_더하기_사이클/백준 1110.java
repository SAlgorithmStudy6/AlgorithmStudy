import java.util.Arrays;
import java.util.Scanner;

public class 백준1110 {
	static char[] arr;
	static int cnt=0;
	static String str;
	static char n1,n2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner (System.in);
		str=scan.next();
		arr=new char[2];
		Arrays.fill(arr,'0');
		for(int i=0;i<str.length();i++) {
			arr[i]=str.charAt(i);
		}
		n1=arr[0];
		n2=arr[1];

			Arrays.fill(arr,'0');
			int sum=(n1-'0')+(n2-'0');
			int second=sum%10;		//일의 자리 수
			arr[0]=n2;
			arr[1]=(char)(second+'0');
			cnt++;
			System.out.println(sol(arr));
		
	}
	static int sol(char[] arr) {
		char s1=arr[0];
		char s2=arr[1];
		if(s1==n1&&s2==n2) return cnt;
		
		
			Arrays.fill(arr,'0');
			int sum=(s1-'0')+(s2-'0');
			int second=sum%10;		//일의 자리 수
			arr[0]=s2;
			arr[1]=(char)(second+'0');
			cnt++;
			sol(arr);
		
		return cnt;
	}

}
