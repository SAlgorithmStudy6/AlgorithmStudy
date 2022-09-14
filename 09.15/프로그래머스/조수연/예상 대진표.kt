package com.ssafy.algorithm

fun main() {
    val sl = Solution()
    val n = 8
    val a = 4
    val b = 5
    println(sl.solution(n,a,b))

}

class Solution {
    fun solution(n: Int, a: Int, b: Int): Int {
        var answer = 1
        var A = a
        var B = b
        while (true){
            if (Math.abs(A - B) == 1) { //둘의 위치가 1 차이가 날 때
                if (A > B) { //A가 더 클 경우 A의 위치가 2로 나눴을 때 나머지가 0 이면 종료
                    if (A % 2 == 0) break
                }else{ //B가 더 클 경우 A의 위치가 2로 나눴을 때 나머지가 0 이면 종료
                    if (B % 2 == 0) break
                }
            }
            answer++
            A = A / 2 + A % 2 //A의 다음 위치
            B = B / 2 + B % 2 //B의 다음 위치
        }
        return answer
    }
}


