import java.util.HashMap;
public class Problem {
    public static void main(String[] args) {
        new SolutionJava().solution(3, new long[]{1, 4, 5, 6, 3, 1, 8});
    }
}

class SolutionJava {
    static HashMap<Long,Long> checkMap = new HashMap<>();
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        for (int i = 0; i < room_number.length; i++) {
            answer[i] = bookRoom(room_number[i]);
        }
        return answer;
    }

    public long bookRoom(long roomNumber){
        if (!checkMap.containsKey(roomNumber)){ //해당 방이 비어있다면 해당 방 번호로 예약
            checkMap.put(roomNumber,roomNumber+1);
            return roomNumber;
        }else{
            long nextRoomNumber = bookRoom(checkMap.get(roomNumber));
            checkMap.put(roomNumber, nextRoomNumber);
            return nextRoomNumber;
        }
    }
}


