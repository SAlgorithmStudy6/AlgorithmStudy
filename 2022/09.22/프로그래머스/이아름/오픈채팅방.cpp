#include <string>
#include <vector>
#include <map>
#include <sstream>

using namespace std;

vector<string> solution(vector<string> record) {
	vector<string> answer;
	vector<pair<int,string>> logs; //0 : Enter, 1: Leave
	map<string, string> nickname;
	for (string str : record) {
		istringstream iss(str);
		string type, id, nck;
		iss >> type >> id >> nck;
		if (type.compare("Enter") == 0) {
			logs.push_back({ 0,id });
			nickname[id] = nck;
		}
		else if (type.compare("Leave") == 0) {
			logs.push_back({ 1,id });
		}
		else { //chagne
			nickname[id] = nck;
		}
	}
	for (auto log : logs) {
		string nck = nickname[log.second];
		if (log.first) { //leave
			answer.push_back(nck + "님이 나갔습니다.");
		}
		else { //enter
			answer.push_back(nck + "님이 들어왔습니다.");
		}
	}
	return answer;
}
