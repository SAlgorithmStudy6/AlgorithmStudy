#include <iostream>
#include <queue>
using namespace std;

int N, K;
int board[500000];
queue<int> q;
bool check(int now, int next) {// next < K*3 &&
	if (0 <= next && next < K * 2 && board[next] == 0) {
		return true;
	}
	return false;
}

void qInput(int now, int next) {
	board[next] = board[now] + 1;
	q.push(next);
}

int main() {
	cin >> N >> K;
	if (K <= N) {
		cout << N - K << endl;
		return 0;
	}
	q.push(N);
	while (!q.empty()) { //순서바꾸니 맞음 왜?
		int now = q.front();
		q.pop();
		if (now == K) {
			break;
		}
		int next;
		next = now + 1;
		if (check(now, next)) {
			qInput(now, next);
		}
		next = now - 1;
		if (check(now, next)) {
			qInput(now, next);
		}
		next = now * 2;
		if (check(now, next)) {
			qInput(now, next);
		}
	}
	board[N] = 0;
	cout << board[K] << endl;
	return 0;
}
