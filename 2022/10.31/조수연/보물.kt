package com.ssafy.algorithm

import java.io.*
import java.util.*
import kotlin.collections.ArrayList


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val N = br.readLine().toInt()

    val A = ArrayList<Int>()
    val B = ArrayList<Int>()

    repeat(2) { time ->
        val token = StringTokenizer(br.readLine())

        repeat(N) {
            when (time) {
                0 -> A.add(token.nextToken().toInt())
                1 -> B.add(token.nextToken().toInt())
            }
        }
    }

    var sum = 0

    //A의 가장 큰수 * B의 가장 작은 수 곱하기 및 체크배열로 체크해주기
    while (A.isNotEmpty()){
        sum += A.maxOrNull()!! * B.minOrNull()!!
        A.remove(A.maxOrNull())
        B.remove(B.minOrNull())
    }

    println(sum)
}




