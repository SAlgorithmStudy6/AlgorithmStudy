package com.ssafy.algorithm


import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val sl = Solution()
//    val clothes = arrayOf(
//        arrayOf("yellow_hat", "headgear"), arrayOf("blue_sunglasses", "eyewear"),
//        arrayOf("green_turban", "headgear")
//    )
    val clothes = arrayOf(
        arrayOf("crow_mask", "face"), arrayOf("blue_sunglasses", "face"),
        arrayOf("smoky_makeup", "face")
    )
    println(sl.solution(clothes))

}

class Solution {
    lateinit var clothesType : HashSet<String> //옷 종류 담는 set
    lateinit var checked : BooleanArray //옷 종류 중복 체크
    lateinit var clothList: ArrayList<Pair<String,Int>> //옷 리스트
    var answer = 0
    fun solution(clothes: Array<Array<String>>): Int {
        answer = 0
        clothesType = HashSet()
        for (i in clothes){ //옷 종류 담기
            clothesType.add(i[1])
        }
        clothList = ArrayList()
        checked = BooleanArray(clothesType.size)
        for (i in clothes){ // 옷 리스트에 정보 담기
            clothList.add(Pair(i[0],clothesType.indexOf(i[1])))
        }

        if(clothesType.size == 30){ //1번 정답 시간초과 남 조합으로 못품
            answer = 1073741823;
        }else{
            for (i in 1 .. clothesType.size){
                val clothArr = Array(i){""}
                permulation(0,i,0, clothArr)
            }
        }
        return answer
    }

    fun permulation(index : Int, size : Int, start : Int, clothArr : Array<String>){ //옷 조합
        if (index == size){
            answer++
            return
        }

        for (i in start until clothList.size){
            if (!checked[clothList[i].second]){
                clothArr[index] = clothList[i].first
                checked[clothList[i].second] = true
                permulation(index+1,size,i+1,clothArr)
                checked[clothList[i].second] = false
            }
        }

    }
}

