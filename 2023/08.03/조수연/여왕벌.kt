import java.io.*
import java.util.*
import kotlin.math.max

lateinit var hive: Array<IntArray>

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (m, n) = readLine().trim().split(" ").map { it.toInt() }

    hive = Array(m) { IntArray(m)}

    var st: StringTokenizer

    repeat(n) {
        st = StringTokenizer(readLine())
        val zero = st.nextToken().toInt()
        val one = st.nextToken().toInt()
        val two = st.nextToken().toInt()

        growFirst(zero, one, two, m)
    }

    growSecond(m)

    val sb = StringBuilder()

    for (i in 0 until m) {
        for (j in 0 until m) {
            sb.append("${hive[i][j] + 1} ")
        }
        sb.append("\n")
    }

    bw.write("$sb")

    bw.flush()
    bw.close()
}

fun growFirst(zero: Int, one: Int, two: Int, m: Int) {
    var x = 0
    var y = m - 1

    repeat(zero) {
        if (y > 0) y--
        else x++
    }

    repeat(one) {
        hive[y][x] += 1
        if (y > 0) y--
        else x++
    }

    repeat(two) {
        hive[y][x] += 2
        if (y > 0) y--
        else x++
    }
}

fun growSecond(m: Int) {
    for (i in 1 until m) {
        for (j in 1 until m) {
            hive[i][j] = max(hive[i][j - 1], max(hive[i - 1][j - 1], hive[i - 1][j]))
        }
    }
}