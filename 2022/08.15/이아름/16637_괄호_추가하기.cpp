#include <iostream>
#include <string>
#include <algorithm>
#include <deque>
#include <cmath>
#include <limits.h>
using namespace std;
int calculator(int n1, int n2, char cal) {
	switch (cal) {
	case '+': 
		return n1 + n2;
	case '-': 
		return n1 - n2;
	case '*': 
		return n1 * n2;
	}
	return 0;
}

int startCal(deque<string> &dq) {
	while (dq.size() > 1) {
		int n1 = stoi(dq.front()); dq.pop_front();
		char cal = dq.front()[0]; dq.pop_front();
		int n2 = stoi(dq.front()); dq.pop_front();
		int result = calculator(n1, n2, cal);
		dq.push_front(to_string(result));
	}
	int total = stoi(dq.front());
	dq.pop_front();
	return total;
}

int main() {
	int N; cin >> N;
	string str; cin >> str;
	int maxTotal = INT_MIN;
	for (int i = 0; i < (1 << N / 2); i++) {
		deque<string> dq;
		dq.push_back(str.substr(0, 1));
		for (int j = 0; j < N / 2; j++) { //연속된 수 x
			if (i & (1 << j)) {
				if (j > 0 && (i & (1 << (j - 1)))) {
					dq.clear();
					break;
				}
				int n1 = stoi(dq.back());
				dq.pop_back();
				char c = str[j*2+1];
				int n2 = str[j * 2 + 2] - '0';
				int result = calculator(n1, n2, c);
				if (result < 0 && !dq.empty() && dq.back().compare("-")==0) {
					dq.pop_back();
					dq.push_back("+");
					result = abs(result);
				}
				dq.push_back(to_string(result));
			}
			else {
				dq.push_back(str.substr(j*2+1,1));
				dq.push_back(str.substr(j * 2 + 2, 1));
			}
		}
		if (dq.size() > 0) {
			int total = startCal(dq);
			maxTotal = max(total, maxTotal);
		}
	}
	cout << maxTotal << endl;
	return 0;
}
