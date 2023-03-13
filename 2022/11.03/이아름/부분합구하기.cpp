#include <string>
#include <vector>
#include <set>

using namespace std;

int solution(vector<int> elements) {
	set<int> s;
	vector<int> tmp(elements.begin(), elements.end());
	tmp.insert(tmp.end(), elements.begin(), elements.end());
	for (int i = 1; i <= elements.size(); i++) {
		for (int t = 0; t < elements.size(); t++) {
			int num = 0;
			for (int k = 0; k < i; k++) {
				num += tmp[t + k];
			}
			s.insert(num);
		}
	}
	return s.size();
}
