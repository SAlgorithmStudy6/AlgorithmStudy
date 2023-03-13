#include <algorithm>
using namespace std;

int GCD(int a, int b) {
	if (b < 1) {
		return a;
	}
	return GCD(b, a%b);
}

long long solution(int w, int h) {
	long long answer = (long long)h*w;
	int gcd = GCD(max(w, h), min(w, h));
	int minus = (h + w - gcd);
	return answer - (long long)minus;
}
