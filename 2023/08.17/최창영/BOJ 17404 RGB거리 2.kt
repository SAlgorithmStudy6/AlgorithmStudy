package BOJ_17404

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.min


// https://www.acmicpc.net/problem/17404
// input
private lateinit var br: BufferedReader

// variables
private const val INF = Int.MAX_VALUE / 4
private var N = 0
private lateinit var memo: Array<IntArray> // N번째의 색상별 최소비용
private lateinit var colors: Array<Color>

private data class Color(var red: Int = 0, var green: Int = 0, var blue: Int = 0)

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\BOJ_17404\\res.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    input()

    bw.write(solve())
    bw.close()
} // End of main()

private fun solve(): String {
    val sb = StringBuilder()

    sb.append(DP())
    return sb.toString()
} // End of solve()

private fun DP(): Int {
    var ans = INF

    repeat(3) { i ->
        when (i) {
            0 -> {
                memo[0][0] = colors[0].red
                memo[0][1] = INF
                memo[0][2] = INF
            }

            1 -> {
                memo[0][0] = INF
                memo[0][1] = colors[0].green
                memo[0][2] = INF
            }

            2 -> {
                memo[0][0] = INF
                memo[0][1] = INF
                memo[0][2] = colors[0].blue
            }
        }


        for (j in 1 until N) {
            memo[j][0] = min(memo[j - 1][1], memo[j - 1][2]) + colors[j].red
            memo[j][1] = min(memo[j - 1][0], memo[j - 1][2]) + colors[j].green
            memo[j][2] = min(memo[j - 1][0], memo[j - 1][1]) + colors[j].blue
        }

        for (j in 0 until 3) {
            if (i == j) continue
            ans = min(ans, memo[N - 1][j])
        }
    }

    return ans
} // End of DP()

private fun input() {
    N = br.readLine().toInt()

    memo = Array(N + 1) { IntArray(3) }
    colors = Array(N + 1) { Color(0, 0, 0) }

    colors = Array(N + 1) { Color() }
    for (i in 0 until N) {
        StringTokenizer(br.readLine()).run {
            colors[i] = Color(
                nextToken().toInt(),
                nextToken().toInt(),
                nextToken().toInt(),
            )
        }
    }
} // End of input()
