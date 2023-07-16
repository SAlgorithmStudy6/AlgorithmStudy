package BOJ_10728

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter

// input
private lateinit var br: BufferedReader
private lateinit var sb: StringBuilder

// variables
private var N = 0

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\BOJ_10728\\res.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    sb = StringBuilder()

    var T = br.readLine().toInt()
    while (T-- > 0) {
        input()

        solve()
    }

    bw.write(sb.toString())
    bw.close()
} // End of main

private fun solve() {
    var max = 0
    val resultList = mutableListOf<Int>()

    for (i in 1 until (1 shl N)) {
        val list = mutableListOf<Int>()

        for (j in 0 until N) {
            if ((1 shl j and i) != 0) {
                list.add(j + 1)
            }
        }

        val size = list.size
        if (check(list, size)) {

            if (size > max) {
                max = size
                resultList.clear()
                resultList.addAll(list)
            }
        }
    }

    sb.append(resultList.size).append('\n')
    resultList.forEach {
        sb.append(it).append(' ')
    }
    sb.append('\n')
} // End of solve

private fun check(list: List<Int>, size: Int): Boolean {
    for (i in list.indices) {
        for (j in i + 1 until size) {
            for (k in j + 1 until size) {
                if ((list[i] xor list[j] xor list[k]) == 0) return false
            }
        }
    }
    return true
} // End of check

private fun input() {
    N = br.readLine().toInt()
} // End of input
