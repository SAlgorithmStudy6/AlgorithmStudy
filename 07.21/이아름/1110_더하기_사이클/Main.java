import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //입력받기 위한 BufferReader
		int N = Integer.parseInt(br.readLine()); //입력받은 수 N
		int count = 0; //사이클 길이를 저장할 변수
		int nextInt = N; //다음 사이클에 나오는 새로운 수
		do{
			count++; //사이클 길이 증가
			int ten = nextInt/10; //10의 자리수
			int one = nextInt%10; //1의 자리수
			int sumNum = ten+one; //각 자리의 합
			nextInt = one*10 + sumNum%10; //새로운 수 구하기
		}while(N != nextInt); //N과 같아질 때까지 반복
		System.out.println(count);
	}

}
