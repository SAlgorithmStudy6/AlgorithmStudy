import java.util.Stack;
import java.util.StringTokenizer;

public class PRG_표_편집 {
    public static void main(String[] args) {
        PRG_표_편집 s = new PRG_표_편집();

        int n = 8;
        int k = 2;
        String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        System.out.println(s.solution(n, k, cmd));
    } // End of main

    public String solution(int n, int k, String[] cmd) {
        StringTokenizer st;
        StringBuilder sb;

        Stack<Integer> backupList = new Stack<>();
        int rowMax_num = n;
        int cmd_len = cmd.length;

        for (int i = 0; i < cmd_len; i++) {
            st = new StringTokenizer(cmd[i]);
            String order = st.nextToken();

            if (order.equals("D") || order.equals("U")) {
                int X = Integer.parseInt(st.nextToken());

                if (order.equals("D")) {
                    k += X;
                } else if (order.equals("U")) {
                    k -= X;
                }
            } else if (order.equals("C")) {
                backupList.push(k);
                rowMax_num--;

                if (k == rowMax_num) {
                    k--;
                }
            } else if (order.equals("Z")) {
                if (backupList.pop() <= k) {
                    k++;
                }

                rowMax_num++;
            }
        }

        sb = new StringBuilder();
        for (int i = 0; i < rowMax_num; i++) {
            sb.append('O');
        }

        while (!backupList.isEmpty()) {
            sb.insert(backupList.pop().intValue(), 'X');
        }

        return sb.toString();
    } // End of solution
} // End of Solution class
