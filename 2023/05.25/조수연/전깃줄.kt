import java.util.*
import java.io.*
import kotlin.collections.ArrayList
import kotlin.math.max

data class Wire(
        val start: Int,
        val end: Int,
)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = readLine().toInt()
    val wireList = ArrayList<Wire>()

    repeat(N){
        val token = StringTokenizer(readLine())
        val wire = Wire(token.nextToken().toInt(),token.nextToken().toInt())
        wireList.add(wire)
    }

    wireList.sortBy { it.start } // 왼쪽 전봇대 번호를 기준으로 정렬

    var dp = IntArray(N)
    var maxWire = -1 // 전깃줄을 가장 많이 연결할 수 있는 갯수

    for (i in 0 until N){
       dp[i] = 1
       for (j in 0 until i){
           if (wireList[j].end < wireList[i].end){ // 이전의 전깃줄보다 현재 전깃줄의 끝 번호가 더 밑에 있으면 연결할 수 있으니깐 +1
               dp[i] = max(dp[i],dp[j]+1)
           }
       }
        maxWire = max(maxWire,dp[i])
    }

    bw.write("${N - maxWire}") // 총 전깃줄 갯수에서 최대로 연결할 수 있는 전깃줄을 빼면 최소로 이을 수 있는 전깃줄 갯수가 나온다

    bw.flush()
    bw.close()
}

