import java.util.*;

class Pair {
	long x;
	long y;

	Pair(long x, long y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static long A, B, cnt;
	static ArrayList<ArrayList<Long>> a = new ArrayList<>();

	public static void main(String[] args) {
		// 16953
		Scanner sc = new Scanner(System.in);

		A = sc.nextInt();
		B = sc.nextInt();

		bfs();
		if (cnt == -1) {
			System.out.println(-1);
		} else {
			System.out.println(cnt + 1);
		}
	}

	static void bfs() {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(A, 0));

		cnt = 0;

		while (!q.isEmpty()) {
			Pair p = q.poll();

			if (p.x == B) {
				cnt = p.y;
				return;
			}
			if (p.x * 2 <= B) {
				q.add(new Pair(p.x * 2, p.y + 1));
			}
			if ((p.x * 10 + 1) <= B) {
				q.add(new Pair(p.x * 10 + 1, p.y + 1));
			}
		}
		cnt = -1;

	}

}
