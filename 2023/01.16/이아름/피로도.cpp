#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int maxCount = 0;
void bfs(int k, int count, vector<bool>& visited, vector<vector<int>>& dungeons) {
    for (int i = 0; i < dungeons.size(); i++) {
        if (!visited[i] && k >= dungeons[i][0]) {
            visited[i] = true;
            bfs(k - dungeons[i][1], count + 1, visited, dungeons);
            visited[i] = false;
        }
    }
    maxCount = max(maxCount, count);
}

int solution(int k, vector<vector<int>> dungeons) {
    vector<bool> visited(dungeons.size(),false);
    bfs(k, 0, visited, dungeons);
    return maxCount;
}
