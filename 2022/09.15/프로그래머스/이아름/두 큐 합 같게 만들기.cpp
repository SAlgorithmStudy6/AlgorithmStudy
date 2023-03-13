#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> queue1, vector<int> queue2) {
	int answer = 0;
	long long sum1 = 0, sum2 = 0, half = 0;
	int count = queue1.size() + queue2.size();
	queue<int> q1, q2;
	for (int i = 0; i < queue1.size(); i++) { //q에 넣기 + 총합 구하기
		sum1 += queue1[i];
		q1.push(queue1[i]);
	}
	for (int i = 0; i < queue2.size(); i++) { //q에 넣기 + 총합 구하기
		sum2 += queue2[i];
		q2.push(queue2[i]);
	}
	half = sum1 + sum2;
	if (half % 2) return -1; //홀수이면 -1
	half /= 2;
	while (answer < count * 10) { //임의의 수
		if (sum1 == half) return answer; //탈출조건
		while (sum1 > half && !q1.empty()) { //한쪽이 크면 pop
			int n = q1.front(); q1.pop();
			if (n > half) return -1;
			q2.push(n);
			sum1 -= n;
			sum2 += n;
			answer++;
		}
		while (sum2 > half && !q2.empty()) {
			int n = q2.front(); q2.pop();
			if (n > half) return -1;
			q1.push(n);
			sum2 -= n;
			sum1 += n;
			answer++;
		}
	}
	if (sum1 != half) return -1; //모두 돌아도 충족하지 않으면 -1
	return answer;
}
