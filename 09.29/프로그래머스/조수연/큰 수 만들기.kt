package com.android.example.kotlinproject

import java.lang.StringBuilder

fun main() {
    val number = "4321"
    val k = 1
    println(Solution().solution(number, k))

}

class Solution {
    fun solution(number: String, k: Int): String {
        val sb = StringBuilder(number)
        var count = k
        var start = 0
        var index = 1 //현재 인덱스
        var compare = 0 //비교 인덱스
        while (true) {
            if (count == 0) break

            if (index == sb.length) { //내림차순 정렬 되어있을 때 가장 작은 수 제거
                sb.deleteCharAt(sb.indexOf(sb.minOrNull()!!))
                count--
            }
            else{
                if (sb[start] == '9') start++ //9로 시작하면 시작점 패스
                else{
                    if (sb[compare] < sb[index]) { //비교 인덱스보다 현재 인덱스가 큰 경우 비교 인덱스 삭제
                        sb.deleteCharAt(compare)
                        count--
                        compare = start
                        index = start+1
                    }else{
                        compare++
                        index++
                    }
                }
            }
        }
        return sb.toString()
    }
}