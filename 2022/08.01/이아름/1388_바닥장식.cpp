#include <iostream>
#include <vector>
#include <string>
#include <queue>
using namespace std;
int main() {
	int N, M; cin >> N >> M;
	vector<string> board;
	for (int i = 0; i < N; i++) {
		string str; cin >> str;
		board.push_back(str);
	}
	bool visit[51][51] = { false };
	int count = N * M;
	//if _ -> +(0,1) -> _이면 전체 count -1 push
	// | -> (+1,0) | 이면 전체 count-1 push
	queue<pair<int, int>> q;
	for (int i = 0; i < N; i++) {
		for (int k = 0; k < M; k++) {
			if (!visit[i][k]) {
				visit[i][k] = true;
				q.push({ i,k });
			}
			while (!q.empty()) {
				int x = q.front().first, y= q.front().second;
				int xx, yy;
				q.pop();
				if (board[x][y] == '-') {
					xx = x; yy = y + 1;
				}
				else {
					xx = x + 1; yy = y;
				}
				if (xx < N && yy < M && board[x][y] == board[xx][yy]) {
					visit[xx][yy] = true;
					count--;
					q.push({ xx,yy });
				}
			}
		}
	}
	cout << count << endl;
}
