import java.io.*
import java.util.StringTokenizer
import kotlin.math.*

lateinit var tree: ArrayList<ArrayList<Pair<Int, Int>>>
lateinit var checked: BooleanArray // 리프 노드 체크 배열
lateinit var visited: BooleanArray // 중복 방문 체크 배열
lateinit var finished: BooleanArray // 리프 노드 중복 방문 체크 배열
var answer = 0

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = readLine().toInt()

    tree = ArrayList()

    for (i in 0..n) {
        tree.add(ArrayList())
    }

    checked = BooleanArray(n + 1){true}
    finished = BooleanArray(n + 1)

    repeat(n - 1) {
        val token = StringTokenizer(readLine())
        val parent = token.nextToken().toInt()
        val child = token.nextToken().toInt()
        val weight = token.nextToken().toInt()

        tree[parent].add(Pair(child, weight))
        tree[child].add(Pair(parent, weight))

        checked[parent] = false
    }

    val leaves = ArrayList<Int>()

    for (i in checked.indices) {
        if (checked[i]) leaves.add(i)
    }

    for (i in leaves.indices) {
        visited = BooleanArray(n + 1)
        checked[leaves[i]] = false
        maxDiameter(leaves[i],0)
        finished[leaves[i]] = true
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}

// dfs 접근
fun maxDiameter(start: Int, weight:Int) {

    visited[start] = true

    for (next in tree[start]) {
        if (!visited[next.first] && !finished[next.first]) {
            maxDiameter(next.first,weight + next.second)
        }
    }

    answer = max(answer,weight)
    return
}