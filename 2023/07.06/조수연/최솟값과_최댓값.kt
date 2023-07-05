import java.awt.Point
import java.io.*
import java.util.*
import kotlin.math.*

lateinit var numArr: IntArray
lateinit var tree: Array<Point>

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m) = readLine().trim().split(" ").map { it.toInt() }

    numArr = IntArray(n + 1)
    tree = Array(n * 4) { Point() }

    repeat(n) { numArr[it + 1] = readLine().toInt() }

    init(1, n, 1)

    val sb = StringBuilder()

    repeat(m) {
        val token = StringTokenizer(readLine())
        val l = token.nextToken().toInt()
        val r = token.nextToken().toInt()

        val result = find(1,n,l,r,1)

        sb.append("${result.y} ${result.x}\n")
    }

    bw.write(sb.toString())

    bw.flush()
    bw.close()
}

// 구간 합 트리 생성
fun init(start: Int, end: Int, node: Int): Point {

    if (start == end) {
        tree[node] = Point(numArr[start], numArr[end])
        return tree[node]
    }

    val mid = (start + end) / 2
    val left = init(start, mid, node * 2)
    val right = init(mid+1, end, node * 2 + 1)

    tree[node] = Point(max(left.x, right.x), min(left.y, right.y))

    return tree[node]
}

fun find(start: Int, end: Int, l : Int, r : Int, node : Int) : Point{
    // 범위 밖에 있는 경우
    if (end < l || start > r) return Point(Integer.MIN_VALUE,Integer.MAX_VALUE)
    // 범위 안에 있는 경우
    if (l <= start && end <= r) return tree[node]

    // 그렇지 않으면 left, right 두 부분으로 나누어서 찾기
    val mid = (start + end) / 2
    val left = find(start, mid,l,r, node * 2)
    val right = find(mid+1, end,l,r, node * 2 + 1)
    return Point(max(left.x,right.x),min(left.y,right.y))
}