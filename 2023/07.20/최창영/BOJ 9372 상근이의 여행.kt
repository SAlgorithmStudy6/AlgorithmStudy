package BOJ_9372

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

// input
private lateinit var br: BufferedReader
private lateinit var sb: StringBuilder

// variables
private const val INF = Int.MAX_VALUE
private var N = 0
private var M = 0

private lateinit var adjList: MutableList<MutableList<Int>>

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\BOJ_9372\\res.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    sb = StringBuilder()

    val t = br.readLine().toInt()
    repeat(t) {
        input()

        solve()
    }

    bw.write(sb.toString())
    bw.close()
} // End of main

private fun BFS(startNode: Int): Int {
    val isVisited = BooleanArray(N + 1)
    val que: Queue<Int> = LinkedList()

    que.offer(startNode)
    var result = 0
    isVisited[startNode] = true

    while (que.isNotEmpty()) {
        result++
        val poll = que.poll()

        adjList[poll].forEach { nextNode ->
            if (!isVisited[nextNode]) {
                que.offer(nextNode)
                isVisited[nextNode] = true
            }
        }
    }

    return result - 1
} // End of BFS

private fun solve() {
    sb.append(BFS(1)).append('\n')
} // End of solve

private fun input() {
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt()
    M = st.nextToken().toInt()

    adjList = ArrayList()
    repeat(N + 1) {
        adjList.add(ArrayList())
    }

    repeat(M) {
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()

        adjList[a].add(b)
        adjList[b].add(a)
    }
} // End of input