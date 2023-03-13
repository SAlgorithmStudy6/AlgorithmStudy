import java.util.HashMap;
import java.util.Map;

public class 호텔방배정 {
    public static void main(String[] args) {
        Solution so = new Solution();
        long k = 10;
        long[] room_number = {1, 3, 4, 1, 3, 1};
        long[] answer = so.solution(k, room_number);
        for (int i = 0; i < answer.length; i++) {
            System.out.printf(answer[i] + " ");
        }
    }

    static class Solution {
        static long[] answer;
        static Map<Long, Long> room;

        public long[] solution(long k, long[] room_number) {
            answer = new long[room_number.length];
            room = new HashMap<>();

            for (int i = 0; i < answer.length; i++) {
                answer[i] = getResult(room_number[i]);
            }

            return answer;
        }

        static long getResult(long roomNum) {
            //방을 배정할 수 있는 경우
            if (!room.containsKey(roomNum)) {
                room.put(roomNum, roomNum+1);
                return roomNum;
            }

            //이미 방이 배정되어 있는 경우
            long nextRoom = room.get(roomNum);
            long emptyRoom = getResult(nextRoom);
            room.put(roomNum, emptyRoom);
            return emptyRoom;
        }
    }
}
