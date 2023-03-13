#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int N;
int answer = 0;
void queen(int index, vector<pair<int, int>> queens) {
	if (index == N) {
		answer++;
		return;
	}
	for (int i = 0; i < N; i++) {
		bool flag = true;
		for (int k = 0; k < queens.size(); k++) {
			if (queens[k].first == index || queens[k].second == i ||
				abs(queens[k].first - index) == abs(queens[k].second - i)) {
				flag = false;
				break;
			}
		}
		if (flag) {
			vector<pair<int, int>> tmp(queens.begin(), queens.end());
			tmp.push_back({ index,i });
			queen(index + 1, tmp);
		}
	}
}

int solution(int n) {
	N = n;
	queen(0, {});
	return answer;
}
