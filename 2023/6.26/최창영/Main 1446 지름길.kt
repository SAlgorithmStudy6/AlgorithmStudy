package 다익스트라_부수기

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

// input
private lateinit var br: BufferedReader

// variables
private var N = 0
private var D = 0
private const val INF = Int.MAX_VALUE

private lateinit var adjList: MutableList<MutableList<Road>>
private var dist = IntArray(10001) { INF }

private data class Road(var location: Int, var dist: Int) : Comparable<Road> {
    override fun compareTo(other: Road): Int {
        return dist - other.dist
    } // End of compareTo
} // End of Road class

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\다익스트라_부수기\\1446.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    input()

    dijkstra()
    sb.append(dist[D])

    bw.write(sb.toString())
    bw.close()
} // End of main

private fun dijkstra() {
    val pque = PriorityQueue<Road>()
    pque.offer(Road(0, 0))
    dist[0] = 0

    while (pque.isNotEmpty()) {
        val poll = pque.poll()

        if (poll.dist != dist[poll.location]) continue
        if(poll.location >= D) continue

        val size = adjList[poll.location].size
        for (i in 0 until size) {
            val nextRoad = adjList[poll.location][i]

            if (dist[nextRoad.location] > dist[poll.location] + nextRoad.dist) {
                dist[nextRoad.location] = dist[poll.location] + nextRoad.dist
                pque.offer(Road(nextRoad.location, dist[nextRoad.location]))
            }
        }
    }
} // End of dijkstra

private fun input() {
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt() // 지름길의 개수
    D = st.nextToken().toInt() // 고속도로의 길이

    adjList = ArrayList()
    for(i in 0 until D) {
        adjList.add(ArrayList())
        adjList[i].add(Road(i + 1, 1))
    }

    for (i in 0 until N) {
        st = StringTokenizer(br.readLine())
        val startLocation = st.nextToken().toInt() // u
        val endLocation = st.nextToken().toInt() // v
        val dist = st.nextToken().toInt() // w

        // 역주행 불가
        if (endLocation > D) continue

        // 원래 가는 길이보다 지름길의 길이가 같거나 큰 경우
        if (dist >= (endLocation - startLocation)) continue
        adjList[startLocation].add(Road(endLocation, dist))
    }

} // End of input
