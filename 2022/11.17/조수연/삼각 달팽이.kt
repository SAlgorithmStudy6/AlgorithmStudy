package com.android.example.kotlinproject

import java.util.*

fun main(){
    val n = 8
    println(Solution().solution(n))
}

class Solution {
    fun solution(n: Int): IntArray {

        val arr = Array(n) { IntArray(n) }

        var value = 1 //배열에 넣어줄 값
        var direction = 1 //방향
        var rowMax = n - 1 //마지막 행
        var colMax = n - 1 //마지막 열
        var rowMin = 0 //시작 행
        var rowCount = 0 //현재 행
        var colCount = 0 //현재 열


        var sum = 0
        for (i in 1 until n) { //총 몇개의 value까지 가는지 계산
            sum += i
        }
        if (n == 1) return intArrayOf(1) // n = 1이면 {1}
        else {
            while (true) {
                if (value > n + sum) break //마지막 요소 할당 끝나면 break

                when (direction) {
                    1 -> { //아래 방향
                        if (rowCount == rowMax) { //마지막 행에 도달했을 경우 방향 전환
                            arr[rowCount][colCount] = value
                            colCount++
                            direction = 2
                            rowMin++
                        } else {
                            arr[rowCount][colCount] = value
                            rowCount++
                        }
                    }
                    2 -> { //오른쪽방향
                        if (colCount == colMax) { //마지막 열에 도달했을 경우 방향 전환
                            arr[rowCount][colCount] = value
                            direction = 3
                            colCount--
                            rowCount--
                            colMax--
                        } else {
                            arr[rowCount][colCount] = value
                            colCount++
                        }
                    }
                    3 -> { //왼쪽 위 대각선 방향
                        if (rowCount == rowMin) { //시작행에 도달했을 때 방향전환
                            arr[rowCount][colCount] = value
                            direction = 1
                            rowCount++
                            colMax--
                            rowMax--
                            rowMin++
                        } else {
                            arr[rowCount][colCount] = value
                            rowCount--
                            colCount--
                        }
                    }
                }
                value++
            }
        }

        var result = arr.flatMap { it.filter { it > 0 } } //2차원배열을 일차원으로 바꾼후 0인 value를 제외하고 flattern
        return result.toIntArray()
    }
}