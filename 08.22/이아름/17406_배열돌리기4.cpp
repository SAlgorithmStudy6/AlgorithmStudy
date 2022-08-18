#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>
using namespace std;

int N, M, K; 
vector<vector<int>> board;
vector<vector<int>> change;
int dx[] = { 1,0,-1,0 };
int dy[] = { 0,1,0,-1 };
int minAnswer = INT_MAX;

//바꾸는 함수
void swap(int x1, int y1, int x2, int y2, vector<vector<int>> &copy) {
	int tmp = copy[x1][y1];
	copy[x1][y1] = copy[x2][y2];
	copy[x2][y2] = tmp;
}

//돌리는 함수
void rotation(int x,int y,int s, vector<vector<int>> &copy) {
	int endx[] = { x + s,x + s,x - s,x - s };
	int endy[] = { y - s,y + s,y + s,y - s };
	int index = 0;
	x -= s; y -= s;
	for (int i = 0; i < s * 8 -1; i++) {
		if (x == endx[index] && y == endy[index]) {
			index++;
		}
		int xx = x + dx[index];
		int yy = y + dy[index];
		swap(x, y, xx, yy, copy);
		x = xx; y = yy;
	}
}

//최소 값 구하는 함수
void minScore(vector<vector<int>> &copy) {
	for (int i = 1; i <= N; i++) {
		int sum = 0;
		for (int k = 1; k <= M; k++) {
			sum += copy[i][k];
			if (minAnswer < sum) {
				break;
			}
		}
		minAnswer = min(minAnswer, sum);
	}
}

int main() {
  //입력
	cin >> N >> M >> K;
	board.push_back({});
	for (int i = 1; i <= N; i++) {
		vector<int> tmp;
		tmp.push_back(0);
		for (int k = 1; k <= M; k++) {
			int input; cin >> input;
			tmp.push_back(input);
		}
		board.push_back(tmp);
	}
	vector<int> order;
	for (int i = 0; i < K; i++) {
		int x, y, s; cin >> x >> y >> s;
		change.push_back({ x,y,s });
		order.push_back(i);
	}
  //시작
	do { //순열 
		vector<vector<int>> copy(board.begin(), board.end());
		for (int i = 0; i < K; i++) {
			for (int k = 1; k <= change[order[i]][2]; k++) {
				rotation(change[order[i]][0], change[order[i]][1], k,copy);
			}
		}
		minScore(copy);
	} while (next_permutation(order.begin(), order.end()));
	cout << minAnswer << endl;
	return 0;
}
