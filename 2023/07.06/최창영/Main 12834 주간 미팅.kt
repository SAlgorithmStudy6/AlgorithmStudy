package BOJ_12834_주간_미팅

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

/*
    장소를 노드로 생각.
    연결된 도로를 간선으로 생각. -> 그래프로 구성


    예외 : KIST나 씨알푸드로 갈 수 없는 경우에는 -1로 처리한다.
 */

// input
private lateinit var br: BufferedReader

// vairables
private const val INF = Int.MAX_VALUE
private var N = 0
private var V = 0
private var E = 0
private var A = 0
private var B = 0

private lateinit var adjList: MutableList<MutableList<Node>>
private lateinit var homes: IntArray

private data class Node(var num: Int, var weight: Int)


fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\BOJ_12834_주간_미팅\\res\\12834.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    input()

    var ans = 0
    ans += dijkstra(A)
    ans += dijkstra(B)

    sb.append(ans)
    bw.write(sb.toString())
    bw.close()
} // End of main

private fun dijkstra(startNodeNum: Int): Int {
    val comp = Comparator<Node> { o1, o2 ->
        o1.weight - o2.weight
    }
    val pque = PriorityQueue(comp)
    val dist = IntArray(V + 1) { INF }

    dist[startNodeNum] = 0
    pque.offer(Node(startNodeNum, 0))

    for (i in generateSequence {}) {
        if (pque.isEmpty()) break

        val poll = pque.poll()

        adjList[poll.num].forEach { nextNode ->
            if (dist[nextNode.num] > dist[poll.num] + nextNode.weight) {
                dist[nextNode.num] = dist[poll.num] + nextNode.weight
                pque.offer(Node(nextNode.num, dist[nextNode.num]))
            }
        }
    }

    var sum = 0
    homes.forEach {
        val temp = dist[it]
        when (temp) {
            INF -> {
                sum--
            }

            else -> {
                sum += temp
            }
        }
    }

    return sum
} // End of dijkstra

private fun input() {
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt() // 팀원의 수
    V = st.nextToken().toInt() // 장소의 수 (노드)
    E = st.nextToken().toInt() // 도로의 수 (간선)

    adjList = ArrayList()
    repeat(V + 1) {
        adjList.add(ArrayList())
    }

    st = StringTokenizer(br.readLine())
    A = st.nextToken().toInt()
    B = st.nextToken().toInt()

    homes = IntArray(N)
    st = StringTokenizer(br.readLine())
    for (i in 0 until N) {
        homes[i] = st.nextToken().toInt()
    }

    repeat(E) {
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val l = st.nextToken().toInt()

        adjList[a].add(Node(b, l))
        adjList[b].add(Node(a, l))
    }
} // End of input
