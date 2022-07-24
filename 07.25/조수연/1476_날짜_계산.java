import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer token = new StringTokenizer(br.readLine());

		int E = Integer.parseInt(token.nextToken());
		int S = Integer.parseInt(token.nextToken());
		int M = Integer.parseInt(token.nextToken());

		int earth = 1;
		int sun = 1;
		int moon = 1;
		int year = 1;

		while (true) {
			if (earth == E && sun == S && moon == M) break;

			earth++;
			sun++;
			moon++;
			year++;

			if (earth > 15) earth = 1;
			if (sun > 28) sun = 1;
			if (moon > 19) moon = 1;
		}
		System.out.println(year);
	}
}
