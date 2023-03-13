package com.ssafy.algorithm


import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val sl = Solution()
    val bridge_length = 100
    val weight = 100
    val truck_weights = intArrayOf(10,10,10,10,10,10,10,10,10,10)
    println(sl.solution(bridge_length, weight, truck_weights))

}

class Solution {
    lateinit var cross: BooleanArray //현재 트럭이 다리에 있는지 체크 배열
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 0
        val wait: Queue<Int> = LinkedList()
        val bridge: Queue<Int> = LinkedList()

        for (i in truck_weights){ //대기 트럭
            wait.offer(i)
        }

        var possible = 0
        var time = 0 //시간

        cross = BooleanArray(truck_weights.size)
        val trucks = IntArray(truck_weights.size)
        var index = 0
        while (true) {
            time++
            if (wait.isEmpty() && bridge.isEmpty()) break //대기 트럭이랑 다리에 트럭 큐가 비어있으면 종료

            if (wait.isNotEmpty()){ //대기트럭이 있을 때
                if (possible + wait.peek() <= weight) { //현재 다리위에 있는 트럭 + 트럭의 무게가 다리가 버틸 수 있는 무게라면 트럭추가
                    val truckWeight = wait.poll()
                    possible += truckWeight
                    bridge.offer(truckWeight)
                    cross[index] = true
                    index++
                }
            }



            for (i in cross.indices){
                if (cross[i]){ //다리위에 있는 트럭의 인덱스
                    trucks[i]++
                    if (trucks[i] >= bridge_length && cross[i]){ //다리를 지난경우
                        cross[i] = false
                        possible -= bridge.poll() //다리가 버틸 수 있는 무게 증가
                    }
                }


            }
        }

        return time
    }
}

