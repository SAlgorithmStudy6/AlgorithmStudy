#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int maxScore = 0;
int contact[] = { -1,-1,-1 };
int N, M, D;
vector<vector<int>> board(16);
vector<bool> visited(16,false);

int dx[] = { 0,-1 ,0 };
int dy[] = { -1,0 ,1 };

int makeScore() {
	int score = 0;
	vector<vector<int>> cboard(board.begin(), board.end());
	for (int i = 0; i < N; i++) {
		vector<pair<int, int>> remove;
		for (int k = 0; k < 3; k++) {
			queue<vector<int>> q; //x,y,count;
			vector<vector<bool>>check(N+1,vector<bool>(M,false));
			q.push({ N - i, contact[k], 0 });
			check[N - i][contact[k]] = true;
			while (!q.empty()) {
				vector<int> now = q.front(); q.pop();
				if (cboard[now[0]][now[1]] == 1) {
					remove.push_back({ now[0],now[1] });
					break;
				}
				if (now[2] == D) continue;
				for (int t = 0; t < 3; t++) {
					int xx = now[0] + dx[t];
					int yy = now[1] + dy[t];
					if (0 <= xx && xx <= N && 0 <= yy && yy < M) {
						if (!check[xx][yy]) {
							check[xx][yy] = true;
							q.push({ xx,yy,now[2] + 1 });
						}
					}
				}
			}
		}
		for (auto r : remove) {
			if (cboard[r.first][r.second] == 1) {
				cboard[r.first][r.second] = 0;
				score++;
			}
		}
		for (int t = 0; t < M; t++) {
			if (cboard[N - i - 1][t] == 1) {
				cboard[N - i - 1][t] = 0;
			}
		}
	}
	return score;
}

void dfs(int index) {
	if (index == 3) {
		int score = makeScore();
		maxScore = max(maxScore, score);
		return;
	}
	for (int i = 0; i < M; i++) {
		if (!visited[i]) {
			contact[index] = i;
			visited[i] = true;
			dfs(index + 1);
			visited[i] = false;
			contact[index] = -1;
		}
	}
}

int main() {
	cin >> N >> M >> D;
	for (int i = 0; i < N; i++) {
		for (int k = 0; k < M; k++) {
			int input; cin >> input;
			board[i].push_back(input);
		}
	}
	for (int k = 0; k < M; k++) {
		board[N].push_back(0);
	}
	dfs(0);
	cout << maxScore << endl;
	return 0;
}
