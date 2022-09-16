#include <string>
#include <vector>
#include <stack>

using namespace std;

int solution(string s) {
	int answer = 0;
	for (int i = 0; i < s.size(); i++) {
		bool isCorrect = true;
		int x = i;
		stack<char> stacks; stacks.push('0'); //empty 방지
		do{ //올바른 문자열인지 확인
			if (s[x] == ')') {
				if (stacks.top() != '(') {
					isCorrect = false;
					break;
				}
				stacks.pop();
			}else if (s[x] == '}') {
				if (stacks.top() != '{') {
					isCorrect = false;
					break;
				}
				stacks.pop();
			}else if(s[x] == ']') {
				if (stacks.top() != '[') {
					isCorrect = false;
					break;
				}
				stacks.pop();
			}
			else {
				stacks.push(s[x]);
			}
			x++;
			if (x == s.size()) {
				x = 0;
			}
		} while (x != i);
		if (stacks.size() > 1) isCorrect = false; //끝나도 stack 안비면
		if (isCorrect) answer++;
	}
	return answer;
}
