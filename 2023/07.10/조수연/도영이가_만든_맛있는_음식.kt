import java.io.*
import java.util.StringTokenizer
import kotlin.math.*

lateinit var taste: Array<IntArray>
lateinit var checked: BooleanArray
var answer = Integer.MAX_VALUE

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = readLine().toInt()
    taste = Array(n) { IntArray(2) }
    checked = BooleanArray(n)

    repeat(n) {
        val token = StringTokenizer(readLine())
        taste[it][0] = token.nextToken().toInt()
        taste[it][1] = token.nextToken().toInt()
    }

    repeat(n) {
        minDiff(0, it + 1, 0, 1, 0)
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}

fun minDiff(size: Int, maxSize: Int, start: Int, sourness: Int, bitterness: Int) {
    if (answer == 0) return

    if (size == maxSize) {
        answer = min(answer, abs(sourness - bitterness))
        return
    }

    for (i in start until taste.size) {
        minDiff(size + 1, maxSize, i + 1, sourness * taste[i][0], bitterness + taste[i][1])
    }
}