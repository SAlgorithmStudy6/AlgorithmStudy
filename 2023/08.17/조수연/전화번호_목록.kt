import java.io.*

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val t = readLine().toInt()
    var contactArr: Array<String>

    repeat(t) {
        val n = readLine().toInt()
        var isFind = false
        contactArr = Array(n) { "" }

        repeat(n) { contactArr[it] = readLine() }

        contactArr.sort()

        for (i in 0 until n-1){
            if (contactArr[i + 1].startsWith(contactArr[i])) {
                isFind = true
                break
            }
        }

        if (isFind) bw.write("NO\n")
        else bw.write("YES\n")
    }

    bw.flush()
    bw.close()
}