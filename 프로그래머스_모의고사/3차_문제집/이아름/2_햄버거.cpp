#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <stack>

using namespace std;

int solution(vector<int> ingredient) { 
	int answer = 0;
	stack<int> s; s.push(0);
	//s에 1,2가 있고 배열에 3,1이 있는 경우 햄버거 가능
	for (int i = 0; i < ingredient.size()-1; i++) {
		if (ingredient[i] == 3 && ingredient[i + 1] == 1) {
			if (s.top() == 2) {
				s.pop();
				if (s.top() == 1) {
					s.pop();
					answer++;
					i++;
					continue;
				}
				else {
					s.push(2);
				}
			}
		}
		s.push(ingredient[i]);
	}
	return answer;
}
