import java.io.*
import java.util.*
import kotlin.collections.ArrayList

lateinit var bw: BufferedWriter
lateinit var expressList: ArrayList<String>
val op = arrayOf("+", "-", " ")

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    bw = BufferedWriter(OutputStreamWriter(System.out))

    val t = readLine().toInt()

    repeat(t) {
        val n = readLine().toInt()
        expressList = ArrayList()

        comb(n, 1, "1")
        expressList.sort()

        for (express in expressList){
            bw.write("$express\n")
        }

        bw.write("\n")
    }

    bw.flush()
    bw.close()
}

fun comb(n: Int, size: Int, expression: String) {
    if (size == n) {
        val ex = expression.replace(" ", "")
        if (calc(ex)) expressList.add(expression)
            return
    }

    for (i in 0 until 3) {
        comb(n, size + 1, expression + op[i] + (size + 1))
    }
}

fun calc(expression: String): Boolean {
    val st = StringTokenizer(expression, "-|+", true)
    var sum = st.nextToken().toInt()

    while (st.hasMoreTokens()) {
        val s = st.nextToken()

        when (s) {
            "+" -> sum += st.nextToken().toInt()
            "-" -> sum -= st.nextToken().toInt()
        }
    }
    return sum == 0
}