#include <string>
#include <vector>
#include <map>

using namespace std;

int solution(vector<int> topping) {
    int answer = 0;
    map<int,int> total;
    for(int n : topping){
        total[n]++;
    }
    map<int,int> right;
    for(int n : topping){
        total[n]--;
        right[n]++;
        if(total[n] == 0){
            total.erase(n);
        }
        if(total.size() == right.size()){
            answer++;
        }
    }

    return answer;
}
