#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> cards) {
    int answer = 0;
    for (int i = 0; i < cards.size(); i++) {
        bool visit[101] = { false };
        int cnt = 0;
        int n = i;
        while (!visit[n]) {
            visit[n] = true;
            n = cards[n]-1;
            cnt++;
        }
        for (int k = 0; k < cards.size(); k++) {
            bool visit2[101];
            copy(begin(visit), end(visit), visit2);
            if (!visit2[k]) {
                int cnt2 = 0;
                int n = k;
                while (!visit2[n]) {
                    visit2[n] = true;
                    n = cards[n]-1;
                    cnt2++;
                }
                answer = max(answer, cnt * cnt2);
            }
        }
    }
    return answer;
}

int main() {
    solution({ 8,6,3,7,2,5,1,4 });
}
