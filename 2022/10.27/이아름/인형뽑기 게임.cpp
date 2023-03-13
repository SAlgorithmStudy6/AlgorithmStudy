#include <string>
#include <vector>
#include <stack>

using namespace std;

int solution(vector<vector<int>> board, vector<int> moves) {
	int answer = 0;
	stack<int> st;
	st.push(0);
	for (int m : moves) { //이동
		for (int i = 0; i < board.size(); i++) { 
			if (board[i][m-1] != 0) { //가장 위에 있는 것 찾기
				if (st.top() == board[i][m-1]) { //stack top과 같으면
					st.pop();
					answer += 2;
				}
				else { //다르면 input
					st.push(board[i][m-1]);
				}
				board[i][m-1] = 0;
				break;
			}
		}
	}

	return answer;
}
