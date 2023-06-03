package DP_부수기

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter

/*
    사자들을 우리에 가둘 때, 가로로도 세로로도 붙어 있게 배치할 수는 없다.
    우리가 2*N 배열에 사자를 배치하는 경우의 수가 몇 가지인지를 알아내는 프로그램을 작성해 주도록 하자.
    사자를 한 마리도 배치하지 않는 경우도 하나의 경우의 수로 친다고 가정한다.
 */

// input
private lateinit var br: BufferedReader

// variables
private var N = 0
private const val MOD = 9901
private lateinit var memo: Array<IntArray>


fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\DP_부수기\\res\\1309.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val sb = StringBuilder()

    input()

    for (i in 2..N) {
        memo[i][0] = (memo[i - 1][0] + memo[i - 1][1] + memo[i - 1][2])
        memo[i][1] = (memo[i - 1][0] + memo[i - 1][2])
        memo[i][2] = (memo[i - 1][0] + memo[i - 1][1])
        memo[i][0] %= MOD
        memo[i][1] %= MOD
        memo[i][2] %= MOD
    }

    val result = (memo[N][0] + memo[N][1] + memo[N][2]) % MOD
    sb.append(result).append('\n')

    bw.write(sb.toString())
    bw.close()
} // End of main

private fun input() {
    N = br.readLine().toInt()
    memo = Array(N + 1) { IntArray(3) { 1 } }

    // 기저 사례
    memo[1][0] = 1
    memo[1][1] = 1
    memo[1][2] = 1
} // End of input
