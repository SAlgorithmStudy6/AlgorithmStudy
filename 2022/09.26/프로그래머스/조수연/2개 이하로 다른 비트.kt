package com.example.test

import java.util.*


fun main() {
    val sl = Solution()
    val numbers = longArrayOf(99999999999999)
    println(sl.solution(numbers))
}

class Solution {
    fun solution(numbers: LongArray): LongArray {
        var answer = LongArray(numbers.size)
        for (i in numbers.indices) {
            var number = changeBinary(numbers[i]) //다음 숫자 찾기
            answer[i] = number //정답에 해당 숫자 넣어주기

        }
        //println(Arrays.toString(answer))
        return answer
    }


    fun changeBinary(num: Long): Long { //이진 코드로 변환

        if(num % 2 == 0L || num == 1L) return num+1

        val binary = java.lang.Long.toBinaryString(num) // Long형 정수를 이진수로 변환
        val sb = StringBuilder(binary)

        if (sb.indexOf('0') < 0) sb.insert(0, '0') // 이진수가 1로만 구성되어있으면 비교 문자길이가 다르므로 1추가

        for (i in binary.length - 1 downTo 0) { //뒤에서 부터 검사해서 0인 지점을 찾아서 해당 0을 1로 바꾸고 다음 위치는 0으로 바꿈
            if (sb[i] == '0') {
                sb.setCharAt(i, '1')
                if (i != binary.length - 1) { //마지막 위치라면 그냥 1로만 변경하면됨
                    sb.setCharAt(i + 1, '0')
                }
                break
            }
        }
        return java.lang.Long.parseLong(sb.toString(), 2)
    }
}