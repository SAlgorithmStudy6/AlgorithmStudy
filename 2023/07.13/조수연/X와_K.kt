import java.io.*
import kotlin.math.pow

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (x,k) = readLine().trim().split(" ").map { it.toInt() }

    val binaryX = x.toString(2)
    val binaryK = k.toString(2)

    var answer = 0L
    var c = ' '
    var idx = binaryK.length-1
    var i = 0

    while (idx >= 0){
        // 이진수 x의 오른쪽부터 탐색
        c = if (i < binaryX.length) binaryX[binaryX.length-1-i]
        else '0'

        if (c == '0'){  // 1인 비트는 넘어가고 0인 비트일 경우에 체크
            if (binaryK[idx] == '1'){ // 이진수 k의 idx가 1이면 조건을 만족
                val a : Long = 2.0.pow(i.toDouble()).toLong()
                answer += a
            }
            idx--
        }
        i++
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}