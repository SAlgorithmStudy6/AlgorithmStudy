import java.io.*
import java.util.*

lateinit var graph: ArrayList<ArrayList<Pair<Int,Int>>>
lateinit var minDistance: IntArray
lateinit var visited : BooleanArray

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, v, e) = readLine().trim().split(" ").map { it.toInt() }
    val (a, b) = readLine().trim().split(" ").map { it.toInt() }

    val houses = IntArray(n)
    var token = StringTokenizer(readLine())

    repeat(n) { houses[it] = token.nextToken().toInt() }

    graph = ArrayList()

    for (i in 0..v) {
        graph.add(ArrayList())
    }

    repeat(e) {
        token = StringTokenizer(readLine())
        val start = token.nextToken().toInt()
        val end = token.nextToken().toInt()
        val distance = token.nextToken().toInt()
        graph[start].add(Pair(end,distance))
        graph[end].add(Pair(start,distance))
    }

    var answer = 0

    for (house in houses){
        minDistance = IntArray(v+1){Integer.MAX_VALUE}
        visited = BooleanArray(v+1)
        dijkstarta(house)

        for (i in 1 until minDistance.size){
            if (minDistance[i] == Integer.MAX_VALUE) minDistance[i] = -1
        }

        answer += minDistance[a] + minDistance[b]
    }

    println(answer)

    bw.flush()
    bw.close()
}

fun dijkstarta(start:Int){
    val q = PriorityQueue<Pair<Int,Int>>(compareBy { it.second })

    q.add(Pair(start,0))
    minDistance[start] = 0

    while (q.isNotEmpty()){
        val (now,distance) = q.poll()
        if (minDistance[now] < distance) continue // 최소 거리를 만족 못하면 pass

        for (next in graph[now]){
            if (!visited[next.first]){
                val cost = distance + next.second
                if (cost < minDistance[next.first]){ // 다음 노드까지의 거리가 최소 거리일 경우
                    minDistance[next.first] = cost
                    q.add(Pair(next.first,cost))
                }
            }
        }
    }
}
