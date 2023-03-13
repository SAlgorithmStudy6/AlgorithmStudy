package com.ssafy.algorithm

fun main() {
    val sl = Solution()
    val n = 3
    val left : Long = 2
    val right : Long = 5
    println(sl.solution(n,left,right))
}

class Solution {
    fun solution(n: Int, left: Long, right: Long): IntArray {
        var answer: IntArray = intArrayOf()
        answer = IntArray((right - left).toInt() + 1)

        var index = 0
        for (i in left..right){
            val row = i / n //행
            val col = i % n //열
            var value = row+1 //시작하는 value 세팅
            if (col > row) value += col - row //열이 행보다 클 경우 value 값은 col - row 값을 더해준 값
            answer[index] = value.toInt()
            index++
        }

        return answer
    }
}


