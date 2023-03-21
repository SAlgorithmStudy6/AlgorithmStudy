public class 단어변환 {

    public static void main(String[] args) {
        String begin = "hit";
        String target = "hhh";
        String[] words = {"hhh", "hht"};

        Solution s = new Solution();
        System.out.println(s.solution(begin, target, words));
    }

    static class Solution {
        static int answer;

        public int solution(String begin, String target, String[] words) {
            answer = Integer.MAX_VALUE;

            for (int i = 0; i < words.length; i++) {
                if (words[i].equals(target)) {
                    getResult(0, begin, target, words, new boolean[words.length]);
                    break;
                }
            }

            if (answer == Integer.MAX_VALUE) {
                answer = 0;
            }

            return answer;
        }

        static void getResult(int cnt, String curWord, String target, String[] words, boolean[] vis) {
            if (curWord.equals(target)) {
                answer = Math.min(answer, cnt);
                return;
            }

            for (int i = 0; i < curWord.length(); i++) {
                String curTemp = curWord.substring(0, i) + curWord.substring(i + 1);
                int check = 0;
                for (int j = 0; j < words.length; j++) {
                    if (i == 1 && j == 1) {
                    }
                    if (!vis[j]) {
                        check++;
                        String wordTemp = words[j].substring(0,i) + words[j].substring(i+1);

                        if (curTemp.equals(wordTemp)) {
                            vis[j] = true;
                            getResult(cnt + 1, words[j], target, words, vis);
                            vis[j] = false;
                        }
                    }
                }
                if (check == 0) {
                    return;
                }
            }
        }
    }
}
