#include <iostream>
using namespace std;

int main() {
	int E, S, M;
	cin >> E >> S >> M;
	int nE = 1, nS = 1, nM = 1;
	int count = 1;
	while (nE != E || nS != S || nM != M) {
		nE++; nS++; nM++;
		if (nE >= 16) nE = 1;
		if (nS >= 29) nS = 1;
		if (nM >= 20) nM = 1;
		count++;
	}
	cout << count << endl;
	return 0;
}
