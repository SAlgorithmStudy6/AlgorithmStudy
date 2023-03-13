fun main() {
    Solution().solution(k = 10, room_number = longArrayOf(1,4,5,6,3,1,8))
}

class Solution {
    val checkMap = HashMap<Long, Long>() // <현재 찾아볼 방, 찾아볼 다음 방>
    fun solution(k: Long, room_number: LongArray): LongArray {
        var answer = LongArray(room_number.size)
        for (i in room_number.indices) {
            answer[i] = bookRoom(room_number[i])
        }
        return answer
    }

    fun bookRoom(roomNumber : Long) : Long{
        if (!checkMap.containsKey(roomNumber)){ //해당 방이 비어있다면 해당 방 번호로 예약
            checkMap[roomNumber] = roomNumber + 1L
            return roomNumber
        }else{
            val nextRoomNumber = bookRoom(checkMap[roomNumber]!!) //다음 방 찾기 재귀 호출
            checkMap[roomNumber] = nextRoomNumber
            return nextRoomNumber
        }
    }
}