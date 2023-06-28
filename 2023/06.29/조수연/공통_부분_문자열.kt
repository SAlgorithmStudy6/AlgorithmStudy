import java.io.*
import kotlin.math.max

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val str = readLine()
    val str2 = readLine()

    val arr = Array(str.length+1){IntArray(str2.length+1)}
    var length = 0

    for (i in 1..str.length){
        for (j in 1..str2.length){
            if (str[i-1] == str2[j-1]){ // 해당 위치에 문자가 서로 같을 때
                arr[i][j] = arr[i-1][j-1] + 1 // 해당 인덱스의 값은 그 전의 인덱스값 + 1 -> 현재 위치 -1의 위치에 문자 비교값 + 1
                length = max(length,arr[i][j])
            }
        }
    }

    bw.write("$length")

    bw.flush()
    bw.close()
}