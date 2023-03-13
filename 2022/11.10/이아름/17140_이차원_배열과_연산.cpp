#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
	int r, c, k; cin >> r >> c >> k;
	r -= 1; c -= 1;
	vector<vector<int>> board(100,vector<int>(100));
	for (int i = 0; i < 3; i++) {
		for (int k = 0; k < 3; k++) {
			cin >> board[i][k];
		}
	}
	int answer = 0;
	int h=3, w=3;
	while (true) {
		if (answer > 100) break;
		if (r < h && c < w && board[r][c] == k) {
			break;
		}
		answer++;
		if (h >= w) { //행 정렬
			int nw = 0;
			for (int i = 0; i < h; i++) {
				int sum[101] = { 0 };
				for (int k = 0; k < w; k++) {
					sum[board[i][k]]++;
					board[i][k] = 0;
				}
				vector<pair<int, int>> sorted;
				for (int k = 1; k < 101; k++) {
					if (sum[k] > 0) {
						sorted.push_back({ sum[k],k });
					}
				}
				int index = 0;
				sort(sorted.begin(), sorted.end());
				for (auto s : sorted) {
					board[i][index++] = s.second;
					board[i][index++] = s.first;
				}
				nw = max(nw, index);
			}
			w = nw;
		}
		else { //열 정렬
			int nh = 0;
			for (int i = 0; i < w; i++) {
				int sum[101] = { 0 };
				for (int k = 0; k < h; k++) {
					sum[board[k][i]]++;
					board[k][i] = 0;
				}
				vector<pair<int, int>> sorted;
				for (int k = 1; k < 101; k++) {
					if (sum[k] > 0) {
						sorted.push_back({ sum[k],k });
					}
				}
				int index = 0;
				sort(sorted.begin(), sorted.end());
				for (auto s : sorted) {
					board[index++][i] = s.second;
					board[index++][i] = s.first;
				}
				nh = max(nh, index);
			}
			h = nh;
		}
	}
	if (answer > 100) {
		cout << -1 << endl;
	}
	else {
		cout << answer << endl;
	}
	return 0;
}
