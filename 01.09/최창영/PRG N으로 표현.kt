package `DP 부수기`

import java.io.*
import java.util.*

private var result = Int.MAX_VALUE
private var M = 0

fun main() {
    val N = 5
    val number = 12
    println(solution(N, number))

} // End of main

private fun solution(N: Int, number: Int): Int {
    M = N

    DFS(0, number, 0)

    if (result == Int.MAX_VALUE) {
        return -1
    }

    return result
} // End of solution

private fun DFS(depth: Int, number: Int, total: Int) {
    if (depth > 8) {
        return
    }

    if (number == total) {
        result = Math.min(depth, result)
        return
    }

    var temp = 0
    for (i in 0 until 8) {
        if (depth + i < 8) {
            temp = temp * 10 + M

            DFS(depth + i + 1, number, total + temp)
            DFS(depth + i + 1, number, total - temp)
            DFS(depth + i + 1, number, total / temp)
            DFS(depth + i + 1, number, total * temp)
        }
    }
} // End of DFS
