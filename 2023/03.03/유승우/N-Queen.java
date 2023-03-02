import java.util.*;

class Solution {

    static int size = 0;
    static int answer = 0;
    // index는 행, value는 열
    static int[] map;

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(4));
    }

    public int solution(int n) {
        size = n;
        map = new int[size];
        dfs(0);

        return answer;
    }

    private void dfs(int pos) {
        // 모든 행에 체스를 두었다.
        if (pos == size) {
            answer++;
            return;
        }
        for (int i = 0; i < size; i++) {
            // pos행, i열에 체스를 두었다.
            map[pos] = i;
            // 가로, 세로, 대각선검사를 통과하면 체스말을 둘 수 있음.
            if (check(pos)) {
                dfs(pos + 1);
            }
        }
    }

    private boolean check(int row) {
        // for문을 row 전까지 해서 그 행까지는 절대 못감 -> 자동으로 체크
        for (int i = 0; i < row; i++) {
            // 열을 체크한다. 안에 가지고 있는 value가 같다면 이미 그 열에는 체스말이 있음.
            if (map[row] == map[i]) return false;
                // 대각선을 체크한다. 가로의 거리와 세로의 거리가 같다 -> 대각선이다.
            else if (Math.abs(row - i) == Math.abs(map[row] - map[i])) return false;
        }
        return true;
    }
}