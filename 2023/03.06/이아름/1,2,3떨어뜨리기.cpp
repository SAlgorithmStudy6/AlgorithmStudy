#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;
vector<int> child[101];
int choice[101] = { 0 }; //연결되는 선
int counts[101] = { 0 }; //각 노드별 쌓이는 개수
int N;

int getChild() { //리프 노드 찾는 함수
    int answer = 1;
    while (child[answer].size() > 0) {
        int n = choice[answer];
        choice[answer]++;
        if (choice[answer] == child[answer].size()) {
            choice[answer] = 0;
        }
        answer = child[answer][n];
    }
    return answer;
}

int checkCount(vector<int>& target) { // 1 진행필요 // 2 모두 완료 // -1 불가능
    bool flag = true; //최소 카드 개수 구함
    for (int i = 1; i <= N; i++) {
        if (counts[i] > target[i - 1]) { //target이 불가능
            return -1;
        }
        if (target[i - 1] > counts[i] * 3) { //진행필요 - 더 많은 카드 필요
            flag = false;
        }
    }
    return flag ? 2 : 1;
}

vector<int> solution(vector<vector<int>> edges, vector<int> target) {
    vector<int> answer;
    N = target.size();
    sort(edges.begin(), edges.end()); //번호 작은 순서대로 정렬
    for (int i = 0; i < edges.size(); i++) {
        child[edges[i][0]].push_back(edges[i][1]);
    }
    //카드의 최소 개수 구하기
    int num = checkCount(target);
    while (num == 1) {
        counts[getChild()]++;
        num = checkCount(target);
    }
    if (num == -1) { //불가능한 target 거르기
        return { -1 };
    }
    else { //숫자 구하기
        vector<int> numbers[101]; //각 노드별 필요한 숫자
        int maxN = 0;
        for (int i = 1; i <= N; i++) { //각 노드별 필요 개수 확인
            int num = 1; //최소 카드 숫자
            int cnt = counts[i]; //필요 개수
            int n = target[i - 1]; //목표 카드 숫자 합
            maxN += cnt; //필요한 총 카드 개수
            while (cnt > 0) { //카드 숫자들 구하기
                while ((n-num+2)/3 >=cnt) {
                    num++;
                }
                numbers[i].push_back(num);
                cnt--;
                n -= num;
            }
        }
        //숫자 넣기
        for (int i = 0; i < N; i++) {
            choice[i] = 0;
        }
        int counts[101] = { 0 };
        for (int i = 0; i < maxN; i++) {
            int n = getChild();
            answer.push_back(numbers[n][counts[n]]);
            counts[n]++;
        }
    }
    return answer;
}
