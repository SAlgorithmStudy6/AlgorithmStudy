import java.util.Arrays;

public class 혼자놀기의달인 {
    static int[] cards = {8, 6, 3, 7, 2, 5, 1, 4};

    public static void main(String[] args) {
        Solution so = new Solution();
        System.out.println(so.solution(cards));
    }

    static class Solution {
        static int answer, group1, group2;
        static int[] vis;
        public int solution(int[] cards) {

            //나올 수 있는 최댓값을 먼저 구해줌 -> 안해주는 게 시간, 메모리 효율 더 좋음
//        int max = 0;
//        int[] copy = cards.clone();
//        Arrays.sort(copy);
//        if (copy.length % 2 == 0) {
//            max = copy[copy.length / 2 - 1] * copy[copy.length / 2 - 1];
//        } else {
//            max = copy[copy.length / 2] * copy[copy.length / 2 - 1];
//        }

            answer = 0;
            group1 = 1;
            group2 = 1;

            Loop1:
            for (int i = 0; i < cards.length; i++) {
                vis = new int[cards.length];
                getResult(i, cards, 1);
                if (group1 < cards.length -1) {
                    for (int j = 0; j < cards.length; j++) {
                        if (vis[j] == 0) {
                            int[] visCopy = vis.clone();    //방문 처리 복구시키기 위한 복사
                            getResult(j, cards, 2);
                            vis = visCopy.clone();
                            answer = Math.max(group1 * group2, answer);
//                        if (answer == max) { // 이미 답이 나올 수 있는 최댓값과 같으면 종료
//                            break Loop1;
//                        }
                        }
                    }
                } else if(group1 == cards.length -1) {    //group1의 값이 cards.length -1 과 같으면 length 값을 넣어줌
                    answer = Math.max(answer, cards.length-1);
                }
            }

            return answer;
        }

        static void getResult(int index, int[] cards, int group) {
            if (vis[index] != 0) {
                int cnt = 0;
                for (int i = 0; i < cards.length; i++) {
                    if (vis[i] == group) {
                        cnt++;
                    }
                }
                if (group == 1) {
                    group1 = cnt;
                } else {
                    group2 = cnt;
                }
                return;
            }
            vis[index] = group;
            getResult(cards[index] - 1, cards, group);
        }
    }

}
