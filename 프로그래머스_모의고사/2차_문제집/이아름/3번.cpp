#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int> solution(int n, vector<vector<int>> roads, vector<int> sources, int destination) {
	vector<int> answer;
	vector<vector<int>> connect(n + 1);
	for (auto r : roads) {
		connect[r[0]].push_back(r[1]);
		connect[r[1]].push_back(r[0]);
	}
	vector<int> distance(n + 1, -1);
	queue<vector<int>> q; //위치, 거리
	q.push({ destination,0 });
	distance[destination] = 0;
	while (!q.empty()) {
		vector<int> now = q.front(); q.pop();
		for (int n : connect[now[0]]) {
			if (distance[n] < 0) {
				q.push({ n,now[1] + 1 });
				distance[n] = now[1] + 1;
			}
		}
	}
	for (int s : sources) {
		answer.push_back(distance[s]);
	}

	return answer;
}
