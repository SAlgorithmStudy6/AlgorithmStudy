package BOJ_13023_ABCDE

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

// input
private lateinit var br: BufferedReader
private lateinit var sb: StringBuilder

// variables
private var N = 0
private var M = 0

private lateinit var isVisited: BooleanArray
private lateinit var adjList: MutableList<MutableList<Int>>

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\BOJ_13023_ABCDE\\res.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    sb = StringBuilder()

    input()

    solve()

    bw.write(sb.toString())
    bw.close()
} // End of main

private fun solve() {
    for (i in 0 until N) {
        if (DFS(i, 0)) {
            sb.append(1)
            return
        }
    }

    sb.append(0)
} // End of sovle

private fun DFS(cur: Int, depth: Int): Boolean {
    if (depth == 4) return true
    isVisited[cur] = true
    var ret = false

    for (nextCur in adjList[cur]) {
        if (isVisited[nextCur]) continue
        if (DFS(nextCur, depth + 1)) {
            ret = true
            break
        }
    }

    isVisited[cur] = false
    return ret
} // End of DFS

private fun input() {
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()
    isVisited = BooleanArray(N)

    adjList = ArrayList()
    for (i in 0 until N) {
        adjList.add(ArrayList())
    }

    for (i in 0 until M) {
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()

        adjList[a].add(b)
        adjList[b].add(a)
    }
} // End of input
