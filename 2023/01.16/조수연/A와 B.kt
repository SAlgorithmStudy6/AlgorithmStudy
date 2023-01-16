package com.ssafy.algorithm

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))

    var S = br.readLine()
    var T = br.readLine()

    var answer = 0

    for (i in T.indices) {
        val c = T.last()
        T = T.substring(0,T.length-1)

        // c가 B인 경우에는 자른 문자열을 뒤집어준다.
        if(c == 'B'){
            val sb = StringBuilder(T)
            T = sb.reverse().toString()
        }

        //두 문자열 비교
        if (S.length == T.length){
            if (S.equals(T)) answer = 1
            break
        }
    }

    println(answer)

}







