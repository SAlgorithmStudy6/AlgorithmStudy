#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

int solution(int distance, vector<vector<int>> scope, vector<vector<int>> times) {
	map<int, int> m;
	for (int i = 0; i < scope.size(); i++) {
		m[scope[i][0]] = i;
	}
	sort(scope.begin(), scope.end());
	for (vector<int> s : scope) {
		int index = m[s[0]];
		for (int i = min(s[0], s[1]); i <= max(s[0], s[1]); i++) {
			int per = i % (times[index][0] + times[index][1]);
			if (per != 0 && per <= times[index][0]) {
				return i;
			}
		}
	}

	return distance;
}
