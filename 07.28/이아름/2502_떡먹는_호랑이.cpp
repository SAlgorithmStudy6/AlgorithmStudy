#include <iostream>
#include <algorithm>
using namespace std;

int D, K;
int checkSum(int A,int B) {
	int next = A + B;
	for (int i = 0; i < D-3; i++) {
		A = B;
		B = next;
		next = A + B;
	}
	return next;
}

int main() { 
	cin >> D >> K;
	for (int i = 1; i <= K; i++) {
		for (int k = i; k <= K; k++) {
			if (checkSum(i, k) == K) {
				cout << i << '\n' << k << endl;
				return 0;
			}
		}
	}
	return 0;
}
