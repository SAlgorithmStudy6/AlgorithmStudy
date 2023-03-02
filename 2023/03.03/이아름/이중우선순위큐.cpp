#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<string> operations) {
    vector<int> answer;
    for (string str : operations) {
        if (str[0] == 'I') {
            answer.push_back(stoi(str.substr(2)));
            sort(answer.begin(), answer.end());
        }
        else if (str[0] == 'D') {
            if (answer.size() > 0) {
                if (stoi(str.substr(2)) < 0) {
                    answer.erase(answer.begin());
                }
                else {
                    answer.erase(answer.begin() + (answer.size() - 1));
                }
            }
        }
    }
    if (answer.size() == 0) {
        return { 0,0 };
    }
    return {answer[answer.size()-1],answer[0]};
}
