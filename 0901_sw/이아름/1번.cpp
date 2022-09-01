#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <limits.h>
using namespace std;

int N;
vector<vector<int>> house;

int dfs(vector<int> &visited, int x, int y) {
	int minCount = INT_MAX;
	for (int i = -15; i <= 15; i++) {
		for (int k = -15; k <= 15; k++) {
			if (i == x && k == y) continue;
			bool flag = true;
			vector<int> distance(visited.begin(), visited.end());
			for (int j = 0; j < N; j++) {
				int dif = abs(i - house[j][0]) + abs(k - house[j][1]);
				if (dif == 0) { //집이 있을 때
					flag = false;
					break;
				}
				if (dif <= house[j][2]) {
					distance[j] = min(distance[j], dif);
				}
			}
			if (flag) {
				bool isAll = true;
				int count = 0;
				for (int j = 0; j < N; j++) {
					if (distance[j] == INT_MAX) {
						isAll = false;
						break;
					}
					count += distance[j];
				}
				if (isAll) {
					minCount = min(minCount, count);
				}
			}
		}
	}
	return minCount;
}

int main() {
	int T; cin >> T;
	for (int t = 1; t <= T; t++) {
		cin >> N;
		house.clear();
		for (int i = 0; i < N; i++) {
			vector<int> tmp;
			for (int k = 0; k < 3; k++) {
				int n; cin >> n;
				tmp.push_back(n);
			}
			house.push_back(tmp);
		}
		int minCount = INT_MAX;
		int minCount2 = INT_MAX;
		int minIndex = 3;
		for (int i = -15; i <= 15; i++) {
			for (int k = -15; k <= 15; k++) {
				bool flag = true;
				int count = 0; //방문 한 개수
				vector<int> distance(N, INT_MAX);
				for (int j = 0; j < N; j++) {
					int dif = abs(i - house[j][0]) + abs(k - house[j][1]);
					if (dif == 0) { //집이 있을 때
						flag = false;
						break;
					}
					if (dif <= house[j][2]) {
						distance[j] = dif;
						count += 1;
					}
				}
				if (flag) {
					if (count == N) {
						int sum = 0;
						for (int j = 0; j < N; j++) {
							sum += distance[j];
						}
						minCount = min(minCount, sum);
						minIndex = 1;
					}
					else if (count > 0 && minIndex > 1) {
						int nowCount = dfs(distance, i, k);
						if (nowCount < minCount2) {
							minIndex = 2;
							minCount2 = nowCount;
						}
					}
				}
			}
		}
		if (minIndex == 3) minCount = -1;
		if (minIndex == 2) minCount = minCount2;
		cout << "#" << t << " " << minCount << endl;
	}
	return 0;
}
