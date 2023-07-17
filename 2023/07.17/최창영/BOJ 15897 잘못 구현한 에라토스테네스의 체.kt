package BOJ_15897

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter

// input
private lateinit var br: BufferedReader
private lateinit var sb: StringBuilder

// variables
private var N = 0L

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\BOJ_15897\\res.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    sb = StringBuilder()

    input()

    test()

    sb.append(solve())
    bw.write(sb.toString())
    bw.close()
} // End of main

private fun solve(): Long {
    var ans: Long = 1

    var i = 1
    var j = 0
    while(i < N){
        j = ((N - 1) % i / ((N - 1) / i) + 1).toInt()
        ans += ((N - 1) / i + 1) * j
        i += j
    }

    return ans
} // End of solve

private fun test() {
    var n = 1560
    var count = 0

    var arr = IntArray(n + 1)
    for(i in 1 .. n) {
        for(j in 1 .. n step i) {
            count++
            println("i : $i , j : $j , count : $count")
            arr[j] += 1
        }
    }
    println("count : $count")
    println(arr.contentToString())
} // End of test

private fun input() {
    N = br.readLine().toLong()
} // End of input
