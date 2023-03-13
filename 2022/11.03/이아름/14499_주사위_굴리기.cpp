#include <iostream>
#include <vector>
using namespace std; 

int N, M, x, y, K;
vector<vector<int>> board;
int dir = 6; //현재 바닥에 있는 방향
int dice[7] = { 0 }; //주사위

void checkDir(int n) {
	int tmp;
	switch (n) {
	case 1 :
		tmp = dice[4];
		dice[4] = dice[1];
		dice[1] = dice[3];
		dice[3] = dice[6];
		dice[6] = tmp;
		break;
	case 2:
		tmp = dice[3];
		dice[3] = dice[1];
		dice[1] = dice[4];
		dice[4] = dice[6];
		dice[6] = tmp;
		break;
	case 3:
		tmp = dice[5];
		dice[5] = dice[1];
		dice[1] = dice[2];
		dice[2] = dice[6];
		dice[6] = tmp;
		break;
	case 4:
		tmp = dice[2];
		dice[2] = dice[1];
		dice[1] = dice[5];
		dice[5] = dice[6];
		dice[6] = tmp;
		break;
	}
}
int dx[] = { 0,0,0,-1,1 };
int dy[] = { 0,1,-1,0,0 };

void goDir(int next) {
	//이동
	int xx = x + dx[next];
	int yy = y + dy[next];
	if (0 <= xx && xx < N && 0 <= yy && yy < M) {
		x = xx;
		y = yy;
	}
	else return;
	checkDir(next);
	//쓰기
	if (board[x][y] == 0) {
		board[x][y] = dice[6];
	}
	else {
		dice[6] = board[x][y];
		board[x][y] = 0;
	}
	//출력
	cout << dice[1] << endl;
}

int main() {
	cin >> N >> M >> x >> y >> K;
	for (int i = 0; i < N; i++) {
		vector<int> list;
		for (int k = 0; k < M; k++) {
			int n; cin >> n;
			list.push_back(n);
		}
		board.push_back(list);
	}
	for (int i = 0; i < K; i++) {
		int n; cin >> n;
		goDir(n);
	}
	return 0;
}
