#include <string>
#include <vector>

using namespace std;

vector<vector<int>> answer;

void hanoi(int n, int start, int to, int via) {
	if (n == 1) {
		answer.push_back({ start, to });
		return;
	}
	hanoi(n - 1, start, via,to); //n-1까지를 경유지에 둠
	answer.push_back({ start,to }); //n을 목적지로 옮김
	hanoi(n - 1, via, to, start); //n-1까지를 목적지로 옮김
}

/*
	1							1
 	2     ->      1    ->		2
	3			  2 3			3
	. . .		. . .		. . .
*/

vector<vector<int>> solution(int n) {
	hanoi(n, 1, 3, 2);
	return answer;
}
