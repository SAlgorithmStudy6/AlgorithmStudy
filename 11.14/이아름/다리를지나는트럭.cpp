#include <string>
#include <vector>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int answer = 0; //경과 시간
    int nowWeight = 0; //현재 다리의 무게
    vector<pair<int, int>> onbridge; //다리에 있는 트럭의 남은 시간, 무게
    int i = 0; //truck_weights index
    while (i < truck_weights.size() || !onbridge.empty()) {
        if (!onbridge.empty() && onbridge[0].first <= 0) { //맨앞 트럭 빼기
            nowWeight -= onbridge[0].second;
            onbridge.erase(onbridge.begin());
        }
        if (i < truck_weights.size() && nowWeight + truck_weights[i] <= weight) { //트럭 넣기
            nowWeight += truck_weights[i];
            onbridge.push_back({ bridge_length,truck_weights[i++] });
        }
        for (int k = 0; k < onbridge.size(); k++) {  //남은시간 하나씩 빼기
            onbridge[k].first -= 1;
        }
        answer++;
    }
    return answer;
}
