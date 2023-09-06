public class 등굣길 {
    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        int puddles[][] = {{2, 2}};

        Solution s = new Solution();

        System.out.println(s.solution(m, n, puddles));
    }

    static class Solution {

        public int solution(int m, int n, int[][] puddles) {
            int answer = 0;

            int[][] map = new int[n + 1][m + 1];

            for (int i = 0; i < puddles.length; i++) {
                for (int j = 0; j < puddles[i].length; j += 2) {
                    map[puddles[i][j + 1]][puddles[i][j]] = -1;
                }
            }

            map[1][1] = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (map[i][j] == -1) continue;
                    if (i == 1 && j == 1) continue;

                    int left = map[i][j - 1] == -1 ? 0 : map[i][j - 1];
                    int up = map[i - 1][j] == -1 ? 0 : map[i - 1][j];
                    map[i][j] = (left + up) % 1_000_000_007;
                }
            }

            answer = map[n][m];
            return answer;
        }
    }
}
