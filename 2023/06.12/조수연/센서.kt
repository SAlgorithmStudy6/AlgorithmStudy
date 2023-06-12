import java.util.*
import java.io.*
import kotlin.collections.ArrayList

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = readLine().toInt()
    val k = readLine().toInt()

    if (k >= n) bw.write("0") // 집중국의 갯수가 센서의 개수보다 같거나 많을경우 수신 가능 영역의 길이는 0
    else {
        val token = StringTokenizer(readLine())
        val pList = ArrayList<Int>()

        repeat(n) {
            pList.add(token.nextToken().toInt())
        }

        pList.sort() // 위치순으로 정렬

        val iList = ArrayList<Int>() // 정렬된 좌표 위치 사이의 거리 리스트

        for (i in 0 until n - 1) {
            iList.add(pList[i + 1] - pList[i])
        }

        repeat(k - 1) {// k-1개의 분기점이 생기므로 좌표 위치 사이의 거리 리스트에 k-1개만큼 최대값을 뺀다
            iList.remove(iList.max())
        }

        bw.write("${iList.sum()}") // 최댓값을 빼고 난 후 거리의 합
    }


    bw.flush()
    bw.close()
}

