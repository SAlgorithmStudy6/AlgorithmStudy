#include <string>
#include <vector>

using namespace std;

int dy[] = { 1,0,-1 };
int dx[] = { 0,1,-1 };
vector<vector<int>> board(1001,vector<int>(1001));
int cnt = 1;

void draw(int y, int x, int N) {
    if (N == 0) {
        board[y][x] = cnt++;
        return;
    }
    for (int i = 0; i < 3; i++) {
        for (int k = 0; k < N; k++) {
            board[y][x] = cnt++;
            y += dy[i];
            x += dx[i];
        }
    }
}

int getTotal(int n) {
    int answer = 0;
    for (int i = 1; i <= n; i++) {
        answer += i;
    }
    return answer;
}

vector<int> solution(int n) {
    vector<int> answer;
    int y = 0, x = 0, N = n - 1;
    int total = getTotal(n);
    while (cnt <= total) {
        draw(y, x, N);
        y += 2;
        x += 1;
        N -= 3;
    }
    //직렬화
    for (int i = 0; i < n; i++) {
        for (int k = 0; k <= i; k++) {
            answer.push_back(board[i][k]);
        }
    }
    return answer;
}
