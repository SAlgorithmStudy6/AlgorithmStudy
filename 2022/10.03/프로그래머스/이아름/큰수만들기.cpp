#include <string>
#include <vector>

using namespace std;

string solution(string number, int k) {
    string answer = "";
    for (int i = 0; i < number.size(); i++) {
        if (k > 0) {
            if (answer.empty()) {
                answer += number[i];
            }
            else {
                if (answer[answer.size() - 1] < number[i]) {
                    answer = answer.substr(0, answer.size() - 1);
                    i--;
                    k--;
                }
                else {
                    answer += number[i];
                }
            }
        }
        else {
            answer += number.substr(i);
            break;
        }
    }
    if (k > 0) answer = answer.substr(0, answer.size() - k);
    return answer;
}
