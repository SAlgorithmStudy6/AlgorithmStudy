import java.io.*
import java.util.*
import kotlin.math.max

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var token = StringTokenizer(readLine())
    val n = token.nextToken().toInt()
    val m = token.nextToken().toInt()
    val r = token.nextToken().toInt()

    val maxValue = 1000000
    val items = IntArray(n + 1)
    val weight = Array(n + 1) { IntArray(n + 1) { maxValue } } // 이동거리에 대한 배열

    token = StringTokenizer(readLine())

    repeat(n) { items[it + 1] = token.nextToken().toInt() }
    repeat(n) { weight[it + 1][it + 1] = 0 } // 자기 자신에게 가는 번호는 없다고 했으니 0으로 초기화

    for (i in 1..r) {
        token = StringTokenizer(readLine())

        val start = token.nextToken().toInt()
        val end = token.nextToken().toInt()
        val distance = token.nextToken().toInt()

        weight[start][end] = distance
        weight[end][start] = distance
    }

    // 플로이드 워셜 알고리즘
    for (i in 1..n) {
        for (j in 1..n) {
            for (k in 1..n) {
                if (weight[j][i] + weight[i][k] < weight[j][k]) { // 1 -> 2 -> 3 거리 비용이 1 -> 3 보다 적으면 1 -> 2 -> 3 비용으로 갱신
                    weight[j][k] = weight[j][i] + weight[i][k]
                }
            }
        }
    }

    var answer = 0

    for (i in 1..n) {

        var itemCount = 0

        for (j in 1..n) {
            if (weight[i][j] <= m) {
                itemCount += items[j]
            }
        }
        answer = max(answer, itemCount)
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}
