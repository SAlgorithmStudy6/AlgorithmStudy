package com.ssafy.algorithm

import java.io.*
import java.util.*
import kotlin.collections.ArrayList

data class Point(val r: Int, val c: Int)

lateinit var field: Array<CharArray> //필드
val direction = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))
lateinit var visited: Array<BooleanArray>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    field = Array(12) { CharArray(6) }

    repeat(12) { r ->
        val rowInfo = br.readLine()
        rowInfo.forEachIndexed { c, info ->
            field[r][c] = info
        }
    }

    var combo = 0
    while (true) {
        var isPop = false
        for (i in field.indices) {
            for (j in field[i].indices) {
                var check = false
                if (field[i][j] != '.') {
                    visited = Array(12) { BooleanArray(6) }
                    check = pop(i, j, field[i][j])
                }
                if (check) isPop = true
            }
        }

        if (isPop) { //연쇄작용이 터지면
            combo++
            shiftDown()
        } else break
    }
    println(combo)
}

fun pop(startR: Int, startC: Int, target: Char): Boolean {
    var queue: Queue<Point> = LinkedList()
    val pointList = ArrayList<Point>() //터질 때의 점들의 좌표
    queue.offer(Point(startR, startC))
    visited[startR][startC] = true
    var count = 1

    while (queue.isNotEmpty()) {
        val point = queue.poll()

        for (i in direction.indices) {
            val r = point.r + direction[i][0]
            val c = point.c + direction[i][1]

            if (r in 0..11 && c in 0..5 && !visited[r][c] && field[r][c] == target) {
                visited[r][c] = true
                queue.add(Point(r, c))
                pointList.add(Point(r, c))
                count++
            }
        }
    }
    if (count >= 4) { //연쇄작용이 일어나면 초기좌표와 리스트에 좌표 .으로 갱신
        field[startR][startC] = '.'
        for (point in pointList) {
            field[point.r][point.c] = '.'
        }
        return true
    }
    return false
}

fun shiftDown() {
    repeat(6) { c ->
        val shiftList = Stack<Char>()
        repeat(12) { r ->
            if (field[r][c] != '.') {
                shiftList.push(field[r][c])
                field[r][c] = '.'
            }
        }
        var row = 11

        while (shiftList.isNotEmpty()) { //제일 밑에 행부터 채워주기
            field[row][c] = shiftList.pop()
            row--
        }
    }
}




