package com.ssafy.algorithm

import java.util.*
import kotlin.collections.HashMap
import kotlin.math.ceil

fun main() {
    val sl = Solution()
    val fees = intArrayOf(180, 5000, 10, 600)
    val records = arrayOf(
        "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"
    )
    println(sl.solution(fees, records))
}

class Solution {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        val queue: Queue<String> = LinkedList() //주차 차량 입출차 확인 큐
        val map: HashMap<String, Int> = HashMap() //해당 차량정보
        val parkTime: HashMap<String, Int> = HashMap() //차량 정보

        for (record in records) {
            val information = record.split(" ")
            if (information[2] == "IN") { //입차
                queue.add(information[1])
                val time = information[0].split(":")
                val hour = (time[0].toInt()) * 60
                val minute = time[1].toInt()
                map.put(information[1], hour + minute)
            } else { //출차
                queue.remove(information[1])
                val time = information[0].split(":")
                val hour = (time[0].toInt()) * 60
                val minute = time[1].toInt()

                val pTime = (hour + minute) - map.getValue(information[1]) // 주차시간
                if (parkTime.containsKey(information[1])) {
                    parkTime.put(information[1], parkTime.getValue(information[1]) + pTime) //주차시간 parkTime에 갱신
                } else {
                    parkTime.put(information[1], pTime)
                }
            }
        }

        if (queue.isNotEmpty()) { //IN만 하고 OUT 안 한 상황
            while (!queue.isEmpty()) {
                val key = queue.poll()
                val pTime = (60 * 23) + 59 - map.getValue(key)
                if (parkTime.containsKey(key)){ //들어온 적이 있으면
                    parkTime.put(key, parkTime.getValue(key) + pTime) //23:59에 출차하는 것으로 간주
                }else{
                    parkTime.put(key,pTime)
                }
            }
        }

        val sortedMap = parkTime.toSortedMap()
        val answer = IntArray(sortedMap.size)
        var index = 0


        for (info in sortedMap) {
            if (info.value - fees[0] >= 0){ //기본시간 보다 클 경우
                answer[index] = fees[1] + ceil((info.value - fees[0]).toDouble() / fees[2].toDouble()).toInt() * fees[3] //요금 정산
            }else answer[index] = fees[1]
            index++
        }

        return answer
    }
}


