import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val str = readLine()

    bw.write("${check(str)}")

    bw.flush()
    bw.close()
}

fun check(str: String): String {
    var i = 0

    while (i < str.length) {
        // 0으로 시작하는 패턴 다음에 무조건 1이 나온다.
        if (str[i] == '0') {
            if (i + 1 >= str.length) return "NOISE"
            if (str[i + 1] != '1') return "NOISE"
            i += 2
        } else { // 1로 시작하는 패턴 뒤에 00이 무조건 나온다.
            if (i + 3 >= str.length) return "NOISE"
            if (str[i + 1] != '0' || str[i + 2] != '0') return "NOISE"
            i += 3

            while (i < str.length && str[i] == '0'){ // 100~ 뒤에 0이 1개이상 나온다.
                i++
            }

            // 0으로 이어지다가 뒤에 1이 안나온 경우 100~1
            if (i >= str.length) return "NOISE"
            i++

            // 1~ 이어지는 경우 i + 1과 i + 2가 00이면 100~패턴이므로 종료 아니면 계속 이동
            while (i < str.length && str[i] == '1'){
                if (i + 2 < str.length && str[i+1] == '0' && str[i+2] == '0') break
                i++
            }
        }
    }
    return "SUBMARINE"
}