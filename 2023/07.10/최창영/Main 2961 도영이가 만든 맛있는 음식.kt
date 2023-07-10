package BOJ_2961_도영이가_만든_맛있는_음식

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

// variables
private lateinit var br: BufferedReader

// input
private var N = 0
private var ans = Int.MAX_VALUE

private lateinit var arr: Array<Taste>
private lateinit var isVisited: BooleanArray
private var comb: MutableList<Taste> = ArrayList()

private data class Taste(var sour: Int, var bitter: Int)

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\BOJ_2961_도영이가_만든_맛있는_음식\\res\\2961.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    input()

    DFS(0, 0)

    sb.append(ans)
    bw.write(sb.toString())
    bw.close()
} // End of main

private fun DFS(depth: Int, idx: Int) {
    if (depth != 0) {
        var sSum = 1
        var bSum = 0
        comb.forEach {
            sSum *= it.sour
            bSum += it.bitter
        }

        ans = Math.min(ans, Math.abs(sSum - bSum))

        if (depth == N) {
            return
        }
    }

    for (i in idx until N) {
        if (isVisited[i]) continue

        isVisited[i] = true
        comb.add(arr[i])
        DFS(depth + 1, i)
        comb.removeAt(depth)
        isVisited[i] = false
    }
} // End of DFS

private fun input() {
    N = br.readLine().toInt()

    arr = Array(N) { Taste(0, 0) }
    isVisited = BooleanArray(N)

    for (i in 0 until N) {
        val st = StringTokenizer(br.readLine())
        arr[i].sour = st.nextToken().toInt()
        arr[i].bitter = st.nextToken().toInt()
    }
} // End of input
