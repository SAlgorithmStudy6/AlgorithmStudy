import java.util.*
import java.io.*

private const val Red = 0; private const val Green = 1; private const val Blue = 2;
private lateinit var memo : Array<IntArray>
private lateinit var arr : Array<IntArray>
private var N = 0;

fun main() {
    val path = "C:\\Users\\Samsung\\Desktop\\코틀린 알고리즘\\src\\main\\kotlin\\DP 부수기\\res\\1149.txt"
    val br = BufferedReader( File(path).bufferedReader() )

    N = br.readLine().toInt()
    memo = Array(N, {IntArray(3)})
    arr = Array(N, {IntArray(3)})

    for(i in 0 until N) {
        var st = StringTokenizer(br.readLine())
        arr[i][Red] = st.nextToken().toInt()
        arr[i][Green] = st.nextToken().toInt()
        arr[i][Blue] = st.nextToken().toInt()
    }

    memo[0][Red] = arr[0][Red]
    memo[0][Green] = arr[0][Green]
    memo[0][Blue] = arr[0][Blue]

    print( Math.min(DP(N-1, Red), Math.min(DP(N-1, Green), DP(N-1, Blue))))
} // End of main

private fun DP(N : Int, color : Int) : Int {

    if(memo[N][color] == 0) {

        if(color == Red) {
            memo[N][Red] = Math.min( DP(N-1, Green), DP(N-1, Blue)) + arr[N][Red];
        }
        else if(color == Green) {
            memo[N][Green] = Math.min( DP(N-1, Red), DP(N-1, Blue)) + arr[N][Green];
        }
        else if(color == Blue) {
            memo[N][Blue] = Math.min( DP(N-1, Red), DP(N-1, Green)) + arr[N][Blue];
        }
    }

    return memo[N][color];
} // End of DP