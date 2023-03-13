#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int R, C, T;

int dx[] = { 0,1,0,-1 };
int dy[] = { -1,0,1,0 };
vector<pair<int, int>> p;
vector<vector<int>> boardSpread;

void spread(vector<vector<int>>& board) { //미세먼지 퍼짐
	vector<vector<int>> tmp(R,vector<int>(C,0));
	for (int i = 0; i < R; i++) {
		for (int k = 0; k < C; k++) {
			if (board[i][k] > 0) {
				int div = board[i][k] / 5;
				int count = 0;
				for (int j = 0; j < 4; j++) { //퍼지기
					int xx = i + dx[j];
					int yy = k + dy[j];
					if (0 <= xx && xx < R && 0 <= yy & yy < C) {
						if (board[xx][yy] < 0)continue;
						tmp[xx][yy] += div;
						count++;
					}
				}
				tmp[i][k] += (board[i][k] - (div * count));
			}
			else if(board[i][k] < 0){
				tmp[i][k] = -1;
			}
		}
	}
	boardSpread = tmp;
}
int go[2][4] = { {3,2,1,0},{1,2,3,0} };

int clean(int x,int y,int type) { //미세먼지 청소 x,y,type 0 - 위(반시계) 1 - 아래(시계)
	int count = 0;
	x += dx[go[type][0]];
	y += dy[go[type][0]];
	boardSpread[x][y] = 0;
	bool flag = true;
	for (int i = 0; i < 4; i++) {
		int nx = x + dx[go[type][i]];
		int ny = y + dy[go[type][i]];
		while (true) {
			if (nx < 0 || nx >= R || ny < 0 || ny >= C) break;
			if (x == p[type].first && y == C - 1 && flag) {
				flag = false;
				break; //3번째 or 1번째
			}
			if (nx == p[type].first && ny == p[type].second) break; //도착
			if (boardSpread[nx][ny] > 0 || boardSpread[x][y] > 0) {
				int tmp = boardSpread[x][y];
				boardSpread[x][y] = boardSpread[nx][ny];
				boardSpread[nx][ny] = tmp;
				count++;
			}
			x = nx; y = ny;
			nx = x + dx[go[type][i]];
			ny = y + dy[go[type][i]];
		}
	}
	return count;
}

int main() {
	cin >> R >> C >> T;
	for (int i = 0; i < R; i++) {
		vector<int> list;
		for (int k = 0; k < C; k++) {
			int n; cin >> n;
			list.push_back(n);
			if (n < 0) {
				p.push_back({ i,k });
			}
		}
		boardSpread.push_back(list);
	}
	for (int t = 0; t < T; t++) {
		spread(boardSpread);
		int count = clean(p[0].first,p[0].second,0);
		count += clean(p[1].first, p[1].second, 1);
		if (count == 0) break;
	}
	int answer = 0;
	for (int i = 0; i < R; i++) {
		for (int k = 0; k < C; k++) {
			if (boardSpread[i][k] > 0) {
				answer += boardSpread[i][k];
			}
		}
	}
	cout << answer << endl;
	return 0;
}
