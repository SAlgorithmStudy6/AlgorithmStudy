import java.awt.Point
import kotlin.math.abs
import kotlin.text.StringBuilder

fun main() {
    val n = 3
    val m = 4
    val x = 2
    val y = 3
    val r = 3
    val c = 1
    val k = 5
    println(Solution().solution(n, m, x, y, r, c, k))
}

class Solution {
    //사전 순이므로 방향 벡터를 하 좌 우 상 순으로 세팅
    val dx = intArrayOf(0, -1, 1, 0)
    val dy = intArrayOf(1, 0, 0, -1)
    val move = charArrayOf('d', 'l', 'r', 'u')
    val sb = StringBuilder()
    var answer = ""

    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        val distance = abs(r - x) + abs(c - y)
        // E까지 거리가 k보다 작은 경우이거나 2로 나눈 나머지가 홀수인 경우에는 어차피 도달 못하니 impossible
        if (k >= distance && (k - distance) % 2 == 0) dfs(Point(y - 1, x - 1), Point(c - 1, r - 1), k, 0, n, m)
        return if (answer.isNotEmpty()) answer else "impossible"
    }

    fun dfs(startPoint: Point, endPoint: Point, k: Int, count: Int, n: Int, m: Int) {
        if (count > k || answer.isNotEmpty()) return //횟수를 초과하거나 단어를 찾으면 return
        for (i in dx.indices) { //방향이동
            if (answer.isNotEmpty()) break
            val x = startPoint.x + dx[i]
            val y = startPoint.y + dy[i]
            val d = abs(endPoint.x - x) + abs(endPoint.y - y)
            // 영역 안이거나
            // 현재 위치에서 E까지 거리가 k - 이동한 횟수보다 작거나
            // k - 이동한 횟수 - 현재위치부터 E까지 거리를 2로 나눴을 때 나머지가 홀수인 경우 이동이 가능
            if (x in 0 until m && y in 0 until n && d <= k - count && (k - count - d) % 2 != 0) {
                if (count + 1 == k && x == endPoint.x && y == endPoint.y) {
                    answer = sb.append(move[i]).toString()
                    return
                } else {
                    sb.append(move[i])
                    dfs(Point(x, y), endPoint, k, count + 1, n, m)
                    sb.deleteAt(sb.length - 1)
                }
            }
        }
    }
}