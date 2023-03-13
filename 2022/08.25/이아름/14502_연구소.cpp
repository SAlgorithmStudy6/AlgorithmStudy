#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<vector<int>> board;
int wallX[3] = { 0 };
int wallY[3] = { 0 };
int N, M;
int maxSafe = 0;
vector<pair<int, int>> virus;

void getSafe() {
	vector<vector<int>> copy_board(board.begin(), board.end());
	queue<vector<int>> q; //x,y
	for (auto v : virus) {
		q.push({ v.first,v.second });
	}
	int dx[] = { -1,0,1,0 };
	int dy[] = { 0,1,0,-1 };
	while (!q.empty()) {
		int x = q.front()[0];
		int y = q.front()[1]; q.pop();
		for (int i = 0; i < 4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if (0 <= xx && xx < N && 0 <= yy && yy < M) {
				if (copy_board[xx][yy] == 0) {
					q.push({ xx,yy });
					copy_board[xx][yy] = 2;
				}
			}
		}
	}
	int count = 0;
	for (int i = 0; i < N; i++) {
		for (int k = 0; k < M; k++) {
			if (copy_board[i][k] == 0) {
				count++;
			}
		}
	}
	maxSafe = max(maxSafe, count);
}

void getWall(int count) {
	if (count == 3) {
		getSafe();
		return;
	}
	if (maxSafe == M * N) return;
	int x = count == 0 ? 0 : wallX[count - 1];
	int y = count == 0 ? 0 : wallY[count - 1] + 1;
	for (int i = x; i < N; i++) {
		for (; y < M; y++) {
			if (board[i][y] == 0) {
				wallX[count] = i; wallY[count] = y;
				board[i][y] = 1;
				getWall(count + 1);
				board[i][y] = 0;
			}
		}
		y = 0;
	}

}

int main() {
	cin >> N >> M;
	for (int i = 0; i < N; i++) {
		vector<int> tmp;
		for (int k = 0; k < M; k++) {
			int input; cin >> input;
			tmp.push_back(input);
			if (input == 2) {
				virus.push_back({ i,k });
			}
		}
		board.push_back(tmp);
	}
	getWall(0);
	cout << maxSafe << endl;
	return 0;
}
