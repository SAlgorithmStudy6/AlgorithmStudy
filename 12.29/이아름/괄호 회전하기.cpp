#include <string>
#include <vector>
#include <stack>
#include <map>

using namespace std;

int solution(string s) {
    int answer = 0;
    map<char, char> m = { {')','('},{'}','{'},{']','['} };
    for (int i = 0; i < s.size(); i++) {
        stack<char> stacks;
        string str = s.substr(i);
        str += s.substr(0, i);
        bool flag = true;
        for (char c : str) {
            if (c == '(' || c == '[' || c == '{') {
                stacks.push(c);
            }
            else if (c == ')' || c == '}' || c == ']') {
                if(stacks.empty() || stacks.top() != m[c]) {
                    flag = false;
                    break;
                }
                else {
                    stacks.pop();
                }
            }
        }
        if (flag && stacks.empty()) {
            answer++;
        }
    }
    return answer;
}
