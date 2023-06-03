package 정렬_부수기

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

/*
   선생님에계 새로운 과제가 주어졌다.
   수업이 끝난 직후에 다음 수업을 시작할 수 있다 (즉, Ti <= Si일 경우 i 수업과 j 수업은 같이 들을 수 있다)
   수강신청 대충한 게 찔리면, 선생님을 도와드리자!

   첫 번째 줄에 N이 주어진다. (1 <= N <= 200,000)
   이후 N개의 줄에 Si, Ti가 주어진다. (0 <= Si <= Ti <= 10^9)

   강의실의 개수를 출력하라
 */

// Input
private lateinit var br: BufferedReader

// Variables
private var N = 0
private var pQue: PriorityQueue<Lecture> = PriorityQueue()
private var timePque = PriorityQueue<Int>()
private lateinit var lectures: Array<Lecture>

private data class Lecture(
    var startTime: Int, // 수업 시작 시간
    var endTime: Int // 수업 끝나는 시간
) : Comparable<Lecture> {
    // End of Class
    override fun compareTo(other: Lecture): Int {
        if (startTime == other.startTime) {
            return endTime - other.endTime
        }
        return startTime - other.startTime
    }
} // End of Lecture class

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\정렬_부수기\\res\\11000.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))
    val sb = StringBuilder()

    input()

    var index = 0
    while (pQue.isNotEmpty()) {
        lectures[index++] = pQue.poll()
    }

    timePque.offer(lectures[0].endTime)

    for (i in 1 until N) {
        if (timePque.peek() <= lectures[i].startTime) {
            timePque.poll()
        }
        timePque.offer(lectures[i].endTime)
    }

    sb.append(timePque.size)

    bw.write(sb.toString())
    bw.close()
} // End of main

private fun input() {
    N = br.readLine().toInt()
    lectures = Array(N) { Lecture(0, 0) }
    for (i in 0 until N) {
        val st = StringTokenizer(br.readLine())
        pQue.offer(
            Lecture(
                st.nextToken().toInt(), st.nextToken().toInt()
            )
        )
    }
} // End of input
