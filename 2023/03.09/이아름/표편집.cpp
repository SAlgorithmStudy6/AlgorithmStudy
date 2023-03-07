#include <string>
#include <vector>
#include <stack>

using namespace std;

struct Node {
    int prev;
    int val;
    int next;
};

string solution(int n, int k, vector<string> cmd) {
    string answer = "";
    vector<Node> list; //l n r
    for (int i = 0; i < n; i++) {
        answer.push_back('O');
        list.push_back({ i-1, i, i + 1 });
    }
    int selected = k;
    stack<Node> st;
    for (string str : cmd) {
        if (str[0] == 'U' || str[0] == 'D') {
            int x = stoi(str.substr(2));
            for (int i = 0; i < x; i++) {
                if (str[0] == 'U') {
                    selected = list[selected].prev;
                }
                else {
                    selected = list[selected].next;
                }
            }
        }
        else if (str[0] == 'C') {
            int l = list[selected].prev;
            int r = list[selected].next;
            st.push(list[selected]);
            answer[selected] = 'X';
            if (l >= 0) {
                list[l].next = r;
            }
            if (r < n) {
                list[r].prev = l;
            }
            if (r >= n) {
                selected = l;
            }
            else {
                selected = r;
            }
        }
        else {
            Node num = st.top(); st.pop();
            int l = num.prev;
            int r = num.next;
            if (l >= 0) {
                list[l].next = num.val;
            }
            if (r < n) {
                list[r].prev = num.val;
            }
            answer[num.val] = 'O';
        }
    }
    return answer;
}
