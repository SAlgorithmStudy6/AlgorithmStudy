public class PRG_혼자_놀기의_달인 {
    static boolean[] isVisited = new boolean[101];
    static int[] cards = new int[101];

    public static void main(String[] args) {
        PRG_혼자_놀기의_달인 s = new PRG_혼자_놀기의_달인();
        int[] cards = {8, 6, 3, 7, 2, 5, 1, 4};
        System.out.println(s.solution(cards));
    } // End of main

    public int solution(int[] cards) {
        this.cards = cards;

        int group1 = 0;
        int group2 = 0;

        int size = cards.length;
        for (int i = 0; i < size; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;

                int count = DFS(i);

                if (count > group1) {
                    group2 = group1;
                    group1 = count;
                } else if(count > group2) {
                    group2 = count;
                }
            }
        }

        return group1 * group2;
    } // End of solution

    private static int DFS(int index) {
        int count = 1;

        int nextIdx = cards[index] - 1;

        if(isVisited[nextIdx] == false) {
            isVisited[nextIdx] = true;
            count = DFS(nextIdx) + 1;
        }

        return count;
    } // End of DFS
} // End of Main class
