package com.ssafy.algorithm

import java.io.*
import java.util.*

private val direction = arrayOf(intArrayOf(0, 0), intArrayOf(0, 1), intArrayOf(0, -1), intArrayOf(-1, 0), intArrayOf(1, 0)) //방향 배열
private lateinit var map: Array<IntArray> //지도
private var dice = intArrayOf(0,0,0,0,0,0,0) //주사위

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var token = StringTokenizer(br.readLine())

    val N = token.nextToken().toInt()
    val M = token.nextToken().toInt()
    var y = token.nextToken().toInt() //현재 주사위 x좌표
    var x = token.nextToken().toInt() //현재 주사위 y좌표
    var K = token.nextToken().toInt()

    map = Array(N) { IntArray(M) }

    repeat(N) { row ->
        token = StringTokenizer(br.readLine())
        repeat(M) { col ->
            map[row][col] = token.nextToken().toInt()
        }
    }

    token = StringTokenizer(br.readLine())
    val commands = IntArray(K)

    repeat(K) {
        commands[it] = token.nextToken().toInt()
    }

    for (command in commands){ //명령 시작
        //정상범위일 때
        if (rangeCheck(x + direction[command][1], y + direction[command][0],N,M)){
            //주사위 위치 갱신
            x += direction[command][1]
            y += direction[command][0]

            dice = roll(command) //주사위 굴리기

            //맵에 숫자가 0이 아닐 때
            if (map[y][x] != 0){
                    dice[6] = map[y][x]
                    map[y][x] = 0
            }else{ //맵에 숫자가 0일 때때
                if (dice[6] != 0) map[y][x] = dice[6]
            }

            bw.write("${dice[1]}\n")
        }

   }
    bw.flush()
    bw.close()
}

//범위 체크
private fun rangeCheck(x : Int, y : Int, N : Int, M : Int) = x in 0 until M && y in 0 until N

//굴리기
private fun roll(command : Int) : IntArray {
    var changeDice = intArrayOf(0,0,0,0,0,0,0)
    when(command){
        //동
        1 -> {
            changeDice[1] = dice[4]
            changeDice[2] = dice[2]
            changeDice[3] = dice[1]
            changeDice[4] = dice[6]
            changeDice[5] = dice[5]
            changeDice[6] = dice[3]
        }
        //서
        2 -> {
            changeDice[1] = dice[3]
            changeDice[2] = dice[2]
            changeDice[3] = dice[6]
            changeDice[4] = dice[1]
            changeDice[5] = dice[5]
            changeDice[6] = dice[4]
        }
        //북
        3 -> {
            changeDice[1] = dice[5]
            changeDice[2] = dice[1]
            changeDice[3] = dice[3]
            changeDice[4] = dice[4]
            changeDice[5] = dice[6]
            changeDice[6] = dice[2]
        }
        //남
        4 -> {
            changeDice[1] = dice[2]
            changeDice[2] = dice[6]
            changeDice[3] = dice[3]
            changeDice[4] = dice[4]
            changeDice[5] = dice[1]
            changeDice[6] = dice[5]
        }
    }
    return changeDice
}