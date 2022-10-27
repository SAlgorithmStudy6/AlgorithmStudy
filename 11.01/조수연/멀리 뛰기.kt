package com.ssafy.algorithm



fun main() {
    val sl = Solution()
    val n = 3
    println(sl.solution(n))

}

class Solution {
    var arr = LongArray(2001) //2000까지 초기화
    fun solution(n: Int): Long {
        dp(arr)
        return arr[n]
    }

    fun dp(arr : LongArray){ //dp로 인덱스 값 넣어주기
        arr[1] = 1
        arr[2] = 2

        for (i in 3 until arr.size){
            arr[i] = (arr[i-1] + arr[i-2]) % 1234567 //인덱스가 커지면 값이 커지므로 미리 1234567로 나머지 연산을 실행
        }
    }
}



