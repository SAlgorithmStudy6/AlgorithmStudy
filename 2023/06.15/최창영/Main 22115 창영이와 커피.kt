package DP_부수기

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

// input
private lateinit var br: BufferedReader

// variables
private var N = 0 //
private var K = 0
private var C = 0

private const val INF = Int.MAX_VALUE

private lateinit var caffeines: IntArray
private lateinit var memo: IntArray

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\DP_부수기\\res\\22115.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    input()

    println(memo[K])


    bw.write(sb.toString())
    bw.close()
} // End of main

private fun input() {
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    K = st.nextToken().toInt()

    caffeines = IntArray(N)
    st = StringTokenizer(br.readLine())

    memo = IntArray(K + 1) { INF }
    memo[0] = 0

    var index = 0
    repeat(N) {
        caffeines[index++] = st.nextToken().toInt()

        for (i in K downTo C) {
            Math.min(memo[i], memo[i - C] + 1)
        }
    }
} // End of input
