package BOJ_1322_X와_K

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

// input
private lateinit var br: BufferedReader

// variables
private var X = 0L
private var K = 0L

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\BOJ_1322_X와_K\\1322.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    input()

    X = X.inv()
    var j: Long = 0
    var ans: Long = 0

    for (i in 0 until 64) {
        if ((1L shl i) and X != 0L) {
            if ((1L shl j.toInt()) and K != 0L)
                ans += (1L shl i)

            j++
        }
    }

    sb.append(ans)
    bw.write(sb.toString())
    bw.close()
} // End of main

private fun input() {
    val st = StringTokenizer(br.readLine())
    X = st.nextToken().toLong()
    K = st.nextToken().toLong()
} // End of input
