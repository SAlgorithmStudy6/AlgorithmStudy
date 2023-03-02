public class NQueen {
    public static void main(String[] args) {
        int n = 4;
        Solution so = new Solution();
        System.out.println(so.solution(n));
    }

    static class Solution {
        static int answer;
        static int[] map;

        public int solution(int n) {
            answer = 0;
            map = new int[n];

            getResult(n, 0);
            return answer;
        }

        static void getResult(int n, int index) {
            if (index == n) {
                answer++;
                return;
            }
            for (int i = 0; i < n; i++) {
                map[index] = i;
                if(checkPossibility(index)){
                    getResult(n, index+1);
                }
            }
        }

        static boolean checkPossibility(int index) {
            for (int i = 0; i < index; i++) {
                //가로 체크 (index를 col 번호로 지정한 순간 같은 열에는 존재할 수 없으므로 세로는 체크 안해도 됨 -> map[index] 에는 한 가지 값만 들어가므로 index 열에는 한 개의 Queen만 존재)
                if (map[index] == map[i]) {
                    return false;
                }
                //대각선 체크

                else if (Math.abs(i - index) == Math.abs(map[i] - map[index])) {
                    return false;
                }
            }
            return true;
        }
    }

}
