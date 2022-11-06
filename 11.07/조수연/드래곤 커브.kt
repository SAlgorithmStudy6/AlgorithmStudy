package com.ssafy.algorithm

import java.io.*
import java.util.StringTokenizer

val direction = arrayOf(intArrayOf(1,0), intArrayOf(0,-1), intArrayOf(-1,0), intArrayOf(0,1)) //방향
var endX = 0
var endY = 0
lateinit var board : Array<IntArray>
var count = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val N = br.readLine().toInt()
    board = Array(101){IntArray(101)}
    var dList = ArrayList<Int>()

    repeat(N){
        val token = StringTokenizer(br.readLine())

        val x = token.nextToken().toInt()
        val y = token.nextToken().toInt()
        val d = token.nextToken().toInt()
        val g = token.nextToken().toInt()

        dList.clear()
        board[y][x] = 1

        for (i in 0 .. g){
            when(i){
                0 ->  {
                    dList.add(d)
                    endX = x + direction[d][0]
                    endY = y + direction[d][1]
                    board[endY][endX] = 1

                }
                1 ->{
                    val curveDirection =  if (d - 1 < 0) 3 else d - 1
                    dList.add(0,curveDirection)
                    val nextX = endX - direction[curveDirection][0]
                    val nextY = endY - direction[curveDirection][1]
                    endX = nextX
                    endY = nextY
                    board[endY][endX] = 1
                }else -> {
                    curve(dList)
                }
            }
        }
    }

    //정사각형 찾기
    for (i in 0..100){
        for (j in 0..100){
            if (board[i][j] == 1){
                square(j,i)
            }
        }
    }

    println(count)

}

//드래곤 커브
fun curve(dList : ArrayList<Int>){

    val tempList = ArrayList<Int>()
    val startIndex = dList.size

    for (i in dList.size-1 downTo  0){ //방향 추가해주기
        val curveDirection =  if (dList[i] - 1 < 0) 3 else dList[i] - 1
        tempList.add(curveDirection) //다음 세대 드래곤 커브 방향 추가
    }

    dList.addAll(0,tempList)

    for (i in startIndex-1 downTo 0){ //방향이 끝 점 기준으로 처음에는 나가는 방향 그 다음은 들어오는 방향 규칙
        if(i % 2 != 0){ //끝 점을 기준으로 나가는 방향
            val nextX = endX + direction[dList[i]][0]
            val nextY = endY + direction[dList[i]][1]
            endX = nextX
            endY = nextY
        }else{ //끝 점을 기준으로 들어오는 방향
            val nextX = endX - direction[dList[i]][0]
            val nextY = endY - direction[dList[i]][1]
            endX = nextX
            endY = nextY
        }
        board[endY][endX] = 1
    }
}

//4점이 정사각형인지 체크
fun square(x : Int, y : Int){
    if (x + 1 < 101 && y + 1 < 101){ //범위체크
        if (board[y][x+1] == 1 && board[y+1][x] == 1 && board[y+1][x+1] == 1) count++ //세점이 1일때
    }
}

