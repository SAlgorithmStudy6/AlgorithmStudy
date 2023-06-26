package DP_부수기

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter

// input
private lateinit var br: BufferedReader

// variables
private var N = 0
private var memo = IntArray(1_001)

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\DP_부수기\\res\\9656.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    input()
    memo[1] = 1
    memo[2] = 2
    memo[3] = 1


    DP()
    println(memo.contentToString())
    if (memo[N] % 2 == 0) {
        sb.append("SK")
    } else {
        sb.append("CY")
    }


    bw.write(sb.toString())
    bw.close()
} // End of main

private fun DP() {
    for (i in 4 .. N) {
        memo[i] = Math.min(memo[i - 1], memo[i - 3]) + 1
    }
} // End of DP

private fun input() {
    N = br.readLine().toInt()
} // End of input
