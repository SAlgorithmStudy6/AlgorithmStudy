import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = readLine().toInt()

    var sum = n.toLong()
    var i = 2

    // 조화수열
    while (i < n) {
        val j = (n - 1) / ((n - 1) / i) // i 구간에서 j까지의 구간의 숫자는 같은 연산의 횟수를 가짐
        val num = 1 + (n - 1) / i // i마다 연산의 횟수
        sum += (j - i + 1) * num

        i = j + 1 // 다음 구간으로 세팅
    }

    if (n != 1) sum++ // n이 1이 아닐 때 i가 n일 때 가지는 연산은 무조건 1

    bw.write("$sum")

    bw.flush()
    bw.close()
}