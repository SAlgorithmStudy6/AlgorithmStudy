#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

bool sosu(long long n) {
	if (n <= 1) return false;
	if (n == 2) return true;
	for (long long i = 3; i <= sqrt(n); i++) {
		if (n%i == 0) return false;
	}
	return true;
}

int solution(int n, int k) {
	int answer = 0;
	string str = "";
	while (n) {
		str = to_string(n % k) + str;
		n /= k;
	}
	string cut = "";
	for (int i = 0; i < str.size(); i++) {
		if (str[i] == '0') {
			if (!cut.empty() && sosu(stoi(cut))) {
				answer++;
			}
			cut = "";
		}
		else {
			cut += str[i];
		}
	}
	if (!cut.empty() && sosu(stol(cut))) {
		answer++;
	}
	return answer;
}
