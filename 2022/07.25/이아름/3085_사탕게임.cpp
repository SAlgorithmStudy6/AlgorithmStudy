#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int N, biggest = 0;
vector<string> board;
int px[] = {-1,0,1 };
int py[] = { 0,1,0 };
void checkSame() { //연속되는 개수 세기
	for (int i = 0; i < N; i++) {
		int count = 1;
		for (int k = 1; k < N; k++) { // 행확인
			if (board[i][k - 1] == board[i][k]) {
				count++;
				biggest = max(count, biggest);
			}
			else {
				count = 1;
			}
		}
		count = 1;
		for (int k = 1; k < N; k++) { //열확인
			if (board[k-1][i] == board[k][i]) {
				count++;
				biggest = max(count, biggest);
			}
			else {
				count = 1;
			}
		}
	}
}

int main() {
	//입력 시작
	cin >> N;
	for (int i = 0; i < N; i++) {
		string str; cin >> str;
		board.push_back(str);
	}

	//본 코드 시작
	for (int i = 0; i < N; i++) {
		for (int k = 0; k < N; k++) {
			int nextX, nextY;
			for (int t = 0; t < 3; t++) {
				nextX = i + px[t];
				nextY = k + py[t];
				if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
					swap(board[nextX][nextY], board[i][k]);
					checkSame();
					swap(board[nextX][nextY], board[i][k]);
				}
			}
		}
	}

	cout << biggest << endl;
	return 0;
}
