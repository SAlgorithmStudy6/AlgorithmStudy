package programmers.lv3;

//class Test {
//    public static void main(String[] args) {
//        int[][] beginning = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
//        int[][] target = {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
//
//        System.out.println(new 이차원_동전_뒤집기().solution(beginning, target));
//    }
//}


public class 이차원_동전_뒤집기 {

    int n, m;
    int answer = Integer.MAX_VALUE;
    int[][] beg;
    int[][] tar;

    public int solution(int[][] beginning, int[][] target) {
        n = beginning.length;
        m = beginning[0].length;
        beg = beginning;
        tar = target;

        dfs(0, 0);

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        return answer;
    }

    public void flip(int y) {
        for (int i = 0; i < m; i++) {
            if (beg[y][i] == 1) {
                beg[y][i] = 0;
            } else {
                beg[y][i] = 1;
            }
        }
    }

    public int check(int x) {
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (tar[i][x] == beg[i][x]) {
                result += 1;
            }
        }
        if (result == n) {
            return 0;
        } else if (result == 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public void dfs(int y, int flipCount) {
        if (n == y) {
            boolean flag = true;
            for (int i = 0; i < m; i++) {
                int result = check(i);
                if (result == -1) {
                    flag = false;
                    break;
                }
                flipCount += result;
            }

            if (flag) {
                answer = Math.min(answer, flipCount);
            }
            return;
        }

        flip(y);
        dfs(y + 1, flipCount + 1);

        // 원상 복구
        flip(y);
        dfs(y + 1, flipCount);

    }
}
