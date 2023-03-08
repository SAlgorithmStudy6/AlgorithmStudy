import ssafy.Solution_tree;

import java.util.*;
import java.io.*;

class Solution {

    HashMap<Long, Long> map = new HashMap<>();

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(10, new long[]{1, 3, 4, 1, 3, 1});
    }

    public long[] solution(long k, long[] room_number) {
        int size = room_number.length;
        long[] answer = new long[size];

        for (int i = 0; i < size; i++) {
            answer[i] = find(room_number[i]);
        }

        return answer;
    }

    private long find(long roomNum) {
        // 방이 없다면 1을 추가해서 다음 방으로 갈 수 있게 한다
        if (!map.containsKey(roomNum)) {
            map.put(roomNum, roomNum + 1);
            return roomNum;
        }

        // 방에 이미 누가 있다. 다른 호수를 찾아본다
        long findRoom = map.get(roomNum);
        long emptyRoom = find(findRoom);
        
        map.put(roomNum, emptyRoom);
        return emptyRoom;
    }
}



