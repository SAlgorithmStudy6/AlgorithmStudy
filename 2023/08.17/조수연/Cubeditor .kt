import java.io.*
import kotlin.math.max

lateinit var table: IntArray

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val str = readLine()
    val n = str.length
    var answer = 0

    for (i in 0 until n) {
        val pattern = str.substring(i, n)
        answer = max(answer, makeTable(pattern))
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}

fun makeTable(pattern: String): Int {
    var idx = 0
    var answer = 0
    val len = pattern.length
    table = IntArray(len)

    for (i in 1 until len) {
        while (idx > 0 && pattern[i] != pattern[idx]) {
            idx = table[idx - 1]
        }

        if (pattern[i] == pattern[idx]) {
            table[i] = ++idx
            answer = max(answer, table[i])
        }
    }
    return answer
}