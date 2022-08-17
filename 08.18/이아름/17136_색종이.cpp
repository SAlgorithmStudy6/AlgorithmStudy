#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>
using namespace std;

int N = 10;
vector<vector<int>> board(N);
int paper[] = { 5,5,5,5,5 };
int minAnswer = INT_MAX;

bool checkPaper(int x, int y, int count) {
	for (int i = 0; i < count; i++) {
		for (int k = 0; k < count; k++) {
			if (board[x + i][y + k] == 0) {
				return false;
			}
		}
	}
	return true;
}

void changeBoard(int x, int y, int count, int flag) {
	for (int i = 0; i < count; i++) {
		for (int k = 0; k < count; k++) {
			board[i + x][k + y] = flag;
		}
	}
}

void dfs(int x,int y, int answer) {
	if (minAnswer <= answer) { //가지치기
		return;
	}
	bool find = false;
	for (; x < N; x++) {
		for (; y < N; y++) {
			if (board[x][y] == 1) {
				find = true;
				break;
			}
			if (x == N - 1 && y == N - 1) {
				minAnswer = min(minAnswer, answer);
				return;
			}
		}
		if (find) break;
		y = 0;
	}
	for (int t = 5; t > 0; t--) {
		if (x + t > N || y + t > N || paper[t - 1] == 0) continue; //범위를 벗어나면
		if (checkPaper(x, y, t)) { //되는지 확인
			paper[t - 1]--;
			changeBoard(x, y, t, 0);
			dfs(x, y, answer + 1);
			changeBoard(x, y, t, 1);
			paper[t - 1]++;
		}
	}
}

int main() {
	for (int i = 0; i < N; i++) {
		for (int k = 0; k < N; k++) {
			int input; cin >> input;
			board[i].push_back(input);
		}
	}
	dfs(0,0,0);
	if (minAnswer == INT_MAX) {
		cout << -1 << endl;
	}
	else {
		cout << minAnswer << endl;
	}
	return 0;
}
