#include <string>
#include <vector>
#include <map>
#include <cmath>

using namespace std;

vector<bool> visited(10, false);
map<int, int> list;
int answer = 0;
bool sosu(int n) {
    if (n <= 1) return false;
    if (n == 2) return true;
    for (int i = 2; i <= sqrt(n); i++) {
        if (n % i == 0) return false;
    }
    return true;
}

void makeNum(string str, string &numbers) {
    if (!str.empty()) {
        int num = stoi(str);
        if (sosu(num) && list[num] == 0) {
            answer++;
            list[num] = 1;
        }
    }
    for (int i = 0; i < numbers.size(); i++) {
        if (!visited[i]) {
            visited[i] = true;
            makeNum(str + numbers[i], numbers);
            visited[i] = false;
        }
    }
}

int solution(string numbers) {
    makeNum("",numbers);
    return answer;
}
