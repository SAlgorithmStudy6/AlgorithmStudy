#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

int solution(int distance, vector<vector<int>> scope, vector<vector<int>> times) {
	map<int, int> m; //time을 보기위해 index 저장
	for (int i = 0; i < scope.size(); i++) {
		m[scope[i][0]] = i;
	}
	sort(scope.begin(), scope.end()); //위치 오름차순으로 정렬
	for (vector<int> s : scope) {
		int index = m[s[0]];
		for (int i = min(s[0], s[1]); i <= max(s[0], s[1]); i++) {
			int per = i % (times[index][0] + times[index][1]); //침투하는 턴의 시간
			if (per != 0 && per <= times[index][0]) { //시간 1 ~ 근무 : 들킴
				return i; //중간에 들키면 해당 위치 return 
			}
		}
	}
	return distance;
}
