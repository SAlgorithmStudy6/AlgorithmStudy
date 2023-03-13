import java.util.Stack;

public class 표편집 {
    public static void main(String[] args) {
        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};

        Solution s = new Solution();
        System.out.println(s.solution(n, k, cmd));
    }

    static class Solution {
        public String solution(int n, int k, String[] cmd) {
            int curNode = k;
            int lastNode = n - 1;
            StringBuilder sb = new StringBuilder();
            Stack<Integer> removedNode = new Stack<>();

            for (int i = 0; i < cmd.length; i++) {
                char command = cmd[i].charAt(0);

                if (command == 'U') {
                    curNode -= Integer.parseInt(cmd[i].substring(2));
                } else if (command == 'D') {
                    curNode += Integer.parseInt(cmd[i].substring(2));
                } else if (command == 'C') {
                    removedNode.add(curNode);
                    if (curNode == lastNode) {
                        curNode--;
                    }
                    lastNode--;
                } else if (command == 'Z') {
                    if(removedNode.pop() <= curNode){
                        curNode++;
                    }
                    lastNode++;
                }
            }

            for (int i = 0; i < lastNode+1; i++) {
                sb.append('O');
            }

            int size = removedNode.size();
            for (int i = 0; i < size; i++) {
                sb.insert(removedNode.pop().intValue(), 'X');
            }
            return sb.toString();
        }
    }
}
