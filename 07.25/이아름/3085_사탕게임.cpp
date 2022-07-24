#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int N, biggest = 0;
vector<string> board;
int px[] = { 0,-1,0,1 };
int py[] = { -1,0,1,0 };
void checkSame() { //���ӵǴ� ���� ����
	for (int i = 0; i < N; i++) {
		int count = 1;
		for (int k = 1; k < N; k++) { // ��Ȯ��
			if (board[i][k - 1] == board[i][k]) {
				count++;
				biggest = max(count, biggest);
			}
			else {
				count = 1;
			}
		}
		count = 1;
		for (int k = 1; k < N; k++) { //��Ȯ��
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
	//�Է� ����
	cin >> N;
	for (int i = 0; i < N; i++) {
		string str; cin >> str;
		board.push_back(str);
	}

	//�� �ڵ� ����
	for (int i = 0; i < N; i++) {
		for (int k = 0; k < N; k++) {
			int nextX, nextY;
			for (int t = 0; t < 4; t++) {
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
