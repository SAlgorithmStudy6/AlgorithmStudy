import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String document = br.readLine(); //����
		String word = br.readLine(); //�ܾ�
		
		int doc_idx = 0; //���� �ε���
		int word_idx = 0; //�ܾ� �ε���
		int last_idx = 0; //��Ұ� ��ġ���� ���� �� �����ϴ� �ε���
		
		int count = 0; //�ܾ� ����
		
		while(true) {
			
			if (doc_idx == document.length()) break; //�ε��� ������ �ݺ��� ����
			
			//��Ұ� ���� ��
	        if (document.charAt(doc_idx) == word.charAt(word_idx)) {
	        	//�ܾ� �ε����� ������ �ε��� �� ��
	            if (word_idx == word.length() - 1) {
	                word_idx = 0;
	                doc_idx++;
	                last_idx = doc_idx;
	                count++;
	            } else {
	                doc_idx++;
	                word_idx++;
	            }
	        } else { //��Ұ� ���� ���� ��
	            last_idx++;
	            doc_idx = last_idx;
	            word_idx = 0;
	        }
		}
		System.out.println(count);
	}
}
