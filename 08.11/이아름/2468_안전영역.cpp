#include <iostream>
#include <queue>
#include <vector>
using namespace std;
int main() {
	int N; cin >> N;
	vector<vector<int>> board;
	int minest=200, maxest=0;
	for (int i = 0; i < N; i++) {
		vector<int> list;
		for (int k = 0; k < N; k++) {
			int num; cin >> num;
			minest = min(minest, num);
			maxest = max(maxest, num);
			list.push_back(num);
		}
		board.push_back(list);
	}
	int answer = 1;
	int x[] = { 0,1,0,-1 };
	int y[] = { 1,0,-1,0 };
	for (int i = minest; i < maxest; i++) {
		int count = 0;
		bool visited[101][101] = { false };
		for (int k = 0; k < N; k++) {
			for (int j = 0; j < N; j++) {
				if (board[k][j] > i && !visited[k][j]) {
					count++;
					queue<pair<int, int>> q;
					q.push({ k,j });
					visited[k][j] = true;
					while (!q.empty()) {
						pair<int, int> now = q.front();
						q.pop();
						for (int t = 0; t < 4; t++) {
							int xx = now.first + x[t];
							int yy = now.second + y[t];
							if (0 <= xx && xx < N && 0 <= yy && yy < N ) {
								if (board[xx][yy] > i && !visited[xx][yy]) {
									visited[xx][yy] = true;
									q.push({ xx,yy });
								}
							}
						}

					}
				}

			}
		}
		answer = max(answer, count);
	}
	cout << answer << endl;
	return 0;
}
