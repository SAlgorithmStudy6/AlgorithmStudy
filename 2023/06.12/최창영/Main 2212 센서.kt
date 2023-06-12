package 그리디_부수기

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

/*
    고속도로 위에 최대 K개의 집중국을 세울 수 있다고 한다.
    N개의 센서가 적어도 하나의 집중국과는 통신이 가능해야 하며, 집중국의 유지비 문제로 인해 각 집중국의 수신 가능 영역의 길이의 합을 최소화해야 한다.


    첫째 줄에 문제에서 설명한 최대 K개의 집중국의 수신 가능 영역의 길이의 합의 최솟값을 출력한다.
 */


// input
private lateinit var br: BufferedReader

// variables
private var N = 0 // 센서의 개수 N
private var K = 0 // 집중국의 개수 K
private lateinit var sensorArr: IntArray

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\그리디_부수기\\2212.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    input()

    val diffArr = IntArray(N - 1)
    for (i in 0 until N - 1) {
        diffArr[i] = sensorArr[i + 1] - sensorArr[i]
    }

    diffArr.sortDescending()

    var ans = 0
    for (i in K - 1 until N - 1) {
        ans += diffArr[i]
    }

    sb.append(ans)

    bw.write(sb.toString())
    bw.close()
} // End of main

private fun input() {
    N = br.readLine().toInt()
    K = br.readLine().toInt()

    sensorArr = IntArray(N)

    val st = StringTokenizer(br.readLine())
    for (i in 0 until N) {
        val tmp = st.nextToken().toInt()
        sensorArr[i] = tmp
    }

    sensorArr.sort()
} // End of input