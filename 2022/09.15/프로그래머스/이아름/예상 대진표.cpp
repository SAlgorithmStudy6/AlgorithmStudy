#include <iostream>

using namespace std;

int solution(int n, int a, int b)
{
	int answer = 0;
	int A = a, B = b;
	while (A != B) {
		answer++;
		A = (A % 2) ? A + 1 : A; //짝수화
		B = (B % 2) ? B + 1 : B; //짝수화
		A /= 2;
		B /= 2;
	}
	return answer;
}
