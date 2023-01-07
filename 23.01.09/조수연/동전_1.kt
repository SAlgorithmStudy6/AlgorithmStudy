package com.ssafy.algorithm

import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val token = StringTokenizer(br.readLine())
    val n = token.nextToken().toInt()
    val k = token.nextToken().toInt()

    val coins = IntArray(n) //동전 종류를 담은 배열

    repeat(n){
        coins[it] = br.readLine().toInt()
    }

    var counts = IntArray(k+1)
    counts[0] = 1 //0 크기의 경우의 수는 1

    for (i in 0 until n){
        val coin = coins[i] //현재 동전
        for (j in coin .. k){ //현재 동전 크기부터 k크기까지 경우의 수 구하기
            counts[j] += counts[j-coin] //j 크기가 되는 경우의 수는 j 크기의 경우의 수에서 현재 코인의 크기를 해당 크기만큼 뺀 경우의 수를 더해준다.
        }
    }

    println(counts[k])
}







