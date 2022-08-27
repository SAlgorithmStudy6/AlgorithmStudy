#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>
#include <math.h>
#include <queue>
using namespace std;

int dx[] = { 0,1,0,-1 };
int dy[] = { 1,0,-1,0 };
vector<vector<int>> board;
vector<int> ghost[5]; //유령 위치
vector<int> customer[5]; //고객 위치
vector<bool> isGhost(5); //유령 잡았는지
vector<bool> isCustomer(5); //유령 잡았는지
int minTime = INT_MAX; //제일 작은 시간
int customerSize = 0;
int N, M;

void init() {
	board.clear();
	minTime = INT_MAX;
	isGhost = vector<bool>(5, false);
	isCustomer = vector<bool>(5, false);
	customerSize = 0;
}

void getMinTime(int time, int x, int y) {
	if (customerSize == 0 || time >= minTime) {
		minTime = min(minTime, time);
		return;
	}
	for (int i = 1; i <= M; i++) { //유령
		if (!isGhost[i]) {
			isGhost[i] = true;
			int xx = ghost[i][0];
			int yy = ghost[i][1];
			int dif = abs(xx - x) + abs(yy - y);
			board[xx][yy] = 0;
			getMinTime(time + dif, xx, yy);
			board[xx][yy] = i;
			isGhost[i] = false;
		}
	}
	for (int i = 1; i <= M; i++) { //고객
		if (isGhost[i] && !isCustomer[i]) {
			customerSize--;
			isCustomer[i] = true;
			int xx = customer[i][0];
			int yy = customer[i][1];
			int dif = abs(xx - x) + abs(yy - y);
			board[xx][yy] = 0;
			getMinTime(time + dif, xx, yy);
			board[xx][yy] = i;
			isCustomer[i] = false;
			customerSize++;
		}
	}
}

int main() {
	int T; cin >> T;
	for (int t = 1; t <= T; t++) {
		cin >> N;
		init();
		for (int i = 0; i < N; i++) {
			vector<int> tmp;
			for (int k = 0; k < N; k++) {
				int input; cin >> input;
				tmp.push_back(input);
				if (input < 0) {
					customer[abs(input)] = { i,k };
					customerSize++;
				}
				else if (input > 0) {
					ghost[input] = { i,k };
				}
			}
			board.push_back(tmp);
		}
		M = customerSize;
		getMinTime(0, 0, 0);
		cout << "#" << t << " " << minTime << endl;
	}
	return 0;
}
