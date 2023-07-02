package 문자열_부수기

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter

/*
    두 문자열에 모두 포함된 가장 긴 공통 부분 문자열을 찾는 프로그램을 작성하라.
 */

// input
private lateinit var br: BufferedReader

// variables
private var str = ""
private var str2 = ""

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\문자열_부수기\\5582.txt"
    br = BufferedReader(File(path).bufferedReader())
    val sb = StringBuilder()
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    input()
    var ans = 0
    val len1 = str.length
    val len2 = str2.length
    val memo = Array(len1 + 2) { IntArray(len2 + 2) }

    for (i in 1..len1) {
        for (j in 1..len2) {
            if (str[i - 1] == str2[j - 1]) {
                // 글자가 같으면 다음 부분의 배열 위치에 같은 길이의 값을 저장한다.
                memo[i][j] = memo[i - 1][j - 1] + 1
                ans = Math.max(ans, memo[i][j])
            }
        }
    }

    memo.forEach {
        println(it.contentToString())
    }

    sb.append(ans)
    bw.write(sb.toString())
    bw.close()
} // End of main

private fun input() {
    str = br.readLine()
    str2 = br.readLine()
} // End of input
