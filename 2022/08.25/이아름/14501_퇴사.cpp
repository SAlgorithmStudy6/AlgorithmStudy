#include <iostream>
#include <vector>
using namespace std;
int main() {
	int N; cin >> N;
	vector<vector<int>> board;
	vector<int> maxMoney(N + 1,0);
	for (int i = 0; i < N; i++) {
		int T, P; cin >> T >> P;
		board.push_back({ T,P });
	}
	for (int i = N - 1; i >= 0; i--) {
		int now;
		if (i + board[i][0] > N) {
			now = 0;
		}
		else {
			now = maxMoney[i + board[i][0]] + board[i][1];
		}
		maxMoney[i] = max(now, maxMoney[i + 1]);
	}
	cout << maxMoney[0] << endl;
	return 0;
}