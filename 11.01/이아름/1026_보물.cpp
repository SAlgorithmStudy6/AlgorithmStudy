#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int main() {
	int N; cin >> N;
	vector<int> A,B;
	for (int i = 0; i < N; i++) {
		int n; cin >> n;
		A.push_back(n);
	}
	for (int i = 0; i < N; i++) {
		int n; cin >> n;
		B.push_back(n);
	}
	sort(A.begin(), A.end());
	sort(B.begin(), B.end(), greater<int>());
	int answer = 0;
	for (int i = 0; i < N; i++) {
		answer += A[i] * B[i];
	}
	cout << answer << endl;
	return 0;
}
