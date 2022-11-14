package com.ssafy.algorithm

import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

data class Point(val r: Int, val c: Int)
lateinit var laboratory : Array<Array<String>>
lateinit var virusList : ArrayList<Point>
val direction = arrayOf(intArrayOf(-1,0), intArrayOf(1,0), intArrayOf(0,-1), intArrayOf(0,1))
var minTime = Integer.MAX_VALUE

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var token = StringTokenizer(br.readLine())
    val N = token.nextToken().toInt()
    val M = token.nextToken().toInt()

    laboratory = Array(N){Array (N){ "b" }}
    virusList = ArrayList()

    repeat(N){ row ->
        token = StringTokenizer(br.readLine())
        repeat(N){ col ->
            val info = token.nextToken().toInt()
            when(info){
                1 -> laboratory[row][col] = "-"
                2 -> {
                    laboratory[row][col] = "*"
                    virusList.add(Point(row,col))
                }
            }
        }
    }

    activate(0,0,M, activeArr = Array(M){Point(-1,-1)})
    if (minTime == Integer.MAX_VALUE) println(-1) else println(minTime)

}
//바이러스 활성화 브루트포스
fun activate(start : Int, size : Int, M : Int, activeArr : Array<Point>){
    if (size == M){
        spread(activeArr)
        return
    }

    for (i in start until virusList.size){
        activeArr[size] = virusList[i]
        activate(i+1,size+1,M,activeArr)
    }
}

fun spread(activeArr : Array<Point>){
    val tempLab = Array(laboratory.size){Array<String>(laboratory.size){""}}
    copyLab(tempLab)

    val queue : Queue<Point> = LinkedList()
    queue.addAll(activeArr)
    for (activated in activeArr){
        tempLab[activated.r][activated.c] = "0"
    }

    var time = 1
    var maxTime = 0
    while (queue.isNotEmpty()){
        for (i in 0 until queue.size){
            val point = queue.poll()
            for (i in direction.indices){
                val pointR = point.r + direction[i][0]
                val pointC = point.c + direction[i][1]

                if (pointR in laboratory.indices && pointC in laboratory.indices && tempLab[pointR][pointC] != "-"){
                    if (tempLab[pointR][pointC] == "b"){
                        tempLab[pointR][pointC] = "$time"
                        maxTime = time
                        queue.add(Point(pointR,pointC))
                    }else if(tempLab[pointR][pointC] == "*"){
                        tempLab[pointR][pointC] = "a"
                        queue.add(Point(pointR,pointC))
                    }
                }
            }
        }
        time++
    }

    if (checkSpread(tempLab)) minTime = min(minTime,maxTime)
}
//깊은 복사
fun copyLab(tempLab : Array<Array<String>>) : Array<Array<String>>{
    for (i in laboratory.indices){
        for (j in laboratory[i].indices){
            tempLab[i][j] = laboratory[i][j]
        }
    }
    return tempLab
}
//바이러스가 다 퍼졌는지 검사
fun checkSpread(tempLab : Array<Array<String>>) : Boolean{
    for (i in tempLab.indices){
        for (j in tempLab[i].indices)
        {
            if (tempLab[i][j] == "b") return false
        }
    }
    return true
}





