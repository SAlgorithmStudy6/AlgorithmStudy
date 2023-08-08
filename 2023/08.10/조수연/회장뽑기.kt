import java.io.*
import java.util.*
import kotlin.math.max
import kotlin.math.min

lateinit var st: StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = readLine().toInt()
    val relation = Array(n + 1) { IntArray(n + 1) { 50 } }
    val scorePq = Array(50){ PriorityQueue<Int>() }

    repeat(n){ relation[it+1][it+1] = 0 }

    while (true) {
        st = StringTokenizer(readLine())
        val s = st.nextToken().toInt()
        val e = st.nextToken().toInt()

        if (s == -1 && e == -1) break

        relation[s][e] = 1
        relation[e][s] = 1
    }

    // 플로이드-워셜
    for (k in 1..n) {
        for (i in 1..n) {
            for (j in 1..n) {
                if (relation[i][k] + relation[k][j] < relation[i][j]) {
                    relation[i][j] = relation[i][k] + relation[k][j]
                }
            }
        }
    }

    var minScore = 50

    for (i in 1..n){
        var maxScore = 0
        for (j in 1..n){
            maxScore = max(maxScore, relation[i][j])
        }
        scorePq[maxScore].add(i)
        minScore = min(minScore,maxScore)
    }

    bw.write("$minScore ${scorePq[minScore].size}\n")

    while (scorePq[minScore].isNotEmpty()){
        bw.write("${scorePq[minScore].poll()} ")
    }

    bw.flush()
    bw.close()
}