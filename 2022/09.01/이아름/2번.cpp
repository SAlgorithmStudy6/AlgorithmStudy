#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>
using namespace std;

int N;
vector<vector<int>> board;
vector<pair<int, int>> core;
int maxIndex, minCount;
int dx[] = { 0,1,0,-1 };
int dy[] = { 1,0,-1,0 };

void getCore(vector<vector<int>> &v, int index, int count, int coreCount) {
	if (coreCount > maxIndex) {
		maxIndex = coreCount;
		minCount = count;
	}
	if (coreCount == maxIndex && minCount > count) {
		minCount = count;
	}
	if (index == core.size()) return;
	bool isNone = true;
	for (int i = 0; i < 4; i++) {
		int len = 0;
		int x = core[index].first;
		int y = core[index].second;
		vector<vector<int>> copy;
		for (int i = 0; i < N; i++) {
			copy.push_back(v[i]);
		}
		bool cross = false;
		while (true) {
			x += dx[i];
			y += dy[i];
			if (0 <= x && x < N && 0 <= y && y < N) {
				if (copy[x][y] > 0) {
					cross = true;
					break;
				}
				copy[x][y] = 1;
				len++;
			}
			else {
				break;
			}
		}
		if (cross) continue;
		isNone = false;
		getCore(copy, index + 1, count + len,coreCount+1);
		if (len == 0) break;
	}
	if (isNone) {
		vector<vector<int>> copy;
		for (int i = 0; i < N; i++) {
			copy.push_back(v[i]);
		}
		getCore(copy, index + 1, count, coreCount);
	}
}

void init() {
	board.clear(); core.clear();
	maxIndex = 0; minCount = INT_MAX;
}

int main()
{
	int  T; cin >> T;
	for (int t = 1; t <= T; t++) {
		init();
		cin >> N;
		for (int i = 0; i < N; i++) {
			vector<int> tmp;
			for (int k = 0; k < N; k++) {
				int n; cin >> n;
				tmp.push_back(n);
				if (n == 1) {
					core.push_back({ i,k });
				}
			}
			board.push_back(tmp);
		}
		getCore(board, 0, 0,0);
		cout << "#" << t << " " << minCount << endl;
	}
	return 0;
}
