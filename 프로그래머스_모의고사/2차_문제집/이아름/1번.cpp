#include <string>
#include <vector>

using namespace std;

int solution(vector<int> number) {
    int answer = 0;
    int N = number.size();
    for(int i = 0;i<N;i++){
        for(int k = i+1;k<N;k++){
            for(int f = k+1;f<N;f++){
                if(number[i]+number[k]+number[f] == 0){
                    answer++;
                }
            }
        }
    }
    return answer;
}
