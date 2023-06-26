import java.util.*
import java.io.*

private const val Red = 0; private const val Green = 1; private const val Blue = 2;
private lateinit var memo : Array<IntArray>
private var N = 0;

fun main() {
    val path = "C:\\Users\\Samsung\\Desktop\\코틀린 알고리즘\\src\\main\\kotlin\\DP 부수기\\res\\1149.txt"
    val br = BufferedReader( File(path).bufferedReader() )

    N = br.readLine().toInt()
    memo = Array(N, {IntArray(3)})

    for(i in 0 until N) {
        var st = StringTokenizer(br.readLine())
        memo[i][Red] = st.nextToken().toInt()
        memo[i][Green] = st.nextToken().toInt()
        memo[i][Blue] = st.nextToken().toInt()
    }

    for(i in 1 until N) {
        memo[i][Red] += Math.min(memo[i-1][Green], memo[i-1][Blue]);
        memo[i][Green] += Math.min(memo[i-1][Red], memo[i-1][Blue]);
        memo[i][Blue] += Math.min(memo[i-1][Red], memo[i-1][Green]);
    }

    print( Math.min(memo[N-1][Red], Math.min(memo[N-1][Green], memo[N-1][Blue]) ))
} // End of main