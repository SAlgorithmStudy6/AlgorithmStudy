#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N;
vector<vector<int>> ening; //각 이닝
int order[51]; //타자 순서
bool visited[51] = { false }; //타자를 넣었는지
int maxScore = 0; //최대 점수

 //점수구하기
void getScore() {
	int score = 0; //현재 점수
	int start = 0; //현재 시작 타자
	for (int i = 0; i < N; i++) { //이닝
		int outCount = 0; //이번 이닝에서 out당한 개수
		bool onGround[] = { false,false,false }; //1루, 2루, 3루에 사람이 있는지
		while (true) {
			if (outCount >= 3) { //3진 아웃 -> 이닝 교채
				break;
			}
			for (int k = start; k < ening[0].size(); k++) {
				int s = ening[i][order[k]];
				if (s == 0) { //아웃
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
					//이미 그라운드에 있는 주자 이동
					for (int k = 0; k < min(s, 3); k++) { //득점
						if (onGround[2 - k]) { //사람이 있으면
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
					//타자 이동
					if (s == 4) {  //홈런
						score++;
					}
					else {
						onGround[s - 1] = true;
					}
				} //k가 끝에 도달하면 처음부터
				if (k == ening[0].size() - 1) {
					k = -1;
				}
			}
		}
	}
	maxScore = max(maxScore, score);
}

void DFS(int Cnt) { //각 타자 순서 구하기
	if (Cnt == ening[0].size()) { //모든 타자를 구했으면
		getScore(); //점수구하기
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
	//입력
	cin >> N;
	for (int i = 0; i < N; i++) {
		vector<int> tmp;
		for (int k = 0; k < 9; k++) {
			int input; cin >> input;
			tmp.push_back(input);
		}
		ening.push_back(tmp);
	}
	//시작
	order[3] = 0; //4번 타자 정함
	visited[3] = true;
	DFS(1);
	cout << maxScore << endl;
	return 0;
}
