package BOJ_1967_트리의_지름

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

/*
    트리는 양방향 그래프가 된다.
    그래프의 구조가 트리의 특성 -> 트리의 특성을 활용할 수 있다.
 */

// input
private lateinit var br: BufferedReader

// variables
private var N = 0
private lateinit var adjList: MutableList<MutableList<Node>>
private lateinit var isVisited: BooleanArray
private lateinit var dist: IntArray

data class Node(var num: Int = 0, var weight: Int = 0)

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\BOJ_1967_트리의_지름\\res\\1967.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    input()

    // 루트에서 시작해서 가장 먼 노드를 찾기
    var ans = 0
    ans = dijkstra(1)
    ans = dijkstra(dist.indexOf(ans))

    sb.append(ans)
    bw.write(sb.toString())
    bw.close()
} // End of main

private fun dijkstra(startNodeNum: Int): Int {
    val comp = Comparator<Node> { o1, o2 -> o1.weight - o2.weight }
    val que = PriorityQueue(comp)
    isVisited = BooleanArray(N + 1)
    dist = IntArray(N + 1) { -1 }

    dist[startNodeNum] = 0
    que.offer(Node(startNodeNum, 0))

    for (i in generateSequence { 0 }) {
        if (que.isEmpty()) break
        val pollNode = que.poll()

        if (isVisited[pollNode.num]) continue
        isVisited[pollNode.num] = true

        adjList[pollNode.num].forEach { nextNode ->

            // 다익스트라의 개념을 적용하되 가장짧은 거리로 최신화 하는 것이 아닌, 가장 먼 거리로 최신화를 함.
            if (!isVisited[nextNode.num] && dist[nextNode.num] < nextNode.weight + dist[pollNode.num]) {
                dist[nextNode.num] = nextNode.weight + dist[pollNode.num]
                que.offer(Node(nextNode.num, dist[nextNode.num]))
            }
        }
    }

    return dist.max()
} // End of dijkstra

fun input() {
    N = br.readLine().toInt()

    adjList = ArrayList()
    repeat(N + 1) {
        adjList.add(ArrayList())
    }

    repeat(N - 1) {
        val st = StringTokenizer(br.readLine())
        val parentNode = st.nextToken().toInt()
        val childNode = st.nextToken().toInt()
        val weight = st.nextToken().toInt()

        adjList[parentNode].add(Node(childNode, weight))
        adjList[childNode].add(Node(parentNode, weight))
    }
} // End of input
