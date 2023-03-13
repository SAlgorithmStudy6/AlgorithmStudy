import java.util.*;
import java.io.*;
public class 미생물격리_2_2382 {
	static class Microbe {
		int x, y;
		int power;
		int dir;

		public Microbe(int x, int y, int power, int dir) {
			this.x = x;
			this.y = y;
			this.power = power;
			this.dir = dir;
		}
	}

	static int N, M, K;
	static int[][] map;
	static List<Microbe> microbes = new LinkedList<>();
	// (상: 1, 하: 2, 좌: 3, 우: 4)
	static int[][] dir = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			microbes.clear();

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				microbes.add(new Microbe(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			for (int day = 0; day < M; day++) {
				int lIdx = 0;
				List<Microbe> lists = new ArrayList<>();
				map = new int[N][N];
				for (int i = 0; i < N; i++) {
					Arrays.fill(map[i], -1);
				}

				//군집 순으로 list를 내림차순 정렬
				microbes.sort(new Comparator<Microbe>() {
					public int compare(Microbe m1, Microbe m2) {
						return m2.power - m1.power;
					}
				});
				
				for (int mIdx = 0; mIdx < microbes.size(); mIdx++) {
					Microbe m = microbes.get(mIdx);
					int curDir = m.dir;
					int nextX = m.x + dir[m.dir][0];
					int nextY = m.y + dir[m.dir][1];
					
					m.x = nextX;
					m.y = nextY;
					
					if (isEdge(nextX, nextY)) { // power절반, 방향 반대
						m.power/=2;
						m.dir = getReversedDir(curDir);
					}
					
					if (map[nextX][nextY] == -1) {
						lists.add(m);
						map[nextX][nextY] = lIdx++;
					} else {
						lists.get(map[nextX][nextY]).power += m.power;
					}
				}
				
				microbes.clear();
				microbes.addAll(lists);
			}
			
			System.out.println("#" + test_case + " "+microbes.stream().mapToInt(m -> m.power).sum());
		}
	}

	// (상: 1, 하: 2, 좌: 3, 우: 4)
	private static int getReversedDir(int curDir) {
		if (curDir%2 == 0)
			return curDir - 1;
		else 
			return curDir + 1;
	}

	private static boolean isEdge(int nextX, int nextY) {
		if (nextX == 0 || nextX == N - 1 || nextY == 0 || nextY == N - 1)
			return true;
		else
			return false;
	}

}

