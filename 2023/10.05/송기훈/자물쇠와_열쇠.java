package programmers.lv3;

class Test {
    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println(new 자물쇠와_열쇠().solution(key, lock));
    }

}

public class 자물쇠와_열쇠 {

    int m, n, mLen;
    int[][] matrix;
    public boolean solution(int[][] key, int[][] lock) {
        m = key.length;
        n = lock.length;
        mLen = m * 2 + n - 2;
        matrix = new int[mLen][mLen];

        for (int i = m - 1; i < m + n - 1; i++) {
            for (int j = m - 1; j < m + n - 1; j++) {
                matrix[i][j] = lock[i - m + 1][j - m + 1];
            }
        }

        for (int i = 0; i < 4; i++) {
            if (check(key)) {
                return true;
            }
            rotate(key);
        }

        return false;
    }

    public void rotate(int[][] temp) {
        int[][] res = new int[m][m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = temp[m - j - 1][i];
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                temp[i][j] = res[i][j];
            }
        }
    }

    public boolean check(int[][] key) {

        for (int i = 0; i < mLen - m + 1; i++) {
            for (int j = 0; j < mLen - m + 1; j++) {

                for (int y = 0; y < m; y++) {
                    for (int x = 0; x < m; x++) {
                        matrix[i + y][j + x] += key[y][x];
                    }
                }

                boolean flag = true;
                for (int y = m - 1; y < m + n - 1; y++) {
                    for (int x = m - 1; x < m + n - 1; x++) {
                        if (matrix[y][x] != 1) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) break;
                }

                if (flag) return true;

                // 원상복구
                for (int y = 0; y < m; y++) {
                    for (int x = 0; x < m; x++) {
                        matrix[i + y][j + x] -= key[y][x];
                    }
                }
            }
        }

        return false;
    }

}
