package `문자열 부수기`

/*
    S를 T로 바꿀 수 있으면 1을 없으면 0을 출력한다.
 */

import java.util.*
import java.io.*

fun main() {
    val path = "C:\\Users\\Samsung\\Desktop\\코틀린 알고리즘\\src\\main\\kotlin\\문자열 부수기\\res\\12904.txt"
    val br = BufferedReader(File(path).bufferedReader())

    val S = br.readLine()
    val T = br.readLine()

    val sList = LinkedList<Char>()
    for (i in S.indices) {
        sList.add(S[i])
    }

    val tList = LinkedList<Char>()
    for (i in T.indices) {
        tList.add(T[i])
    }

    while (sList.size < tList.size) {
        if (tList[tList.size - 1] == 'A') {
            tList.removeAt(tList.size - 1)
        } else if (tList[tList.size - 1] == 'B') {
            tList.removeAt(tList.size - 1)
            tList.reverse()
        }
    }

    var sStr = ""
    sList.forEach {
        sStr += it
    }

    var tStr = ""
    tList.forEach {
        tStr += it
    }


    if (sStr == tStr) {
        println(1)
    } else {
        println(0)
    }
} // End of main