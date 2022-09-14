package com.ssafy.algorithm

fun main() {
    val sl = Solution()
    val arr1 = arrayOf(intArrayOf(1,4), intArrayOf(3,2), intArrayOf(4,1))
    val arr2 = arrayOf(intArrayOf(3,3), intArrayOf(3,3))
    println(sl.solution(arr1,arr2))

}

class Solution {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        var answer = arrayOf<IntArray>()
        answer = Array(arr1.size){IntArray(arr2[0].size)}

        for (i in answer.indices){ 
            for (j in answer[i].indices){
                    for (k in arr2.indices) { // 3*2 2*2 일 때 행렬곱이 될려면 가운데 두 개의 수가 같아야 하므로 그 수만큼 반복
                        answer[i][j] += arr1[i][k] * arr2[k][j]
                    }
            }
        }

        return answer
    }
}


