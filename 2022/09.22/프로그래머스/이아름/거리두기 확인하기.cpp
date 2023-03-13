#include <string>
#include <vector>

using namespace std;
int dx[] = { 1,0,-1,0 };
int dy[] = { 0,1,0,1 };
int N = 5;

bool check(vector<string> &place) {
	for (int i = 0; i < place.size(); i++) {
		for (int k = 0; k < place[i].size(); k++) {
			if (place[i][k] == 'P') {
				for (int j = 0; j < 4; j++) {
					int x = i + dx[j];
					int y = k + dy[j];
					if (x >= 0 && x < N && y >= 0 && y < N) {
						if (place[x][y] == 'P') {
							return false;
						}
						else if (place[x][y] == 'O') {
							for (int f = 0; f < 4; f++) {
								int xx = x + dx[f];
								int yy = y + dy[f];
								if (xx >= 0 && xx < N && yy >= 0 && yy < N) {
									if (xx == x && yy == y) continue;
									if (xx == i && yy == k) continue;
									if (place[xx][yy] == 'P') {
										return false;
									}
								}
							}
						}
					}
				}
			}
		}
	}
	return true;
}

vector<int> solution(vector<vector<string>> places) {
	vector<int> answer;
	for (vector<string> place : places) {
		answer.push_back(check(place));
	}
	return answer;
}
