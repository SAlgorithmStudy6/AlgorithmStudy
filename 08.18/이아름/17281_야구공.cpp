#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N;
vector<vector<int>> ening;
int order[51];
bool visited[51] = { false };
int maxScore = 0;
void getScore() { //점수구하기
	int score = 0;
	int start = 0;
	for (int i = 0; i < N; i++) {
		int outCount = 0;
		bool onGround[] = { false,false,false }; //1루, 2루, 3루
		while (true) {
			if (outCount >= 3) {
				break;
			}
			for (int k = start; k < ening[0].size(); k++) {
				int s = ening[i][order[k]];
				if (s == 0) {
					outCount++;
					if (outCount >= 3) {
						start = k + 1;
						if (start == ening[0].size()) {
							start = 0;
						}
						break;
					}
				}
				else {
					for (int k = 0; k < min(s, 3); k++) { //득점
						if (onGround[2 - k]) {
							score++;
							onGround[2 - k] = false;
						}
					}
					for (int k = 2 - s; k >= 0; k--) { //이동
						if (onGround[k]) {
							onGround[k + s] = true;
							onGround[k] = false;
						}
					}
					if (s == 4) { //타자 이동
						score++;
					}
					else {
						onGround[s - 1] = true;
					}
				}
				if (k == ening[0].size() - 1) {
					k = -1;
				}
			}
		}
	}
	maxScore = max(maxScore, score);
}

void DFS(int Cnt) {
	if (Cnt == ening[0].size()) {
		getScore();
		return;
	}
	for (int i = 0; i < ening[0].size(); i++) {
		if (!visited[i]) {
			visited[i] = true;
			order[i] = Cnt;
			DFS(Cnt + 1);
			visited[i] = false;
		}
	}
}

int main() {
	cin >> N;
	for (int i = 0; i < N; i++) {
		vector<int> tmp;
		for (int k = 0; k < 9; k++) {
			int input; cin >> input;
			tmp.push_back(input);
		}
		ening.push_back(tmp);
	}
	order[3] = 0;
	visited[3] = true;
	DFS(1);
	cout << maxScore << endl;
	return 0;
}
