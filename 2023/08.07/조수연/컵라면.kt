import java.io.*
import java.util.*

lateinit var st: StringTokenizer

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = readLine().toInt()
    val pq = PriorityQueue(compareBy<Pair<Int, Int>> { it.first }.thenByDescending { it.second })

    repeat(n) {
        st = StringTokenizer(readLine())
        pq.add(Pair(st.nextToken().toInt(), st.nextToken().toInt()))
    }

    val resultPq = PriorityQueue<Int>() // 라면 갯수가 적은 순으로 정렬
    for (i in 1..n) {
        val problem = pq.poll()
        resultPq.add(problem.second)

        if (problem.first < resultPq.size){ // 해당 데드라인에 문제가 여러개일 경우 갯수가 가장 높은 라면만 남기고 poll
            resultPq.poll()
        }
    }

    var answer = 0

    while (resultPq.isNotEmpty()){
        answer += resultPq.poll()
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}