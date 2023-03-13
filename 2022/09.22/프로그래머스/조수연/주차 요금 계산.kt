package com.ssafy.algorithm

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val sl = Solution()
    val record = arrayOf(
        "Enter uid1234 Muzi",
        "Enter uid4567 Prodo",
        "Leave uid1234",
        "Enter uid1234 Prodo",
        "Change uid4567 Ryan"
    )
    println(sl.solution(record))
}

class Solution {
    fun solution(record: Array<String>): Array<String> {
        val user = HashMap<String, String>() // <id,nickname> map
        val command = ArrayList<Pair<Int,String>>() // <command,id> map
        for (info in record) { //채팅방 메세지 세팅
            val information = info.split(" ")
            when (information[0]) { //첫 단어로 구별
                "Enter" -> {
                    user.put(information[1], information[2]) //user 갱신
                    command.add(Pair(1,information[1])) //명령어 삽입
                }
                "Leave" -> {
                    command.add(Pair(2,information[1]))
                }
                "Change" -> {
                    user.put(information[1], information[2])
                }
            }
        }

        val answer = Array(command.size){""}
        var index = 0
        for (pair in command){
            if (pair.first == 1){ // 1이면 enter 2이면 leave
                answer[index] = user.getValue(pair.second) + "님이 들어왔습니다."
            }else{
                answer[index] = user.getValue(pair.second) + "님이 나갔습니다."
            }
            index++
        }
        return answer
    }
}


