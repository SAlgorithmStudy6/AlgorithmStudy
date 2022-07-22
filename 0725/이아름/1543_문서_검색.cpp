#include <iostream>
#include <string>
using namespace std;

int main() {
	string target, search; //target : 1번째 문서, search : 2번째 문서
	getline(cin, target); //공백포함 받기
	getline(cin, search);
	int answer = 0; //제출할 답
	for (int i = 0; i <= (target.size() - search.size()); i++) {
		if (target.size() < search.size()) break; //1번째 문서가 2번째 문서보다 짧을 경우
		string subtarget = target.substr(i, search.size());
		if (!subtarget.compare(search)) { //문서 이름 비교
			answer++;
			i += (search.size() - 1);
		}
	}
	cout << answer << endl;
	return 0;
}
