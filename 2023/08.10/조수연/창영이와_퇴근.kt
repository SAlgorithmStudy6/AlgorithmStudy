import java.awt.Point
import java.io.*
import java.util.*
import kotlin.math.*

lateinit var st: StringTokenizer
lateinit var road: Array<IntArray>
val dir = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = readLine().toInt()

    if (n == 1) bw.write("0")
    else {
        road = Array(n) { IntArray(n) }

        var maxH = 0
        var minH = Integer.MAX_VALUE

        repeat(n) { i ->
            st = StringTokenizer(readLine())
            repeat(n) { j ->
                road[i][j] = st.nextToken().toInt()
                maxH = max(maxH, road[i][j])
                minH = min(minH, road[i][j])
            }
        }

        // 이분 탐색 (조건을 만족하면서 가장 높은 경사로 구하기)
        var minGrad = 0
        var maxGrad = maxH - minH

        while (minGrad <= maxGrad) {
            val midGrad = (minGrad + maxGrad) / 2

            if (dijkstra(n, midGrad)) maxGrad = midGrad - 1
            else minGrad = midGrad + 1
        }

        bw.write("$minGrad")
    }

    bw.flush()
    bw.close()
}

fun dijkstra(n: Int, baseGrad: Int): Boolean {
    val q: Queue<Point> = LinkedList()
    val visited = Array(n) { BooleanArray(n) }

    q.add(Point(0, 0))
    visited[0][0] = true

    while (q.isNotEmpty()) {
        val cur = q.poll()

        if (cur.x == n - 1 && cur.y == n - 1) return true

        for (i in 0 until 4) {
            val nx = cur.x + dir[i][1]
            val ny = cur.y + dir[i][0]

            if (nx in 0 until n && ny in 0 until n && !visited[ny][nx]) {
                if (abs(road[ny][nx] - road[cur.y][cur.x]) <= baseGrad) {
                    visited[ny][nx] = true
                    q.add(Point(nx, ny))
                }
            }
        }
    }
    return false
}