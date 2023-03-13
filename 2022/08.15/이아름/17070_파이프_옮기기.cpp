#include <iostream>
#include <queue>
#include <vector>
using namespace std;
int main() {
	int N; cin >> N;
	vector<vector<int>> board;
	vector<vector<int>> count;
	for (int i = 0; i < N; i++) {
		vector<int> tmp;
		for (int k = 0; k < N; k++) {
			int n; cin >> n;
			tmp.push_back(n);
		}
		board.push_back(tmp);
		vector<int> tmp1(N, 0);
		count.push_back(tmp1);
	}
	if (board[N - 1][N - 1] == 1) {
		cout << 0 << endl;
		return 0;
	}
	queue<vector<int>> q; //x1,y1,x2,y2
	int dx[] = { 0,1,1 };
	int dy[] = { 1,0,1 };
	q.push({ 0,0, 0,1 });
	count[0][1] = 1;
	while (!q.empty()) {
		vector<int> now = q.front();
		q.pop();
		if (now[1] != now[3] && now[3] < N - 1 && board[now[2]][now[3] + 1] == 0) { //가로
			q.push({ now[2],now[3], now[2],now[3] + 1 });
			count[now[2]][now[3] + 1]++; //+= count[now[2]][now[3]];
		}
		if (now[0] != now[2] && now[2] < N - 1 && board[now[2] + 1][now[3]] == 0) { //세로
			q.push({ now[2],now[3], now[2] + 1, now[3] });
			count[now[2] + 1][now[3]] ++;
		}
		if (now[2] < N - 1 && now[3] < N - 1) { //대각선
			bool flag = true;
			for (int i = 0; i < 3; i++) {
				if (board[now[2] + dx[i]][now[3] + dy[i]] != 0) {
					flag = false;
				}
			}
			if (flag) {
				q.push({ now[2],now[3], now[2] + 1,now[3] + 1 });
				count[now[2] + 1][now[3] + 1] ++;
			}
		}
	}
	cout << count[N - 1][N - 1] << endl;
	return 0;
}
