import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int count = 0;
		int number = N;

		while (true) {
			int left = number / 10;
			int right = number % 10;

			if (number < 10) {
				number *= 11;
				count++;
			} else {
				number = (10 * right) + (left + right) % 10;
				count++;
			}
			if (number == N)
				break;
		}
		
		bw.write(String.valueOf(count));

	    bw.flush();
	    bw.close();

	}

}
