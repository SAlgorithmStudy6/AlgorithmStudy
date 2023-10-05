import java.util.*;

class Test {
    public static void main(String[] args) {
        int[][] beginning = {{0, 1, 0, 0, 0}, {1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 1, 0}};
        int[][] target = {{0, 0, 0, 1, 1}, {0, 0, 0, 0, 1}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 0, 1}};
        new Solution().solution(beginning, target);
    }
}

class Solution {
    class Coin {
        String state;
        int count;
        ArrayList<Integer> commandList;

        public Coin(String state, int count, ArrayList<Integer> commandList) {
            this.state = state;
            this.count = count;
            this.commandList = commandList;
        }
    }

    static int row;
    static int col;
    static StringBuilder beginSb;
    static StringBuilder targetSb;
    HashMap<String, Integer> stateMap;

    public int solution(int[][] beginning, int[][] target) {
        row = beginning.length;
        col = beginning[0].length;
        beginSb = new StringBuilder();
        targetSb = new StringBuilder();
        stateMap = new HashMap<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                beginSb.append(beginning[i][j]);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                targetSb.append(target[i][j]);
            }
        }

        return bfs();
    }

    public int bfs() {
        Queue<Coin> queue = new LinkedList<>();
        ArrayList<Integer> commandList = new ArrayList<>();

        stateMap.put(beginSb.toString(), 1);
        queue.add(new Coin(beginSb.toString(), 0, commandList));

        while (!queue.isEmpty()) {
            Coin now = queue.poll();

            if (now.state.equals(targetSb.toString())) return now.count;

            for (int i = 0; i < row + col; i++) {
                ArrayList<Integer> tempList = new ArrayList<>(now.commandList);

                if (tempList.contains(i)) continue; // 이미 실행한 명령이면 pass

                String change = reverseCoin(now.state, i);

                if (stateMap.containsKey(change)) continue; // 이미 나온 상태이면 pass

                stateMap.put(change, 1);
                tempList.add(i);
                queue.add(new Coin(change, now.count + 1, tempList));
            }
        }

        return -1;
    }

    public String reverseCoin(String state, int command) {
        StringBuilder changeSb = new StringBuilder();

        if (command < row) { // 행 뒤집기
            for (int i = 0; i < row * col; i++) {
                if (i >= command * col && i < (command + 1) * col) {
                    if (state.charAt(i) == '1') changeSb.append('0');
                    else changeSb.append('1');
                } else changeSb.append(state.charAt(i));
            }
        } else { // 열 뒤집기
            for (int i = 0; i < row * col; i++) {
                if (i % col == (command - 1) % col) {
                    if (state.charAt(i) == '1') changeSb.append('0');
                    else changeSb.append('1');
                } else changeSb.append(state.charAt(i));
            }
        }

        return changeSb.toString();
    }
}