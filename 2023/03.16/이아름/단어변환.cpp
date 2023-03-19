#include <string>
#include <vector>
#include <queue>

using namespace std;

bool canChange(string begin, string compare) {
    int count = 0;
    for (int i = 0; i < begin.size(); i++) {
        if (begin[i] != compare[i]) {
            count++;
            if (count > 1) return false;
        }
    }
    return true;
}

int solution(string begin, string target, vector<string> words) {
    queue<pair<string, int>> q;
    q.push({ begin,0 });
    bool visited[51] = { false };
    while (!q.empty()) {
        string str = q.front().first;
        int cnt = q.front().second;
        q.pop();
        if (str.compare(target) == 0) {
            return cnt;
        }
        for (int i = 0; i < words.size(); i++) {
            if (!visited[i]) {
                if (canChange(str, words[i])) {
                    q.push({ words[i],cnt + 1 });
                    visited[i] = true;
                }
            }
        }
    }
    return 0;
} 
