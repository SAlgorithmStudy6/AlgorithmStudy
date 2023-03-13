#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

bool visit[101][101] = { false };

int main() {
	int M, N, K; cin >> M >> N >> K;
	for (int i = 0; i < K; i++) {
		int x1, y1,x2, y2; 
		cin >> x1 >> y1 >> x2 >> y2;
		for (int k = x1; k < x2;k++) {
			for (int t = y1; t < y2; t++) {
				visit[k][t] = true;
			}
		}
	}
	int tox[] = {0,1,0,-1};
	int toy[] = { 1,0,-1,0 };
	queue<pair<int, int>> q;
	vector<int> answer;
	for (int i = 0; i < N; i++) {
		for (int k = 0; k < M; k++) {
			if (!visit[i][k]) {
				int count = 1;
				q.push({ i,k });
				visit[i][k] = true;
				while (!q.empty()) {
					int x = q.front().first;
					int y = q.front().second;
					q.pop();
					for (int j = 0; j < 4; j++) {
						int nx = x + tox[j];
						int ny = y + toy[j];
						if (0 <= nx && nx < N && 0 <= ny && ny < M && !visit[nx][ny]) {
							count++;
							q.push({ nx,ny });
							visit[nx][ny] = true;
						}
					}
				}
				answer.push_back(count);
			}
		}
	}
	sort(answer.begin(), answer.end());
	cout << answer.size() << endl;
	for (int n : answer) {
		cout << n << " ";
	}
	cout << endl;
}
