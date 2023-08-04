import java.awt.Point
import java.io.*
import java.util.*
import kotlin.math.abs

lateinit var board: Array<CharArray>
lateinit var visited: Array<Array<Array<BooleanArray>>>
var sequence = ""
val dir = arrayOf(intArrayOf(-1, 0), intArrayOf(1, 0), intArrayOf(0, -1), intArrayOf(0, 1))

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m) = readLine().trim().split(" ").map { it.toInt() }
    board = Array(n) { CharArray(m) }
    visited = Array(n) { Array(m) { Array(n) { BooleanArray(m) } } }
    val red = Point()
    val blue = Point()

    repeat(n) { i ->
        val info = readLine()
        repeat(m) { j ->
            board[i][j] = info[j]
            if (board[i][j] == 'R') {
                red.x = j
                red.y = i
            } else if (board[i][j] == 'B') {
                blue.x = j
                blue.y = i
            }
        }
    }

    val cnt = bfs(red, blue)
    bw.write("$cnt\n$sequence")

    bw.flush()
    bw.close()
}

fun bfs(red: Point, blue: Point): Int {
    val q: Queue<Triple<Point, Point, Pair<Int, String>>> = LinkedList()
    val direction = charArrayOf('U','D','L','R')
    
    visited[red.y][red.x][blue.y][blue.x] = true
    q.add(Triple(red, blue, Pair(0, "")))

    while (q.isNotEmpty()) {

        val info = q.poll()
        val r = info.first
        val b = info.second
        val s = info.third

        if (s.first > 10) return -1

        if (board[b.y][b.x] == 'O') continue // 파란 공이 구멍에 빠지면 안된다.

        if (board[r.y][r.x] == 'O' && board[b.y][b.x] != 'O') { // 빨간 공만 구멍에 빠졌을 경우 종료
            sequence = s.second
            return s.first
        }

        for (i in 0 until 4) {
            var nrx = r.x
            var nry = r.y

            while (true) {
                if (nrx + dir[i][1] >= 0 && nry + dir[i][0] >= 0) {
                    nrx += dir[i][1]
                    nry += dir[i][0]

                    if (board[nry][nrx] == '#' || board[nry][nrx] == 'O') break
                }
            }

            if (board[nry][nrx] == '#') {
                nrx -= dir[i][1]
                nry -= dir[i][0]
            }

            var nbx = b.x
            var nby = b.y

            while (true) {
                if (nbx + dir[i][1] >= 0 && nby + dir[i][0] >= 0) {
                    nbx += dir[i][1]
                    nby += dir[i][0]

                    if (board[nby][nbx] == '#' || board[nby][nbx] == 'O') break
                }
            }

            if (board[nby][nbx] == '#') {
                nbx -= dir[i][1]
                nby -= dir[i][0]
            }

            if (nry == nby && nrx == nbx && board[nry][nrx] != 'O') { // 같은 위치인 경우
                val rDis = abs(nry - r.y) + abs(nrx - r.x)
                val bDis = abs(nby - b.y) + abs(nbx - b.x)

                if (rDis > bDis) { // 파란 공은 못 움직이는 상황에서 빨간 공이랑 만난 상황
                    nrx -= dir[i][1]
                    nry -= dir[i][0]
                } else {
                    nbx -= dir[i][1]
                    nby -= dir[i][0]
                }
            }

            // 두 공이 이전의 위치와 같은지 체크
            if (!visited[nry][nrx][nby][nbx]) {
                visited[nry][nrx][nby][nbx] = true
                q.add(Triple(Point(nrx, nry), Point(nbx, nby), Pair(s.first + 1, s.second + direction[i])))
            }
        }
    }
    return -1
}