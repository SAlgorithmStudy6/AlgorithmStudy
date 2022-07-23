import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String document = br.readLine(); //문서
		String word = br.readLine(); //단어
		
		int doc_idx = 0; //문서 인덱스
		int word_idx = 0; //단어 인덱스
		int last_idx = 0; //요소가 일치하지 않을 때 시작하는 인덱스
		
		int count = 0; //단어 갯수
		
		while(true) {
			
			if (doc_idx == document.length()) break; //인덱스 끝나면 반복문 종료
			
			//요소가 같을 때
	        if (document.charAt(doc_idx) == word.charAt(word_idx)) {
	        	//단어 인덱스가 마지막 인덱스 일 때
	            if (word_idx == word.length() - 1) {
	                word_idx = 0;
	                doc_idx++;
	                last_idx = doc_idx;
	                count++;
	            } else {
	                doc_idx++;
	                word_idx++;
	            }
	        } else { //요소가 같지 않을 때
	            last_idx++;
	            doc_idx = last_idx;
	            word_idx = 0;
	        }
		}
		System.out.println(count);
	}
}
