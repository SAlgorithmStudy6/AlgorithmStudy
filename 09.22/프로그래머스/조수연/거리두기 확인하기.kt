package com.ssafy.algorithm

import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val sl = Solution()
    val places = arrayOf(
        arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
        arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
        arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
        arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
        arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP")
    )
    println(sl.solution(places))
}

data class Point(val x: Int, val y: Int)

class Solution {
    lateinit var visited : BooleanArray
    lateinit var points : ArrayList<Point>
    lateinit var copy : Array<CharArray>
    fun solution(places: Array<Array<String>>): IntArray {

        var answer = IntArray(5)
        var index = 0

        for (place in places) {
            copy = Array(5){CharArray(5)}
            points = ArrayList()
            var result = 1

            for (i in place.indices) {
                for (j in place[i].indices) {
                    copy[i][j] = place[i][j]
                    if (place[i][j] == 'P') points.add(Point(i,j))
                }
            }

            visited = BooleanArray(points.size)
            var idx = 0

            for (i in place.indices) {
                for (j in place[i].indices) {
                    if (place[i][j] == 'P') {
                        visited[idx] = true
                        idx++
                        if (!check(i,j)){
                            result = 0
                            break
                        }
                    }
                }
                if (result == 0){
                    break
                }
            }

            answer[index] = result
            index++
        }
        return answer
    }

    fun check(row : Int, col : Int) : Boolean{ //맨허튼 거리로 거리두기 지키는지 확인
        for (i in points.indices){
            if (!visited[i]){
                val distance = Math.abs(row - points[i].x) + Math.abs(col - points[i].y) //맨허튼 거리
                if (distance == 1) return false
                else if (distance == 2){
                    if (!isBlcok(row,col,points[i].x,points[i].y)) return false
                }
            }
        }
        return true
    }

    fun isBlcok(row1 : Int, col1 : Int, row2 : Int, col2 : Int) : Boolean { //파티션으로 막혀있는지 판단
        if (row1 - row2 < 0 && col1 - col2 < 0){ //기준에서 P가 오른쪽 아래에 있을 때
            if (copy[row1+1][col1] != 'X' || copy[row1][col1+1] != 'X') return false
        }else if(row1 - row2 < 0 && col1 - col2 > 0) { //기준에서 P가 왼쪽 아래에 있을 때
            if (copy[row1+1][col1] != 'X' || copy[row1][col1-1] != 'X') return false
        }else if(row1 - row2 < 0 && col1 -  col2 == 0){ //기준에서 P가 아래쪽에 있을 때
            if (copy[row1+1][col1] != 'X') return false
        }else if(row1 - row2 == 0 && col1 - col2 < 0) { //기준에서 P가 오른쪽에 있을 때
            if (copy[row1][col1+1] != 'X') return false
        }
        return true
    }
}


