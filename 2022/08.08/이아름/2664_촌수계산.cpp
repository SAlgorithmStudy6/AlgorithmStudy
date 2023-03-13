#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int main() {
	int N; cin >> N;
	int board[200];
	for (int i = 1; i <= N; i++) {
		board[i] = i;
	}
	int p1, p2; cin >> p1 >> p2;
	int K; cin >> K;
	for (int i = 0; i < K; i++) {
		int x, y; cin >> x >> y;
		board[y] = x;
	}

	vector<int> parent1(N + 1, -1), parent2(N + 1, -1);
	int count = 0;
	parent1[p1] = count++;
	while (board[p1] != p1) {
		p1 = board[p1];
		parent1[p1] = count++;
	}
	count = 0;
	parent2[p2] = count++;
	while (board[p2] != p2) {
		p2 = board[p2];
		parent2[p2] = count++;
	}
	int answer = 200;
	for (int i = 1; i <= N; i++) {
		if (parent1[i] >= 0 && parent2[i] >= 0) {
			answer = min(parent1[i] + parent2[i],answer);
		}
	}
	if (answer == 200) cout << -1 << endl;
	else cout << answer << endl;
	return 0;
}
