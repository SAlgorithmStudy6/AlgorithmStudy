#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int main() {
	bool visited[200] = { false };
	bool connect[200][200] = { false }; 
	int N, K; cin >> N >> K;
	for (int i = 0; i < K; i++) {
		int c1, c2; cin >> c1 >> c2;
		connect[c1][c2] = connect[c2][c1] = true;
	}
	int count = 0;
	queue<int> q;
	q.push(1);
	visited[1] = true;
	while (!q.empty()) {
		int computer = q.front();
		q.pop();
		for (int i = 1; i <= N; i++) {
			if (connect[computer][i] && !visited[i]) {
				visited[i] = true;
				count++;
				q.push(i);
			}
		}
	}
	cout << count << endl;
	return 0;
}
