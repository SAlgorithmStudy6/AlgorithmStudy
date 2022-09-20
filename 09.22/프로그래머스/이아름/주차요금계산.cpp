#include <string>
#include <vector>
#include <map>
#include <sstream>
#include <algorithm>
#include <cmath>

using namespace std;

vector<int> solution(vector<int> fees, vector<string> records) {
	vector<int> answer;
	map<string, int> times;
	map<string, int> parking;
	for (string str : records) {
		istringstream iss(str);
		string t, number, type;
		iss >> t >> number >> type;
		int time = stoi(t.substr(0, 2)) * 60;
		time += stoi(t.substr(3, 2));			
		if (type.compare("IN") == 0) {
			parking[number] = time;
		}
		else {
			times[number] += (time - parking[number]);
			parking.erase(number);
		}
	}
	int time = 60 * 23 + 59;
	for (auto p : parking) {
		times[p.first] += time - p.second;
	}
	for (auto car : times) {
		int fee = fees[1] + ceil(max((double)(car.second - fees[0]),0.0) / fees[2])*fees[3];
		answer.push_back(fee);
	}
	return answer;
}
