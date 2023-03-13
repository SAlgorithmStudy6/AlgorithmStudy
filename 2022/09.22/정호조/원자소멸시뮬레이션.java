import java.util.*;
import java.io.*;

public class 원자소멸시뮬레이션_5648 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] map;
	static ArrayList<Atom> atom;

	static int ans;		

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		atom = new ArrayList<>();
		map = new int[4001][4001];	// map을 testcase마다 초기화해주면 runtime error발생, 어차피 원자 소멸할 때 값을 0으로 바꿔주므로 초기화는 한 번만 진행

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			ans = 0;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int c = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int r = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int d = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				if(e == 0) continue;
				atom.add(new Atom(r, c, d, e, 0));
				map[4000-r][c]++;
			}

			while (atom.size() > 1) {
				// 이동시키기
				for (int i = 0; i < atom.size(); i++) {
					int r = atom.get(i).r;
					int c = atom.get(i).c;
					int dir = atom.get(i).d;
					int e = atom.get(i).e;
					int status = atom.get(i).status;
					map[4000-r][c] =0;
					atom.set(i, new Atom(r + dy[dir], c + dx[dir], dir, e, status));

					// 범위 밖으로 나간 원자 삭제
					if (atom.get(i).c < 0 || atom.get(i).c > 4000 || atom.get(i).r < 0 || atom.get(i).r > 4000) {
						atom.remove(i--);	
					} else {
						map[4000-atom.get(i).r][atom.get(i).c]++;
					}
				}

				// 겹치는 부분 찾아서 삭제 대상으로 변경해주기
				for (int i = 0; i < atom.size(); i++) {
					if (map[4000-atom.get(i).r][atom.get(i).c] > 1) {
						atom.set(i, new Atom(atom.get(i).r, atom.get(i).c, atom.get(i).d, atom.get(i).e, 1));
					}
				}

				// 삭제
				for (int i = 0; i < atom.size(); i++) {
					// 충돌 일어난 원자들 삭제
					if (atom.get(i).status == 1) {
						ans += atom.get(i).e;
						map[4000-atom.get(i).r][atom.get(i).c] = 0;
						atom.remove(i--);
					}
				}
			}
			
			System.out.printf("#%d %d\n", tc, ans);
			//하나 남았을 때 map초기화 해줌
			if(atom.size() == 1) {
				map[4000-atom.get(0).r][atom.get(0).c] = 0;
			}
			atom.clear();
		}
	}

	public static class Atom {
		int c, r, d, e, status;

		public Atom(int r, int c, int d, int e, int status) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.e = e;
			this.status = status; // 이동시켰을 때 겹치면 1
		}
	}
}
