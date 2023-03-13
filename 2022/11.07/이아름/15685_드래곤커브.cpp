#include <iostream>
#include <stack>
#include <queue>
#include <vector>
#include <algorithm>

using namespace std;

vector<vector<bool>> board(101,vector<bool>(101,false));
int x, y, d, g;

int dy[] = {0,-1,0,1};
int dx[] = {1,0,-1,0};
int N;
int checkCycle() {
	int answer = 0;
	for (int i = 0; i < 100; i++) {
		for (int k = 0; k < 100; k++) {
			if (board[i][k]) {
				if (board[i][k + 1] && board[i + 1][k] && board[i + 1][k + 1]) {
					answer++;
				}
			}
		}
	}
	return answer;
}

void goDragon() {
	stack<int> go;
	go.push(d);
	vector<int> list;
	for (int i = 0; i <= g; i++) {
		while (!go.empty()) {
			board[y][x] = true;
			d = go.top(); go.pop();
			list.push_back(d);
			y += dy[d];
			x += dx[d];
		}
		board[y][x] = true;
		for (int n : list) {
			go.push(n + 1 > 3 ? 0 : n + 1);
		}
	}
}

int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> x >> y >> d >> g;
		goDragon();
	}
	cout << checkCycle() << "\n";
	return 0;
}
