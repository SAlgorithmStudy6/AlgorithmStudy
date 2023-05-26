import java.util.*
import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val token = StringTokenizer(readLine())
    val N = token.nextToken().toInt()
    val K = token.nextToken().toInt()

    val info = readLine()
    val ate = BooleanArray(N) // 햄버거를 먹었는지 체크하는 배열
    var answer = 0
    var isFind = false

    for (i in info.length - 1 downTo 0) {
        if (info[i] == 'P') {

            isFind = false // 오른쪽에서 체크 결과 초기화

            // 오른쪽부터 탐색
            if (i + K > info.length - 1) { // K 더한 거리가 범위를 벗어날 경우
                for (j in info.length - 1 downTo i + 1) {
                    if (info[j] == 'H' && !ate[j]) {
                        ate[j] = true
                        answer++
                        isFind = true
                        break
                    }
                }
            } else {
                for (j in i + K downTo i + 1) {
                    if (info[j] == 'H' && !ate[j]) {
                        ate[j] = true
                        answer++
                        isFind = true
                        break
                    }
                }
            }

            if (isFind) continue

            // 왼쪽 탐색
            if (i - K < 0) { // K 더한 거리가 범위를 벗어날 경우
                for (j in i - 1 downTo 0) {
                    if (info[j] == 'H' && !ate[j]) {
                        ate[j] = true
                        answer++
                        break
                    }
                }
            } else {
                for (j in i - 1 downTo i - K) {
                    if (info[j] == 'H' && !ate[j]) {
                        ate[j] = true
                        answer++
                        break
                    }
                }
            }
        }
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}
