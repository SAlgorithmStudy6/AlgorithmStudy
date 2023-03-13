#include <iostream>
#include <vector>

using namespace std;
bool isBadTaste[300][300] = { false };

bool IsCompSuccess(int n1, int n2, int n3) {
	if (isBadTaste[n1][n2]) return false;
	if (isBadTaste[n2][n3]) return false;
	if (isBadTaste[n1][n3]) return false;
	return true;
}

int main() {
	int N, M;
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		int n1, n2; cin >> n1 >> n2;
		isBadTaste[n1][n2] = true;
		isBadTaste[n2][n1] = true;
	}
	int answer = 0;
	for (int i = 1; i <= N - 2; i++) { //1¹øÂ° ¸À
		for (int k = i + 1; k <= N - 1; k++) { //2¹øÂ° ¸À
			for (int j = k + 1; j <= N; j++) { //3¹øÂ° ¸À
				if (IsCompSuccess(i, k, j)) {
					answer++;
				}
			}
		}
	}
	cout << answer << endl;
	return 0;
}