#include <iostream>
#include <queue>
using namespace std;
int main() {
	long long A, B; cin >> A >> B;
	queue<pair<long long,int>> q;
	q.push({ A,0 });
	while (!q.empty()) {
		long long n = q.front().first;
		int count = q.front().second;
		q.pop();
		if (n == B) {
			cout << count+1 << endl;
			return 0;
		}
		if (n * 2 <= B) {
			q.push({ n * 2,count + 1 });
		}
		if (n * 10 + 1 <= B) {
			q.push({ n * 10 + 1,count + 1 });
		}
	}
	cout << -1 << endl;
	return 0;
}
