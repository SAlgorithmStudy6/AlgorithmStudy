class Test {
    public static void main(String[] args) {
        int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
        int k = 3;
        new Solution().solution(stones, k);
    }
}

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int min = 1;
        int max = 200000000;

        while (min <= max) {
            int mid = (min + max) / 2;

            if (canCross(k, mid, stones)) { // 해당 인원이 다리를 건널 수 있으면 최소 인원을 증가시켜 다시 탐색
                min = mid + 1;
                answer = Math.max(answer, mid);
            } else { // 해당 인원이 다리를 못 건너는 경우 최대 인원을 감소시켜 다시 탐색
                max = mid - 1;
            }
        }
        return answer;
    }

    public boolean canCross(int k, int people, int[] stones) {
        int cnt = 0;

        for (int stone : stones) {
            if (stone < people) { // 디딤돌의 갯수가 건너는 사람 수보다 작다 -> 건너뛰는 시작점
                cnt++; // 건너뛰는 칸수 갱신
            } else { // 시작점 이후에 디딤돌의 갯수가 건너는 사람 수보다 많으면 건너뛰는 칸수 다시 0으로 초기화
                cnt = 0;
            }

            if (cnt == k) return false; // 최대 칸수를 넘으면 종료
        }
        return true;
    }
}