#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int N = 12, M = 6;

vector<pair<int, int>> list;
vector<string> board(12);
vector<vector<bool>> visited(12, vector<bool>(6, false));
bool check(int y, int x) {
	if (0 <= y && y < N && 0 <= x && x < M) {
		return true;
	}
	return false;
}
int dy[] = { 0,1,0,-1 };
int dx[] = { 1,0,-1,0 };

int checkCnt(int y, int x, char c, int cnt) {
	visited[y][x] = true;
	for (int i = 0; i < 4; i++) {
		int yy = y + dy[i];
		int xx = x + dx[i];
		if (check(yy, xx)) {
			if (board[yy][xx] == c && !visited[yy][xx]) {
				visited[yy][xx] = true;
				cnt = checkCnt(yy,xx,c, cnt + 1);
			}
		}
	}
	return cnt;
}

vector<vector<bool>> visited2(12, vector<bool>(6, false));
void convert(int y, int x, char c) {
	visited2[y][x] = true;
	board[y][x] = '.';
	list.push_back({ y,x });
	for (int i = 0; i < 4; i++) {
		int yy = y + dy[i];
		int xx = x + dx[i];
		if (check(yy, xx)) {
			if (board[yy][xx] == c && !visited2[yy][xx]) {
				visited2[yy][xx] = true;
				board[yy][xx] = '.';
				convert(yy, xx, c);
			}
		}
	}
}

void down(int y, int x) {
	while (board[y][x] == '.' && check(y-1,x) && board[y-1][x] != '.'){
		char c = board[y - 1][x];
		board[y - 1][x] = '.';
		int xx = x, yy = y;
		while (check(yy, xx) && board[yy][xx] == '.') {
			yy++;
		}
		board[yy - 1][x] = c;
		y -= 1;
	}
}

int main() {
	for (int i = 0; i < N; i++) {
		cin >> board[i];
	}
	int answer = 0;
	while(true) {
		list.clear();
		visited = vector<vector<bool>>(12, vector<bool>(6, false));
		for (int i = 0; i < N; i++) {
			for (int k = 0; k < M; k++) {
				if (board[i][k] != '.' && !visited[i][k]) {
					int cnt = checkCnt(i, k, board[i][k], 1);
					if (cnt >= 4) {
						visited2 = vector<vector<bool>>(12, vector<bool>(6, false));
						convert(i, k, board[i][k]);
					}
				}
			}
		}
		if (list.empty()) {
			break;
		}
		answer++;
		for (pair<int,int> d : list) {
			down(d.first, d.second);
		}
	}
	cout << answer << "\n";
	return 0;
}
