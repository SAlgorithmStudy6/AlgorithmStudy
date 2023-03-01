import java.util.*;

public class PRG_이중우선순위큐 {
    static TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    public static void main(String[] args) {
        PRG_이중우선순위큐 s = new PRG_이중우선순위큐();
        String[] arr = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        System.out.println(Arrays.toString(s.solution(arr)));
    } // End of main

    public int[] solution(String[] operations) {

        StringTokenizer st;
        int len = operations.length;
        for (int i = 0; i < len; i++) {
            st = new StringTokenizer(operations[i]);
            String fun = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            if (fun.equals("I")) {
                treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
            } else if(fun.equals("D") && num == 1 && !treeMap.isEmpty()) {
                // 최댓값 삭제
                int maxNum = treeMap.lastKey();
                int numCount = treeMap.get(maxNum);
                if(numCount == 1) {
                    treeMap.remove(maxNum);
                } else {
                    treeMap.put(maxNum, numCount - 1);
                }
            } else if(fun.equals("D") && num == -1 && !treeMap.isEmpty()){
                // 최솟값 삭제
                int minNum = treeMap.firstKey();
                int numCount = treeMap.get(minNum);
                if(numCount == 1) {
                    treeMap.remove(minNum);
                } else {
                    treeMap.put(minNum, numCount - 1);
                }
            }
        }

        int[] ans;
        if(treeMap.isEmpty()) {
            ans = new int[] {0, 0};
        } else {
            ans = new int[] {treeMap.lastKey(), treeMap.firstKey()};
        }

        return ans;
    } // End of solution
} // End of Main class
