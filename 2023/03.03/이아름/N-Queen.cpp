#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int answer = 0;
void bfs(int n, int x, vector<pair<int, int>> v) {
    if (n == v.size()) {
        answer++;
        return;
    }
    for (int i = 0; i < n; i++) {
        bool flag = true;
        for (pair<int, int> p : v) {
            if (p.first == x || p.second == i ||
                abs(x - p.first) == abs(i - p.second)) {
                flag = false;
                break;
            }
        }
        if (flag) {
            vector<pair<int, int>> v1 = v;
            v1.push_back({ x,i });
            bfs(n, x + 1, v1);
        }
    }
}
int solution(int n) {
    bfs(n, 0, {});
    return answer;
}
