#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;
vector<int> child[101];
int choice[101] = { 0 };
int counts[101] = { 0 };
int N;

int getChild() {
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
    bool flag = true;
    for (int i = 1; i <= N; i++) {
        if (counts[i] > target[i - 1]) {
            return -1;
        }
        if (target[i - 1] > counts[i] * 3) { //진행필요
            flag = false;
        }
    }
    return flag ? 2 : 1;
}

vector<int> solution(vector<vector<int>> edges, vector<int> target) {
    vector<int> answer;
    N = target.size();
    sort(edges.begin(), edges.end());
    for (int i = 0; i < edges.size(); i++) {
        child[edges[i][0]].push_back(edges[i][1]);
    }
    int num = checkCount(target);
    while (num == 1) {
        counts[getChild()]++;
        num = checkCount(target);
    }
    if (num == -1) {
        return { -1 };
    }
    else { //숫자 구하기
        vector<int> numbers[101];
        int maxN = 0;
        for (int i = 1; i <= N; i++) {
            int num = 1;
            int cnt = counts[i];
            int n = target[i - 1];
            maxN += cnt;
            while (cnt > 0) {
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
