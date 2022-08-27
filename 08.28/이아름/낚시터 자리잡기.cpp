#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>

using namespace std;
int minMoved, N;
vector<vector<int>> door;
vector<bool> visited;

void getWent(vector<bool>& fishing, int moved,int count) {
	if (moved >= minMoved || count == 3) {
		minMoved = min(minMoved, moved);
		return;
	}
	for (int i = 0; i < 3; i++) {
		if (!visited[i]) {
			visited[i] = true;
			vector<bool> copy_fishing(fishing.begin(), fishing.end());
			int man = door[i][1];
			int nowMoved = moved;
			while (man) {
				int left = INT_MAX, right = INT_MAX;
				for (int k = 0; k <= N; k++) {
					if (left == INT_MAX && door[i][0] - k > 0) {
						if (!copy_fishing[door[i][0] - k]) {
							left = k;
						}
					}
					if (right == INT_MAX && door[i][0] + k <= N) {
						if (!copy_fishing[door[i][0] + k]) {
							right = k;
						}
					}
					if (left < INT_MAX || right < INT_MAX) break;
				}
				if (left == right && man == 1) {
					//left
					copy_fishing[door[i][0] - left] = true;
					nowMoved += left + 1;
					getWent(copy_fishing, nowMoved,count+1);
					nowMoved -= left + 1;
					copy_fishing[door[i][0] - left] = false;
					//right
					copy_fishing[door[i][0] + right] = true;
					nowMoved += right + 1;
				}
				else if(left < INT_MAX){
					copy_fishing[door[i][0] - left] = true;
					nowMoved += left+1;
				}else{
					copy_fishing[door[i][0] + right] = true;
					nowMoved += right + 1;
				}
				man--;
			}
			getWent(copy_fishing, nowMoved, count + 1);
			visited[i] = false;
		}
	}
}

void init() {
	minMoved = INT_MAX;
	door.clear();
	visited = { false,false,false };
}

int main() {
	int T; cin >> T;
	for (int t = 1; t <= T; t++) {
		init();
		cin >> N;
		for (int i = 0; i < 3; i++) {
			int fish, man;
			cin >> fish >> man;
			door.push_back({ fish,man });
		}
		vector<bool> fishing(N+1, 0);
		getWent(fishing, 0,0);
		cout << "#" << t << " " << minMoved << endl;
	}
	return 0;
}
