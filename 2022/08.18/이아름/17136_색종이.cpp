#include <iostream>
#include <vector>
#include <algorithm>
#include <limits.h>
using namespace std;

int N = 10;
vector<vector<int>> board(N); 
int paper[] = { 5,5,5,5,5 }; //색종이의 개수를 저장하는 변수 
int minAnswer = INT_MAX; //붙여야할 최소 개수

//count 크기의 색종이를 붙일수 있는지 확인
bool checkPaper(int x, int y, int count) { 
	for (int i = 0; i < count; i++) {
		for (int k = 0; k < count; k++) {
			if (board[x + i][y + k] == 0) {
				return false;
			}
		}
	}
	return true;
}

//x,y 부터 count 크기의 색종이를 붙이거나 때거나 (flag = 0 : 땜 / flag = 1 : 붙임)
void changeBoard(int x, int y, int count, int flag) {
	for (int i = 0; i < count; i++) {
		for (int k = 0; k < count; k++) {
			board[i + x][k + y] = flag;
		}
	}
}

void dfs(int x,int y, int answer) {
	if (minAnswer <= answer) { //가지치기
		return;
	}
	bool find = false; //board가 1인 것을 찾았는지
	for (; x < N; x++) {
		for (; y < N; y++) {
			if (board[x][y] == 1) { //찾음
				find = true;
				break;
			}
			if (x == N - 1 && y == N - 1) { //끝에 도달 -> minAnswer갱신
				minAnswer = min(minAnswer, answer);
				return;
			}
		}
		if (find) break;
		y = 0;
	}
	for (int t = 5; t > 0; t--) { //5부터 1까지 가능한 색종이 찾기
		//범위를 벗어나거나 종이가 없을때
		if (x + t > N || y + t > N || paper[t - 1] == 0) continue;
		if (checkPaper(x, y, t)) { //되는지 확인
			paper[t - 1]--; //색종이 사용
			changeBoard(x, y, t, 0); //색종이 붙이기
			dfs(x, y, answer + 1); //탐색
			changeBoard(x, y, t, 1); //색종이 때기
			paper[t - 1]++; //색종이 복구
		}
	}
}

int main() {
	//입력
	for (int i = 0; i < N; i++) {
		for (int k = 0; k < N; k++) {
			int input; cin >> input;
			board[i].push_back(input);
		}
	}
	//시작
	dfs(0,0,0);
	if (minAnswer == INT_MAX) { // 불가능 한 경우
		cout << -1 << endl;
	}
	else {
		cout << minAnswer << endl;
	}
	return 0;
}
