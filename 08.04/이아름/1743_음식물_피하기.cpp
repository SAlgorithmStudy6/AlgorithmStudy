#include <iostream>
#include <queue>
#include <vector>
#include <algorithm>
using namespace std;
int main() {
	int N, M, K;
	cin >> N >> M >> K;
	int board[200][200];
	vector<pair<int, int>> trash;
	for (int i = 0; i < K; i++) {
		int r, c; cin >> r >> c;
		board[r][c] = 1;
		trash.push_back({ r,c });
	}
	int max_count = 0;
	queue<pair<int,int>> q;
	bool visit[200][200] = { false };
	int y[] = { 0,1,0,-1 };
	int x[] = { 1,0,-1,0 };
	for(pair<int,int> t : trash){
		int nowX = t.first; int nowY = t.second;
		if (!visit[nowX][nowY]) {
			q.push({ nowX, nowY });
			visit[nowX][nowY] = true;
			int count = 1;
			while (!q.empty()) {
				int nx = q.front().first;
				int ny = q.front().second;
				q.pop();
				for (int i = 0; i < 4; i++) {
					int xx = nx + x[i];
					int yy = ny + y[i];
					if (0 < xx && xx <= N && 0 < yy && yy <= M) {
						if (board[xx][yy] == 1 && !visit[xx][yy]) {
							visit[xx][yy] = true;
							count++;
							q.push({ xx,yy });
						}
					}
				}
			}
			max_count = max(max_count, count);
		}
	}
	cout << max_count << endl;
	return 0;
}
