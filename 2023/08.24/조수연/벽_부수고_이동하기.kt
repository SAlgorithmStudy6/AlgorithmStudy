import java.awt.Point
import java.io.*
import java.util.*

lateinit var map: Array<IntArray>
lateinit var visited: Array<Array<BooleanArray>>
val dir = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m) = readLine().split(" ").map { it.toInt() }
    map = Array(n) { IntArray(m) }
    visited = Array(n) { Array(m) { BooleanArray(2) } }

    repeat(n) { i ->
        val info = readLine()
        repeat(m) { j ->
            map[i][j] = info[j].digitToInt()
        }
    }

    bw.write("${bfs(n, m)}")

    bw.flush()
    bw.close()
}

fun bfs(n: Int, m: Int): Int {
    val q: Queue<Triple<Point, Boolean, Int>> = LinkedList()

    q.add(Triple(Point(0, 0), false, 1))

    if (n == 1 && m == 1) return 1

    while (q.isNotEmpty()) {
        val cur = q.poll()

        if (cur.first.x == m - 1 && cur.first.y == n - 1) return cur.third

        for (i in 0 until 4) {
            val nx = cur.first.x + dir[i][1]
            val ny = cur.first.y + dir[i][0]

            if (nx in 0 until m && ny in 0 until n) {
                if (map[ny][nx] == 0) {
                    if (!cur.second && !visited[ny][nx][0]) { // 벽을 부순 적이 없는 경우
                        q.add(Triple(Point(nx, ny), false, cur.third + 1))
                        visited[ny][nx][0] = true
                    } else if (cur.second && !visited[ny][nx][1]) { // 벽을 한 번 부순 경우
                        q.add(Triple(Point(nx, ny), true, cur.third + 1))
                        visited[ny][nx][1] = true
                    }
                } else {
                    if (!cur.second){ // 벽을 만났는데 부순 적이 없는 경우
                        q.add(Triple(Point(nx,ny),true,cur.third + 1))
                        visited[ny][nx][1] = true
                    }
                }
            }
        }
    }
    return -1
}