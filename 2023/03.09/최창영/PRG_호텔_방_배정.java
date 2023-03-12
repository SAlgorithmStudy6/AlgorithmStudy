import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PRG_호텔_방_배정 {
    private static Map<Long, Long> hashMap = new HashMap<>();

    public static void main(String[] args) {
        PRG_호텔_방_배정 s = new PRG_호텔_방_배정();

        long k = 10;
        long[] room_number = {1, 3, 4, 1, 3, 1};
        System.out.println(Arrays.toString(s.solution(k, room_number)));
    } // End of main

    public long[] solution(long k, long[] room_number) {
        int len = room_number.length;
        long[] ans = new long[len];

        for (int i = 0; i < len; i++) {
            ans[i] = unionFind(room_number[i]);
        }

        return ans;
    } // End of solution

    private static long unionFind(long room) {
        if(hashMap.containsKey(room)) {
            hashMap.put(room, unionFind(hashMap.get(room)));
        } else {
            hashMap.put(room, room + 1);
            return room;
        }

        return hashMap.get(room);
    } // End of unionFind
} // End of Solution class
