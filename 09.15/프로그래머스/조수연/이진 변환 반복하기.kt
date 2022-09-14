package com.ssafy.algorithm

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val sl = Solution()
    val S = "1111111"
    println(Arrays.toString(sl.solution(S)))

}

class Solution {
    fun solution(s: String): IntArray {
        var str = s
        var changeCount = 0 //변환 횟수
        var removeCount = 0 //제거 횟수

        while (true) {
            if (str == "1") break

            for (binary in str) {
                if (binary == '0') removeCount++
            }

            str = str.replace("0", "")

            changeCount++

            str = changeBinary(str)

        }

        var answer = IntArray(2)
        answer[0] = changeCount
        answer[1] = removeCount

        return answer
    }

    fun changeBinary(str: String): String { //문자열의 길이를 이진수로 변환
        val strLength = str.length
        return Integer.toBinaryString(strLength)
    }
}



