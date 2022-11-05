#include <iostream>
#include <vector>
#include <queue>
#include <stack>
using namespace std;

int solution(int N, vector<vector<int>> road, int K) {
    int answer = 0;
    vector<vector<pair<int, int>>> board(N+1,vector<pair<int,int>>());
    for (auto r: road) {
        board[r[0]].push_back({ r[1], r[2] });
        board[r[1]].push_back({ r[0], r[2] });
    }
    priority_queue<pair<int,int>> pq;
    vector<bool> visited(N + 1, false);
    pq.push({ 0,1 });
    visited[1] = true;
    while (!pq.empty()) {
        int cnt = pq.top().first * -1;
        int now = pq.top().second;
        pq.pop();
        visited[now] = true;
        for (auto b : board[now]) {
            if (!visited[b.first] && cnt + b.second <= K) {
                pq.push({ -1 * (b.second + cnt), b.first});
            }
        }
    }

    for (int i = 1; i <= N; i++) {
        if (visited[i]) {
            answer++;
        }
    }

    return answer;
}
