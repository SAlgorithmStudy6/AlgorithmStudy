import java.io.*
import java.util.*
import kotlin.system.exitProcess

lateinit var graph: ArrayList<ArrayList<Int>>
lateinit var visited: BooleanArray

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m) = readLine().trim().split(" ").map { it.toInt() }

    graph = ArrayList()

    for (i in 0 until n) {
        graph.add(ArrayList())
    }

    visited = BooleanArray(n)

    repeat(m) {
        val token = StringTokenizer(readLine())
        val s = token.nextToken().toInt()
        val e = token.nextToken().toInt()

        graph[s].add(e)
        graph[e].add(s)
    }

    for (i in 0 until n) {
        visited = BooleanArray(n)
        (dfs(i, 0))
    }

    bw.write("0")


    bw.flush()
    bw.close()
}

fun dfs(start: Int, depth: Int): Boolean {
    if (depth == 4) {
        print(1)
        exitProcess(0)
    }

    visited[start] = true

    for (next in graph[start]) {
        if (!visited[next]) {
            dfs(next, depth + 1)
            visited[next] = false
        }
    }

    return false
}