#include <string>
#include <vector>
#include <cmath>

using namespace std;

long long getNext(long long n) {
    if (n == 0) return 1;
    int index = 0;
    long long tmp = n;
    while (tmp) {
        if (tmp %2 == 0) { //중간에 0이 있으면 0 -> 1 / 이전 1 -> 0
            if (index > 0) {
                n -= pow(2, index - 1);
            }
            return (n + pow(2, index));
        }
        tmp /= 2;
        index++;
    }
    n += pow(2, index); //1로만 채워져있으면 그 앞 비트 0->1 그 뒤 비트 0->1
    n -= pow(2, index - 1);
    return n;
}

vector<long long> solution(vector<long long> numbers) {
    vector<long long> answer;
    for (long long n : numbers) {
        answer.push_back(getNext(n));
    }
    return answer;
}
