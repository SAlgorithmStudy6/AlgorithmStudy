package `DP 부수기`

import java.util.*
import java.io.*
import java.lang.StringBuilder

/*
    memoization을 활용하여 최적화,
    재귀로 구현 ->
    [동전의 가치][선택한 횟수(재귀의 깊이)]
    해당 깊이에서 해당 동전의 가치를 선택했을 경우에 만들어지는 금액, (가지 치기를 할 수 있다)

    ⭐ 이 문제는 재귀로는 풀 수 없는 문제이다 ⭐
    메모리 제한으로 인해서 2차원 배열을 만들 수 없기 때문에 재귀로는 풀 수 없는 문제이다
    따라서 Bottom Up의 방법으로만 문제를 풀 수 있다.
    memo[j - 동전의 가치]를 생각해내는게 이 문제의 포인트임
 */

private var N = 0
private var K = 0

// 동전을 선택한 횟수에서 만들어진 금액
//private val memo = Array(10_001) { IntArray(100_000) { -1 } }
private val memo = IntArray(10_001)
private val money = IntArray(101)

fun main() {
    val path = "C:\\Users\\Samsung\\Desktop\\코틀린 알고리즘\\src\\main\\kotlin\\DP 부수기\\res\\2293.txt"
    val br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val sb = StringBuilder()

    val st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt() // 동전의 개수
    K = st.nextToken().toInt() // 목표

    for (i in 1..N) {
        money[i] = br.readLine().toInt()
    }

    memo[0] = 1
    DP()
    sb.append(memo[K])

    bw.write(sb.toString())
    bw.close()
} // End of main

private fun DP() {
    for (i in 1..N) {
        for (j in 0..K) {
            if (j - money[i] >= 0) {
                memo[j] += memo[j - money[i]]
            }
        }
    }
} // End of DP
