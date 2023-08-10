import java.io.*
import java.util.*
import kotlin.math.min

lateinit var st: StringTokenizer
const val MAX_VALUE = 1000000

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = readLine().toInt()
    val house = Array(n + 1) { IntArray(n + 1) }
    val dp = Array(n + 1) { IntArray(n + 1) }

    repeat(n) {
        st = StringTokenizer(readLine())
        house[it + 1][0] = st.nextToken().toInt()
        house[it + 1][1] = st.nextToken().toInt()
        house[it + 1][2] = st.nextToken().toInt()
    }

    var answer = MAX_VALUE

    for (i in 0 until 3) { // R -> G -> B순으로 선택
        for (j in 0 until 3) { // 선택한 색깔 집을 제외하고는 최댓값으로 갱신
            if (i == j) dp[1][j] = house[1][j]
            else dp[1][j] = MAX_VALUE
        }

        for (j in 2..n){
            dp[j][0] = house[j][0] + min(dp[j-1][1], dp[j-1][2])
            dp[j][1] = house[j][1] + min(dp[j-1][0], dp[j-1][2])
            dp[j][2] = house[j][2] + min(dp[j-1][0], dp[j-1][1])
        }

        for (j in 0 until 3){
            if (i != j) answer = min(answer, dp[n][j]) // n번이 1번에서 선택한 집이 아닐 때 최솟값 갱신
        }
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}