import java.io.*;

public class Main_1543_문서검색 {
	static 	StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_1543.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// String은 immutable(불변성)의 성질을 가지고 있어서 문자열 연산에는 효율적이지 않다.
		// 그 이유는 문자열 연산은 appned, delete하는 작업에 있어서 StringBuilder가 헐씬 효율적임
		sb.append(br.readLine());
		String find = br.readLine();
		
		search(find);
		
	} // End of main
	
	static void search(String find) {
		
		int count = 0;
		int startIndex = 0;
		int find_len = find.length();
				
		// find와 똑같은 문자열을 StringBuilder에서 찾아서 해당 index값을 가져옴
		// 이후에 StringBuilder에서 delete작업을 하면됨.
		// 그리고 count값 증가를 하면 답을 구할 수 있음
		while((startIndex = sb.toString().indexOf(find)) != -1) {
			sb.delete(0, startIndex + find_len);
			count++;
		}
		
		System.out.println(count);
	}
	
} // End of class