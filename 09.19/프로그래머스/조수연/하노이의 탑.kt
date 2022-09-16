package com.ssafy.algorithm

import kotlin.collections.ArrayList

fun main() {
    val sl = Solution()
    val n = 2
    println(sl.solution(n))
}

class Solution {
    lateinit var move : ArrayList<IntArray>
    fun solution(n: Int): Array<IntArray> {
        var answer = arrayOf<IntArray>()
        move = ArrayList()
        hanoi(n,1,3,2)
        answer = Array(move.size){IntArray(2)}
        for (i in move.indices){
            answer[i] = move[i]
        }
        return answer
    }

    fun hanoi(n : Int, start : Int, end : Int, sub : Int){ // 원판 이동
        if (n == 1){
            move.add(intArrayOf(start,end))
            return
        }

        hanoi(n-1,start,sub,end) //시작 지점에서 중간지점까지 이동
        move.add(intArrayOf(start,end))
        hanoi(n-1,sub,end,start) //중간 지점에서 끝 지점까지 이동
    }
}


