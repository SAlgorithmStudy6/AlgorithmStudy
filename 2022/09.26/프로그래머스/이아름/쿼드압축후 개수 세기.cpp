#include <string>
#include <vector>

using namespace std;
vector<int> answer(2,0);
vector<vector<int>> list;

bool check(int x, int y, int n, int tag) {
    for (int i = 0; i < n; i++) {
        for (int k = 0; k < n; k++) {
            if (list[x + i][y + k] != tag) {
                return false;
            }
        }
    }
    return true;
}

void divid(int x, int y, int cnt) { //시작점, 변 길이
    int tag = list[x][y];
    if (cnt == 1) {
        answer[tag]++;
        return;
    }
    if (check(x, y, cnt, tag)) {
        answer[tag]++;
        return;
    }
    else {
        int half = cnt / 2;
        divid(x, y, half);
        divid(x, y + half, half);
        divid(x + half, y, half);
        divid(x + half, y + half, half);
    }
}

vector<int> solution(vector<vector<int>> arr) {
    list = arr;
    divid(0, 0, arr.size());
    return answer;
}
