package DP_부수기

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.*


// 우선순위 큐
private var pque: PriorityQueue<Point> = PriorityQueue()
private var tempPque: PriorityQueue<Point> = PriorityQueue()


// 메모이제이션
private lateinit var memo: Array<Point>

private data class Point(
    var n: Int?,
    var c: Int?
) : Comparable<Point> {
    // Point 정렬 조건 -> c역순, n 정순으로 정렬
    override fun compareTo(other: Point): Int {
        if (c!! < other.c!!) {
            return 1
        } else if (c!! > other.c!!) {
            return -1
        } else {
            if (n!! > other.n!!) {
                return 1
            } else if (n!! < other.n!!) {
                return -1
            } else {
                return 0
            }
        }
    }
} // End of Point class

fun main() {
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val sb = StringBuilder()

    val e = 8
    val starts = intArrayOf(1, 3, 7)
    sb.append(solution(e, starts).contentToString())

    bw.write(sb.toString())
    bw.close()
} // End of main

private fun solution(e: Int, starts: IntArray): IntArray {
    val size = starts.size
    val result = IntArray(size)

    DP(e)

    val pqueSize = pque.size
    for (i in 0 until size) {
        val s = starts[i]

        tempPque.forEach {
            pque.offer(it)
        }
        tempPque.clear()

        for (j in 0 until pqueSize) {
            val temp = pque.poll()
            tempPque.offer(temp)

            if (temp.n!! >= s) {
                result[i] = temp.n!!
                break
            }
        }
    }

    return result
} // End of solution

private fun DP(e: Int) {
    memo = Array(e) { Point(null, null) }
    for (i in 1..e) {
        memo[i - 1] = Point(i, 1)
    }

    // 각 수의 약수 갯수 모두 구하기
    for (i in 2..e step 1) {
        for (j in 1..(e / i) step 1) {
            memo[(i * j) - 1].c = memo[(i * j) - 1].c!! + 1
        }
    }

    memo.forEach {
        pque.offer(it)
    }
} // End of DP
