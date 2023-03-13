package com.ssafy.algorithm

import java.util.*

fun main() {
    val sl = Solution()
    val queue1 = intArrayOf(1,1,1,1,1,1,1,1,1,10)
    val queue2 = intArrayOf(1,1,1,1,1,1,1,1,1,1)
    println(sl.solution(queue1, queue2))
}

class Solution {
    var sum1: Long = 0
    var sum2: Long = 0

    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var answer: Int = -2
        var sum: Long = 0

        //큐의 합 구하기
        for (i in queue1) sum1 += i
        for (i in queue2) sum2 += i
        sum = (sum1 + sum2) / 2

        if (queue1.maxOrNull()!! > sum || queue2.maxOrNull()!! > sum) answer = -1 //인덱스가 각큐의 합보다 클 경우 -1 리턴
        else answer = shift(queue1, queue2, sum)
        return answer
    }

    fun shift(queue1: IntArray, queue2: IntArray, sum: Long): Int {

        var count = 0
        val shift1: Queue<Long> = LinkedList()
        val shift2: Queue<Long> = LinkedList()

        for (value in queue1) { //큐에 값 채우기
            shift1.offer(value.toLong())
        }

        for (value in queue2) {
            shift2.offer(value.toLong())
        }
        var time = 0
        while (time <= 300000) { //shift가 똑같이 반복하면 탈출 loop

            if (sum1 == sum2) break //합이 같으면 종료

            if (sum1 < sum) { //shift1 큐의 합이 전체 큐의 합 / 2보다 작으면 shift1에 value 추가
                val qPoll = shift2.poll()
                shift1.offer(qPoll)
                sum1 += qPoll //shift1 sum1 값 증가
                sum2 -= qPoll //shift2 sum2 값 감소
                count++
            } else {
                val qPoll = shift1.poll()
                shift2.offer(qPoll)
                sum2 += qPoll
                sum1 -= qPoll
                count++
            }
            time++
        }
        if (time > 300000){
            count = -1
        }
        return count
    }
}


