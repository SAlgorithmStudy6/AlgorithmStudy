import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Test {
    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        new Solution().solution(key, lock);
    }
}

class Solution {
    static int n;
    static int m;
    static int move; // key 이동거리

    public boolean solution(int[][] key, int[][] lock) {
        n = lock.length;
        m = key.length;
        move = m - 1;

        // 이동 거리는 자물쇠의 길이 + 열쇠의 길이 - 겹치는 부분(1)
        for (int x = 0; x < move + n; x++) {
            for (int y = 0; y < move + n; y++) {
                for (int r = 0; r < 4; r++) {
                    int[][] map = new int[n + m * 2][n + m * 2];

                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            map[i + move][j + move] = lock[i][j];
                        }
                    }

                    rotate(map, key, r, x, y);

                    if (check(map)) return true;
                }
            }
        }

        return false;
    }


    // 키를 회전한 후 map 배열에 더해준다.
    public void rotate(int[][] map, int[][] key, int degree, int x, int y) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                switch (degree) {
                    case 0:
                        map[y + i][x + j] += key[i][j];
                        break;
                    case 1:
                        map[y + i][x + j] += key[j][m - i - 1];
                        break;
                    case 2:
                        map[y + i][x + j] += key[m - i - 1][m - j - 1];
                        break;
                    case 3:
                        map[y + i][x + j] += key[m - j - 1][i];
                        break;
                }
            }
        }
    }

    public boolean check(int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // map 영역에 자물쇠에 값이 0이거나 2이면 열쇠가 맞지 않음
                if (map[move + i][move + j] != 1) return false;
            }
        }

        return true;
    }
}