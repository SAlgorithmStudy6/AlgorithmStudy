import java.util.*;

public class Solution3 {
	static List<List<Road>> roadList;
	static Integer dist[];
	static Integer dist2[];
	static int N;

	static class Road implements Comparable<Road> {
		int roadNum;
		int time;

		public Road(int roadNum, int time) {
			this.roadNum = roadNum;
			this.time = time;
		}

		@Override
		public int compareTo(Road o) {
			return time - o.time;
		}
	} // End of Road

	public static void main(String[] args) {
		Solution3 s = new Solution3();

		int n = 5;
		int roads[][] = { { 1, 2 }, { 1, 4 }, { 2, 4 }, { 2, 5 }, { 4, 5 } };
		int sources[] = { 1, 3, 5 };
		int destination = 5;
		System.out.println(Arrays.toString(s.solution(n, roads, sources, destination)));
	} // End of main

	public int[] solution(int n, int[][] roads, int[] sources, int destination) {
		int answer[] = new int[sources.length];
		N = n;
		roadList = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			roadList.add(new ArrayList<>());
		}

		int len = roads.length;
		for (int i = 0; i < len; i++) {
			int parent = roads[i][0];
			int son = roads[i][1];

			roadList.get(parent).add(new Road(son, 1));
			roadList.get(son).add(new Road(parent, 1));
		}

		// sources배열에서 destination까지 갈 수 있는 최단 거리 구하기 (다익스트라)
		int j = 0;
		for (int num : sources) {

			dist = new Integer[N + 1];
			answer[j] = dijkstra(num, destination);
			j++;
		}

		return answer;
	} // End of solution

	private static int dijkstra(int startNum, int destination) {
		PriorityQueue<Road> pque = new PriorityQueue<Road>();
		boolean visit[] = new boolean[N + 1];
		dist[startNum] = 0;
		pque.offer(new Road(startNum, 0));

		while (!pque.isEmpty()) {
			Road r = pque.poll();
			int rnum = r.roadNum;

			if(rnum == destination) break;
			if (!visit[rnum]) {
				visit[rnum] = true;

				for (Road road : roadList.get(rnum)) {
					if (!visit[road.roadNum] && dist[road.roadNum] == null
							|| (dist[road.roadNum] > (dist[rnum] + road.time))) {
						dist[road.roadNum] = (dist[r.roadNum] + road.time);
						pque.offer(new Road(road.roadNum, dist[road.roadNum]));
					}
				}
			}
		}

		if (dist[destination] == null)
			return -1;

		return dist[destination];
	} // End of dijkstra
} // End of Solution class