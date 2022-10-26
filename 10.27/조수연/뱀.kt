package com.ssafy.algorithm

import java.io.*
import java.util.*

data class Point(val row: Int, val col: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val N = br.readLine().toInt()
    val K = br.readLine().toInt()

    val snake = ArrayList<Point>()
    val board = Array(N + 1) { IntArray(N + 1) }

    repeat(K) { //사과위치 세팅
        val token = StringTokenizer(br.readLine())
        val r = token.nextToken().toInt()
        val c = token.nextToken().toInt()
        board[r][c] = 1
    }


    val L = br.readLine().toInt()
    val map = HashMap<Int, Char>()
    val rotation = arrayOf(intArrayOf(0, 1), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(-1, 0)) //회전 배열
    val queue: Queue<Int> = LinkedList()

    repeat(L) {
        val token = StringTokenizer(br.readLine())
        val X = token.nextToken().toInt()
        val C = token.nextToken()[0]
        map[X] = C
        queue.add(X)
    }

    snake.add(Point(1, 1)) //뱀의 위치 삽입
    var direction = 0 //처음에는 오른쪽으로 아동
    var time = 0

    while (true) {
        time++
        //범위를 벗어나면 종료
        if (snake.last().row + rotation[direction][0] !in 1..N || snake.last().col + rotation[direction][1] !in 1..N) break
        else {
            //이동했는데 자기몸과 만나면 종료
            if (snake.contains(Point(snake.last().row + rotation[direction][0], snake.last().col + rotation[direction][1]))) break
            else {
                val moveR = snake.last().row + rotation[direction][0]
                val moveC = snake.last().col + rotation[direction][1]

                //사과가 없으면
                if (board[moveR][moveC] != 1){
                    snake.removeAt(0)
                    snake.add(Point(moveR,moveC))
                }
                else { //사과가 있으면 사과자리는 0으로 초기화 및 뱀 길이는 증가
                    board[moveR][moveC] = 0
                    snake.add(Point(moveR, moveC))
                }
            }
        }

        if (queue.isNotEmpty()) {
            if (time == queue.peek()) { //X초 후 방향 전환하기
                when (map.getValue(queue.poll())) {
                    'L' -> if (direction - 1 < 0) direction = 3 else direction-- //왼쪽 90도
                    'D' -> if (direction + 1 > 3) direction = 0 else direction++ //오른쪽 90도
                }
            }
        }

    }
    println(time)
}

