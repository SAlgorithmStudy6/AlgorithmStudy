#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

unordered_map<long long, long long> parent;
vector<long long> answer;

long long getParent(long long n) {
    if (parent[n] == 0ll) {
        return n;
    }
    return parent[n] = getParent(parent[n]);
}

vector<long long> solution(long long k, vector<long long> room_number) {
    for (long long n : room_number) {
        long long num = getParent(n);
        answer.push_back(num);
        parent[num] = num+1;
    }
    return answer;
}
