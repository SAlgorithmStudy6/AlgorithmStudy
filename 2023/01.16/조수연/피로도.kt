package com.ssafy.algorithm

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val sl = Solution()
    val k = 80
    val dungeons = arrayOf(intArrayOf(80, 20), intArrayOf(50, 40), intArrayOf(30, 10))
    println(sl.solution(k, dungeons))
}


class Solution {
    lateinit var visited: BooleanArray //중복 체크
    var max = Integer.MIN_VALUE
    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        var answer: Int = -1
        visited = BooleanArray(dungeons.size)
        combination(0, 0, k, dungeons)
        answer = max
        return answer
    }

    fun combination(size: Int, count: Int, k: Int, dungeons: Array<IntArray>) { //조합 뽑기
        if (size == visited.size) {
            max = Math.max(max, count)
            return
        }

        for (i in visited.indices) {
            if (k >= dungeons[i][0]) { //최소 피로도를 만족했을 때
                if (!visited[i]) {
                    visited[i] = true
                    combination(size + 1, count + 1, k - dungeons[i][1], dungeons)
                    visited[i] = false
                }
            }else{ //최소피로도를 만족하지 못하였을 때
                if (!visited[i]){
                    visited[i] = true
                    combination(size+1,count,k,dungeons)
                    visited[i] = false
                }
            }
        }
    }
}


