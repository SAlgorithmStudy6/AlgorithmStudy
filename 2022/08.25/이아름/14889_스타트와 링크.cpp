#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>
using namespace std;

bool visited[21] = { false };
int selected[11] = { 0 };
vector<vector<int>> board;
int minDiff = INT_MAX;
int N;
void getDiff() {
	vector<int> deSelected;
	int index = 0;
	for (int i = 0; i < N; i++) {
		if (i == selected[index]) {
			index++;
		}
		else {
			deSelected.push_back(i);
		}
	}
	int sumSel = 0;
	int sumDeSel = 0;
	for (int i = 0; i < N / 2; i++) {
		for (int k = 0; k < N / 2; k++) {
			if (i == k) continue;
			sumSel += board[selected[i]][selected[k]];
			sumDeSel += board[deSelected[i]][deSelected[k]];
		}
	}
	minDiff = min(minDiff, abs(sumSel - sumDeSel));
}

void makeSelect(int count) {
	if (minDiff == 0) return;
	if (count == N / 2) {
		getDiff();
		return;
	}
	int limit = 0;
	if (count != 0) {
		limit = selected[count - 1] + 1;
	}
	for (int i = limit; i < N; i++) {
		if (!visited[i]) {
			visited[i] = true;
			selected[count] = i;
			makeSelect(count + 1);
			visited[i] = false;
		}
	}
}


int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		vector<int> tmp;
		for (int k = 0; k < N; k++) {
			int input; cin >> input;
			tmp.push_back(input);
		}
		board.push_back(tmp);
	}
	makeSelect(0);
	cout << minDiff << endl;
	return 0;
}