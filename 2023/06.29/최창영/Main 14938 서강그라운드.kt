package BOJ_서강그라운드

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

/*
    얻을 수 있는 최대 아이템의 개수
 */

// input
private lateinit var br: BufferedReader

// variables
private const val INF = Int.MAX_VALUE

private var N = 0
private var M = 0
private var R = 0
private var ans = 0

private lateinit var items: IntArray
private lateinit var adjList: MutableList<MutableList<Node>>

private data class Node(var num: Int, var weight: Int) : Comparable<Node> { // End of Node class
    override fun compareTo(other: Node): Int {
        return weight - other.weight
    } // End of weight
} // End of Node class

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\BOJ_서강그라운드\\res\\14938.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    input()

    for (i in 1..N) {
        ans = Math.max(dijkstra(startNode = i), ans)
    }

    sb.append(ans)
    bw.write(sb.toString())
    bw.close()
} // End of main

private fun dijkstra(startNode: Int): Int {
    val pque = PriorityQueue<Node>()
    val dist = IntArray(N + 1) { INF }
    val isVisited = BooleanArray(N + 1) { false }

    pque.offer(Node(startNode, 0))
    dist[startNode] = 0

    while (pque.isNotEmpty()) {
        val pollNode = pque.poll()

        if (isVisited[pollNode.num]) continue
        //isVisited[pollNode.num] = true

        adjList[pollNode.num].forEach { nextNode ->
            if (!isVisited[pollNode.num] && dist[nextNode.num] > dist[pollNode.num] + nextNode.weight) {
                pque.offer(Node(nextNode.num, nextNode.weight))
                dist[nextNode.num] = dist[pollNode.num] + nextNode.weight
            }
        }
    }

    var res = 0
    for (i in 1..N) {
        if (dist[i] <= M) {
            res += items[i]
        }
    }

    return res
} // End of dijkstra

private fun input() {
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt() // 지역의 개수
    M = st.nextToken().toInt() // 수색범위
    R = st.nextToken().toInt() // 길의 개수

    adjList = ArrayList()
    repeat(N + 1) {
        adjList.add(ArrayList())
    }
    items = IntArray(N + 1)

    st = StringTokenizer(br.readLine())
    for (i in 1..N) {
        items[i] = st.nextToken().toInt()
    }

    for (i in 0 until R) {
        st = StringTokenizer(br.readLine())

        val start = st.nextToken().toInt()
        val end = st.nextToken().toInt()
        val weight = st.nextToken().toInt()
        adjList[start].add(Node(end, weight))
        adjList[end].add(Node(start, weight))
    }
} // End of input
