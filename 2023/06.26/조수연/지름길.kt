import java.io.*
import java.util.StringTokenizer
import kotlin.math.min

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var token = StringTokenizer(readLine())
    val n = token.nextToken().toInt()
    val d = token.nextToken().toInt()
    val shortcutList = ArrayList<Triple<Int, Int, Int>>()

    repeat(n) {
        token = StringTokenizer(readLine())
        val start = token.nextToken().toInt()
        val end = token.nextToken().toInt()
        val cost = token.nextToken().toInt()

        // 목표 위치보다 도착 지점이 작고 지름길 비용이 원래 소요되는 거리보다 작을 경우 추가
        if (d >= end && end - start > cost) shortcutList.add(Triple(start, end, cost))
    }

    // 시작위치(오름차순) -> 도착 위치(내림차순) -> 비용 (오름차순) 정렬
    shortcutList.sortWith(compareBy<Triple<Int, Int, Int>> { it.first }.thenByDescending { it.second }.thenBy { it.third })

    var moveD = 0
    var index = 0
    val distanceArr = IntArray(10001) { Integer.MAX_VALUE }
    distanceArr[0] = 0

    while (moveD < d) {
        if (index < shortcutList.size) { // 지름길을 다 검색했으면 나머지 이동 거리는 기존에 소요되는 거리 비용
            val shortcut = shortcutList[index]
            if (moveD == shortcut.first) { // 지름길의 시작위치를 기점으로 최소거리를 갱신
                distanceArr[shortcut.second] = min(distanceArr[moveD] + shortcut.third, distanceArr[shortcut.second])
                index++
            } else {
                distanceArr[moveD + 1] = min(distanceArr[moveD + 1], distanceArr[moveD] + 1)
                moveD++
            }
        }else{
            distanceArr[moveD + 1] = min(distanceArr[moveD+1],distanceArr[moveD]+1)
            moveD++
        }
    }

    bw.write("${distanceArr[moveD]}")

    bw.flush()
    bw.close()
}