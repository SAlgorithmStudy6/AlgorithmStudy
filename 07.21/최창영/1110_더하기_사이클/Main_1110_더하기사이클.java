import java.io.*;

public class Main_1110_더하기사이클 {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/1110.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int temp = num;

		int result = 0;
		for (;;) {
			int firstNum = temp / 10;
			int secondNum = temp % 10;
			
			temp = secondNum * 10 + (firstNum + secondNum) % 10;
			result++;
			if(num == temp) break;
		}

		System.out.print(result);
	} // End of main
} // End of Main class