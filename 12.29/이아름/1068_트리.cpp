#include <iostream>
#include <vector>
#include <queue>

using namespace std;
int N;
vector<int> child[51]; 
int removeIdx;

int getLeaf() {
	int answer = 0;
	for (int i = 0; i < N; i++) {
		if (child[i].empty()) answer++;
		if (child[i].size() == 1 && child[i][0] == removeIdx) answer++;
	}
	return answer;
}

int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		int n; cin >> n;
		if (n >= 0) {
			child[n].push_back(i);
		}
	}

	queue<int> q;
	cin >> removeIdx;
	q.push(removeIdx);
	while (!q.empty()) {
		int now = q.front(); q.pop();
		if (now < 0) continue;
		for (int n : child[now]) {
			q.push(n);
		}
		child[now].push_back(-2);
	}

	cout << getLeaf() << endl;
	return 0;
}
