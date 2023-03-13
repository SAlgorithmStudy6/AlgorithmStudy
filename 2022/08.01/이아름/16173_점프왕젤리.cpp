#include <iostream>
#include <vector>
#include <queue>
using namespace std;
int main() {
	int toX[] = { 0,1 };
	int toY[] = { 1,0 };
	int N; cin >> N;
	vector<vector<int>> board;
	for (int i = 0; i < N; i++) {
		vector<int> list;
		for (int k = 0; k < N; k++) {
			int n; cin >> n;
			list.push_back(n);
		}
		board.push_back(list);
	}
	queue<pair<int, int>> q;
	bool visit[5][5] = { false }; //왜 visit를 찍어야하지? - 이미 지나가봤던 실패케이스는 가지 않기위해
	q.push({ 0,0 });
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		if (board[x][y] == -1) {
			cout << "HaruHaru" << endl;
			return 0;
		}
		for (int i = 0; i < 2; i++) {
			int xx = x + (toX[i] * board[x][y]);
			int yy = y + (toY[i] * board[x][y]);
			if (xx < N && yy < N && !visit[xx][yy]) {
				q.push({ xx,yy });
				visit[xx][yy] = true;
			}
		}
	}
	cout << "Hing" << endl;
	return 0;
}
