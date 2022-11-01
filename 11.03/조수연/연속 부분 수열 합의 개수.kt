package com.ssafy.algorithm

fun main(){
    val elements = intArrayOf(7,9,1,1,4)
    println(Solution().solution(elements))
}

class Solution {

    lateinit var set : HashSet<Int> //숫자합 set
    lateinit var visited : BooleanArray
    var isComplete = false //다음 인덱스 넘어가는 체크 배열

    fun solution(elements: IntArray): Int {
        set = HashSet()
        visited = BooleanArray(elements.size)

        for (i in 1..elements.size){
            isComplete = false
            combination(0,0,0,i,0,elements)
        }

        return set.size
    }

    private fun combination(size : Int, start : Int,lastIndex : Int , max : Int, sum : Int, elements: IntArray){

        if (size == max){
            set.add(sum)
            isComplete = true
            return
        }

        //원형 수열이므로 마지막인덱스에서도 + size만큼 숫자 뽑기
        for (i in start until elements.size-1+max){
            if (size == 0) isComplete = false //연속된 수열이 아니면 break
            if (size == 0 && i == elements.size) break //마지막인덱스를 지나면 break

            if (!isComplete) combination(size+1,i+1,i,max,sum+elements[i%elements.size],elements)
            else break
        }
    }
}







