#include <string>
#include <vector>
#include <stack>

using namespace std;

int solution(string s) {
    int answer = 0;
    for (int i = 0; i < s.size(); i++) {
        stack<char> stacks;
        string str = s.substr(i);
        str += s.substr(0, i);
        bool flag = true;
        for (char c : str) {
            if (c == '(' || c == '[' || c == '{') {
                stacks.push(c);
            }
            else if (c == ')') {
                if (stacks.top() == '(') stacks.pop();
                else {
                    flag = false;
                    break;
                }
            }
            else if (c == ']') {
                if (stacks.top() == '[') stacks.pop();
                else {
                    flag = false;
                    break;
                }
            }
            else if (c == '}') {
                if (stacks.top() == '{') stacks.pop();
                else {
                    flag = false;
                    break;
                }
            }
        }
        if (flag && stacks.empty()) {
            answer++;
        }
    }
    return answer;
}
