#include <string>
#include <vector>
#include <iostream>

using namespace std;
int pac = 1;
vector<vector<int>> board;
int N;
int dx[] = { 1,0,-1 };
int dy[] = { 0,1,-1 };

int draw(int x, int y, int cnt, int end) {
	int max_n = N - x - y-1;
	int xx = x, yy = y;
	if (max_n == 0) {
		board[x][y] = cnt++;
		return cnt;
	}
	for (int i = 0; i < 3; i++) {
		for (int k = 0; k < max_n; k++) {
			board[x][y] = cnt++;
			x += dx[i]; y += dy[i];
			if (cnt > end) return end+1;
		}
	}
	return cnt;
}

vector<int> solution(int n) {
	vector<int> answer;
	N = n;
	board = vector<vector<int>>(n, vector<int>(n, 0));
	int start = 1; int end = 0;
	for (int i = 1; i <= n; i++) {
		end += i;
	}
	int index = 0;
	if (start == end) {
		board[0][0] = start;
	}
	while (start < end) {
		start = draw(index * 2, index, start, end);
		index++;
		if (start == end) {
			board[index * 2][index] = end;
		}
	}
	//직렬화
	for (int i = 0; i < n; i++) {
		for (int k = 0; k <= i; k++) {
			answer.push_back(board[i][k]);
		}
	}
	return answer;
}

int main() {
	solution(10);
	return 0;
}
