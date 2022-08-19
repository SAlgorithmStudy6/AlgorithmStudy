#include <string>
#include <vector>

using namespace std;

int solution(int a, int b, int n) {
	int answer = 0;
	while (n / a) {
		int plus = b * (n / a);
		answer += plus;
		n = n % a + plus;
	}
	return answer;
}
