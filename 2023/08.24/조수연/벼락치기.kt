import java.io.*
import java.util.*
import kotlin.math.max

lateinit var st: StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, t) = readLine().split(" ").map { it.toInt() }
    val scores = Array(n + 1) { IntArray(2) }

    repeat(n) {
        st = StringTokenizer(readLine())
        scores[it + 1][0] = st.nextToken().toInt()
        scores[it + 1][1] = st.nextToken().toInt()
    }

    val dp = Array(n + 1) { IntArray(t + 1) }

    // knapsack
    for (i in 1..n) {
        for (j in 1..t) {
            if (scores[i][0] > j) {
                dp[i][j] = dp[i - 1][j]
            } else {
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - scores[i][0]] + scores[i][1])
            }
        }
    }

    bw.write("${dp[n][t]}")

    bw.flush()
    bw.close()
}