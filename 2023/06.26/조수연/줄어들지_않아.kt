import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val t = readLine().toInt()
    val dp = Array(65){LongArray(10)}

    for (i in 0 .. 9){
        dp[1][i] = 1
    }

    for (i in 2 until dp.size){
        for (j in dp[i].indices){ // ex) dp[2][0]에는 0 ~ 9까지 들어갈 수 있으므로 += dp[i-1][k]에서 k가 0~9까지 들어갈 수 있다.
            for (k in j .. 9) // 해당 숫자부터 9까지 들어갈 수 있다.
            {
                dp[i][j] += dp[i-1][k]
            }
        }
    }

    repeat(t){
        bw.write("${dp[readLine().toInt() ].sum()}\n")
    }

    bw.flush()
    bw.close()
}