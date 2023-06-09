package 자료구조_부수기

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

/*
    석환이는 자연수가 쓰여진 카드를 N장 가지고 있다. 처음에 i번 카드엔 a
    만들 수 있는 가장 작은 점수
 */

// input
private lateinit var br: BufferedReader

// variables
private var N = 0 // 카드의 개수
private var M = 0 // 카드 합체 횟수

private var pq = PriorityQueue<Long>()

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\이분_탐색_부수기\\15903.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val sb = StringBuilder()

    input()

    for (i in 0 until M) {
        val a = pq.poll()
        val b = pq.poll()
        pq.offer(a + b)
        pq.offer(a + b)
    }

    var ans = 0L
    while (pq.isNotEmpty()) {
        ans += pq.poll()
    }

    sb.append(ans)
    bw.write(sb.toString())
    bw.close()
} // End of main

private fun input() {
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    for (i in 0 until N) {
        pq.offer(st.nextToken().toLong())
    }
} // End of input
