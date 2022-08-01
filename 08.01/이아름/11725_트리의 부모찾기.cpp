#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int main() {
	int N; cin >> N;
	vector<int> connect[100005];
	int answer[100005] = { 0 };
	for (int i = 0; i < N - 1; i++) {
		int n1, n2; cin >> n1 >> n2;
		connect[n1].push_back(n2);
		connect[n2].push_back(n1);
	}
	queue<int> q;
	q.push(1);
	answer[1] = 1;
	while (!q.empty()) {
		int before = q.front(); q.pop();
		for (int next : connect[before]) {
			if (answer[next] == 0) {
				answer[next] = before;
				q.push(next);
			}
		}
	}
	for (int i = 2; i <= N; i++) {
		cout << answer[i] << "\n";
	}
	return 0;
}
