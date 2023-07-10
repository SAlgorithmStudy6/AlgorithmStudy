import java.io.*
import java.util.*
import kotlin.math.*

lateinit var graph: ArrayList<ArrayList<Pair<Int, Int>>>
lateinit var visited: BooleanArray
lateinit var dist: IntArray
lateinit var answer: IntArray

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    answer = IntArray(2) { Integer.MAX_VALUE }

    val (n, m) = readLine().trim().split(" ").map { it.toInt() }
    val j = readLine().toInt()
    val k = readLine().toInt()

    val a = IntArray(k)

    var token = StringTokenizer(readLine())
    repeat(k) {
        a[it] = token.nextToken().toInt()
    }

    val b = IntArray(k)

    token = StringTokenizer(readLine())
    repeat(k) {
        b[it] = token.nextToken().toInt()
    }

    graph = ArrayList()

    for (i in 0..n) {
        graph.add(ArrayList())
    }

    repeat(m) {
        val token = StringTokenizer(readLine())
        val s = token.nextToken().toInt()
        val e = token.nextToken().toInt()
        val d = token.nextToken().toInt()

        graph[s].add(Pair(e, d))
        graph[e].add(Pair(s, d))
    }

    dist = IntArray(n + 1) { Integer.MAX_VALUE }
    visited = BooleanArray(n + 1)
    dijkstra(j)

    for (i in a.indices) answer[0] = min(answer[0],dist[a[i]])
    for (i in b.indices) answer[1] = min(answer[1],dist[b[i]])

    if (answer[0] == Integer.MAX_VALUE && answer[1] == Integer.MAX_VALUE) bw.write("-1")
    else{
        if (answer[0] <= answer[1]) bw.write("A\n${answer[0]}")
        else bw.write("B\n${answer[1]}")
    }

    bw.flush()
    bw.close()
}

fun dijkstra(start: Int) {
    val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

    pq.offer(Pair(start, 0))
    dist[start] = 0

    while (pq.isNotEmpty()) {
        val (now, distance) = pq.poll()

        if (dist[now] < distance) continue

        for (next in graph[now]) {
            if (!visited[next.first]) {
                val cost = distance + next.second
                if (cost < dist[next.first]) {
                    dist[next.first] = cost
                    pq.offer(Pair(next.first, cost))
                }
            }
        }
    }
}