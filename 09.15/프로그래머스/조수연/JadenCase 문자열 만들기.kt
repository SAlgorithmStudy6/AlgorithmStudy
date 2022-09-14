package com.ssafy.algorithm

fun main() {
    val sl = Solution()
    val S = "for the last week"
    println(sl.solution(S))

}

class Solution {
    fun solution(s: String): String {
        //문자열을 소문자로 바꾼 후 공백을 기준으로 나눠서 해당 문자들을 capitalize()함수를 통해 첫번째 문자만 바꾼 후 joinString()함수로 문자열 변환
        return s.lowercase().split(" ").map { it.capitalize() }.joinToString(" ")
    }
}



