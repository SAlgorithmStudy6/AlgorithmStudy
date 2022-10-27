#include <iostream>
#include <deque>
#include <vector>
using namespace std;

vector<pair<int, char>> togo;
int board[101][101] = { 0 };
int N, K, L;

int dx[] = { 0,1,0,-1 };
int dy[] = { 1,0,-1,0 };
deque<pair<int, int>> dq;

int getLastTime() {
	dq.push_back({ 1,1 });
	board[1][1] = 1;
	int type = 0, time = 0;
	for (auto go : togo) {
		for (; time < go.first; time++) {
			pair<int, int> head = dq.front();
			int nextX = head.first + dx[type];
			int nextY = head.second + dy[type];
			if (nextX < 1 || nextX > N || nextY < 1 || nextY > N //범위를 벗어남
				|| board[nextX][nextY] == 1) { //몸과 부딪힘
				return time;
			}
			if (board[nextX][nextY] == 0) { //사과와 만나지 않음
				pair<int, int> tail = dq.back();
				board[tail.first][tail.second] = 0;
				dq.pop_back();
			}
			dq.push_front({ nextX,nextY }); //머리 움직임
			board[nextX][nextY] = 1;
		}
		if (go.second == 'L') {
			type = (type - 1) < 0 ? 3 : type - 1;
		}
		else if(go.second == 'D'){
			type = (type + 1) > 3 ? 0 : type + 1;
		}
	}
}

int main() {
	cin >> N >> K;
	for (int i = 0; i < K; i++) {
		int x, y; cin >> x >> y;
		board[x][y] = 2; //사과
	}
	cin >> L;
	for (int i = 0; i < L; i++) {
		int t; cin >> t;
		char C; cin >> C;
		togo.push_back({ t,C });
	}
	togo.push_back({ 1000000,'O' });
	cout << getLastTime()+1 << "\n";
	return 0;
}
