package DP_부수기

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

/*
    첫째 줄에 사용한 동전의 최소 개수를 출력한다.
    불가능한 경우에는 -1을 출력한다.
 */

// input
private lateinit var br: BufferedReader

// variables
private var N = 0
private var K = 0
private var coinArr = IntArray(101)

// 최솟값을 구하는 문제 -> 나올 수 있는 최댓값 보다 더 큰 값으로 초기화
private var memo = IntArray(10_001) { 10_001 }

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\DP_부수기\\res\\2294.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    input()

    DP()

    if (memo[K] == 10001) {
        sb.append(-1)
    } else {
        sb.append(memo[K])
    }

    bw.write(sb.toString())
    bw.close()
} // End of main

private fun DP() {
    for (i in 1..N) {
        val coin = coinArr[i]

        for (j in coin..K) {
            memo[j] = Math.min(memo[j], memo[j - coin] + 1)
        }
    }
} // End of DP

private fun input() {
    val st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    K = st.nextToken().toInt()

    for (i in 1..N) {
        coinArr[i] = br.readLine().toInt()
    }

    memo[0] = 0
} // End of input
