#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

bool solution(string S, string T) {
	if (S.size() >= T.size()) {
		if (S.compare(T) == 0) return true;
		return false;
	}
	char back = T[T.size() - 1];
	T = T.substr(0, T.size() - 1);
	if (back == 'B') {
		string tmp = "";
		for (int i = T.size() - 1; i >= 0; i--) {
			tmp += T[i];
		}
		T = tmp;
	}
	return solution(S, T);
}

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	string S, T;
	cin >> S >> T;
	cout << solution(S, T) << endl;
	return 0;
}
