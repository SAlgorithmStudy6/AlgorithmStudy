package com.example.test

import java.util.*

fun main() {
    val sl = Solution()
    val arr = arrayOf(
        intArrayOf(1, 1, 0, 0),
        intArrayOf(1, 0, 0, 0),
        intArrayOf(1, 0, 0, 1),
        intArrayOf(1, 1, 1, 1)
    )
    println(sl.solution(arr))
}

class Solution {
    lateinit var checked: Array<IntArray>
    fun solution(arr: Array<IntArray>): IntArray {
        var answer = IntArray(2)
        checked = Array(arr.size) { IntArray(arr.size) { 2 } }
        var size = 1
        while (true) {
            if (Math.pow(2.0, size.toDouble()).toInt() == arr.size) break
            size++
        }

        while (true) {
            if (size == 0) break
            for (i in arr.indices step Math.pow(2.0, size.toDouble()).toInt()) {
                for (j in arr.indices step Math.pow(2.0, size.toDouble()).toInt()) {
                    //println("$i $j")
                    if (checked[i][j] == 2) { //압축이 아직 안 된 영역일 경우
                        var oneFlag = false
                        var zeroFlag = false
                        for (a in i until i + Math.pow(2.0, size.toDouble()).toInt()) {
                            for (b in j until j + Math.pow(2.0, size.toDouble()).toInt()) {
                                if (arr[a][b] == 0) zeroFlag = true
                                else if (arr[a][b] == 1) oneFlag = true
                            }
                        }
                        if (!oneFlag && zeroFlag) { //0으로만 채워져 있는 경우
                            for (a in i until i + Math.pow(2.0, size.toDouble()).toInt()){
                                for (b in j until j + Math.pow(2.0, size.toDouble()).toInt()) {
                                    checked[a][b] = 0
                                }
                            }
                            answer[0]++ //압춛된 값 ++
                        } else if (oneFlag && !zeroFlag) { //1로만 채워져 있는 경우
                            for (a in i until i + Math.pow(2.0, size.toDouble()).toInt()){
                                for (b in j until j + Math.pow(2.0, size.toDouble()).toInt()) {
                                    checked[a][b] = 1
                                }
                            }
                            answer[1]++
                        }
                    }
                }
            }
            size--
        }

        for (i in checked.indices){ //0과 1 갯수 갱신
            for (j in checked[i].indices){
                if (checked[i][j] == 2){
                    answer[arr[i][j]]++
                }
            }
        }
        return answer
    }
}