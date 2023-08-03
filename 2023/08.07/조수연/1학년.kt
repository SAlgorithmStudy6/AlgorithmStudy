import java.io.*
import java.util.*

lateinit var st: StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = readLine().toInt()
    val dp = Array(n) { LongArray(21) }
    val numArr = IntArray(n)

    st = StringTokenizer(readLine())

    repeat(n) {
        numArr[it] = st.nextToken().toInt()
    }

    dp[0][numArr[0]] = 1 // 초깃값

    var plus = 0
    var minus = 0

    for (i in 1 until n - 1) { // 등호(=)를 제외하고 덧셈과 뺄셈의 횟수는 n-2번
        for (j in 0..20) {
            if (dp[i - 1][j] != 0L) { // 계산하기 전의 값
                plus = j + numArr[i]
                minus = j - numArr[i]

                if (plus in 0..20) {
                    dp[i][plus] += dp[i - 1][j]
                }

                if (minus in 0..20) {
                    dp[i][minus] += dp[i - 1][j]
                }
            }
        }
    }

    bw.write("${dp[n - 2][numArr[n - 1]]}")

    bw.flush()
    bw.close()
}