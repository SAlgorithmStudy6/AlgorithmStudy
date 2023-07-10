package BOJ_14618_총깡총깡

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

/*
    어디에 살던 상관이 없는 경우 -1을 출력한다.

    집의 종류를 말한 뒤에 거리를 출력한다.
    A를 출력한 뒤 다음줄에 거리를 출력 B형 집에서만 진서의 집에 갈 수 있는 경우 B를 출력한 뒤 다음 줄에 거리를 출력, 둘다 진서의 집에 갈 수 없는 경우 -1을 출력
 */

// input
private lateinit var br: BufferedReader

// variables
private const val INF = Int.MAX_VALUE
private var N = 0
private var M = 0
private var J = 0
private var K = 0

private lateinit var houseTypeA: IntArray
private lateinit var houseTypeB: IntArray
private lateinit var adjList: MutableList<MutableList<Node>>
private lateinit var dist: IntArray

private data class Node(var num: Int, var weight: Int)

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\BOJ_14618_총깡총깡\\res\\14618.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    input()

    dijkstra(J)

    var aMin = INF
    var bMin = INF
    houseTypeA.forEach { houseNum ->
        val d = dist[houseNum]
        aMin = Math.min(aMin, d)
    }

    houseTypeB.forEach { houseNum ->
        val d = dist[houseNum]
        bMin = Math.min(bMin, d)
    }


    // A형 집과 B형 집의 거리가 같으면 A형의 집에 산다
    if (aMin == INF && bMin == INF) {
        sb.append(-1)
    } else {
        if (aMin <= bMin) {
            sb.append('A').append('\n').append(aMin)
        } else {
            sb.append('B').append('\n').append(bMin)
        }
    }

    bw.write(sb.toString())
    bw.close()
} // End of main

private fun dijkstra(startNodeNum: Int) {
    val comp = Comparator<Node> { o1, o2 -> o1.weight - o2.weight }
    val pque = PriorityQueue(comp)
    val isVisited = BooleanArray(N + 1)

    pque.offer(Node(startNodeNum, 0))
    dist[startNodeNum] = 0

    for (i in generateSequence { }) {
        if (pque.isEmpty()) break

        val pollNode = pque.poll()
        if (isVisited[pollNode.num]) continue
        isVisited[pollNode.num] = true

        adjList[pollNode.num].forEach { nextNode ->
            val nextNum = nextNode.num
            val nextWeight = nextNode.weight

            if (dist[nextNum] > nextWeight + dist[pollNode.num]) {
                dist[nextNum] = nextWeight + dist[pollNode.num]
                pque.offer(Node(nextNum, dist[nextNum]))
            }
        }
    }
} // End of dijkstra

private fun input() {
    var st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt() // 집의 수
    M = st.nextToken().toInt() // 집과 집 사이의 도로 수
    J = br.readLine().toInt() // 진서의 집

    K = br.readLine().toInt() // 종류별 동물의 수
    houseTypeA = IntArray(K) // A 형의 집
    houseTypeB = IntArray(K) // B 형의 집

    st = StringTokenizer(br.readLine())
    for (i in 0 until K) {
        houseTypeA[i] = st.nextToken().toInt()
    }

    st = StringTokenizer(br.readLine())
    for (i in 0 until K) {
        houseTypeB[i] = st.nextToken().toInt()
    }

    adjList = ArrayList()
    repeat(N + 1) {
        adjList.add(ArrayList())
    }
    dist = IntArray(N + 1) { INF }

    for (i in 0 until M) {
        st = StringTokenizer(br.readLine())
        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()
        val z = st.nextToken().toInt()

        adjList[x].add(Node(y, z))
        adjList[y].add(Node(x, z))
    }
} // End of input
