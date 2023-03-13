package com.ssafy.algorithm

import java.io.*
import java.util.StringTokenizer
import kotlin.math.max

lateinit var array: ArrayList<ArrayList<Int>>
var visited = IntArray(101) //숫자가 몇번 나오는지 체크 배열

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var token = StringTokenizer(br.readLine())
    val r = token.nextToken().toInt()
    val c = token.nextToken().toInt()
    val k = token.nextToken().toInt()

    array = ArrayList(ArrayList())

    repeat(3) {
        token = StringTokenizer(br.readLine())
        val list = ArrayList<Int>()
        list.add(token.nextToken().toInt())
        list.add(token.nextToken().toInt())
        list.add(token.nextToken().toInt())
        array.add(list)
    }

    var count = 0

    while (count <= 100) {
        //array list 사이즈가 해당좌표를 포함하고 k일때 break
        if (array.size >= r && array[0].size >= c && array[r - 1][c - 1] == k) break
        count++

        if (array.size >= array[0].size) { //행의 개수 >= 열의 개수
            array = operationR()
        } else { //행의 개수 < 열의 개수
            array = operationC()
        }
    }

    if(count > 100) count = -1
    println(count)

}

fun operationR(): ArrayList<ArrayList<Int>> {
    var tempArr = ArrayList<ArrayList<Int>>()
    var changeArr : ArrayList<Int>
    var maxRow = 0
    for (i in array.indices) {
        visited = IntArray(101) //카운트 초기화
        var row = 0 //행의 길이
        changeArr = ArrayList()

        for (num in array[i]) {
            visited[num]++
        }

        val countList = ArrayList<Pair<Int, Int>>()

        for (j in  1 until visited.size) {
            if (visited[j] >= 1 && countList.size <= 100) {
                countList.add(Pair(j, visited[j]))
                row += 2
            }
        }

        maxRow = max(maxRow, row) //최대 행 길이 갱신

        countList.sortWith(compareBy<Pair<Int, Int>> { it.second }.thenBy { it.first }) //나온 횟수로 오름차순 정렬하고 같은 횟수면 나온 숫자로 오름차순 정렬

        for (pair in countList) {
            changeArr.add(pair.first)
            changeArr.add(pair.second)
        }

        tempArr.add(changeArr)
    }

    val resultArr = Array(array.size){IntArray(maxRow)} //최대 열 길이만큼 배열 초기화

    for (i in tempArr.indices){
        for (j in tempArr[i].indices){
            resultArr[i][j] = tempArr[i][j]
        }
    }

    tempArr = ArrayList()

    for (i in resultArr.indices){
        var tempList = ArrayList<Int>()
        for (j in resultArr[i].indices){
            tempList.add(resultArr[i][j])
        }
        tempArr.add(tempList)
    }

    return tempArr
}

fun operationC(): ArrayList<ArrayList<Int>> {
    var tempArr = ArrayList<ArrayList<Int>>()
    var changeArr : ArrayList<Int>
    var maxCol = 0
    for (i in array[0].indices) {
        visited = IntArray(101) //카운트 초기화
        var col = 0 //행의 길이
        changeArr = ArrayList()

        for (num in array) {
            visited[num[i]]++
        }

        val countList = ArrayList<Pair<Int, Int>>()

        for (j in  1 until visited.size) {
            if (visited[j] >= 1 && countList.size <= 100) {
                countList.add(Pair(j, visited[j]))
                col += 2
            }
        }

        maxCol = max(maxCol, col) //최대 열 길이 갱신

        countList.sortWith(compareBy<Pair<Int, Int>> { it.second }.thenBy { it.first }) //나온 횟수로 오름차순 정렬하고 같은 횟수면 나온 숫자로 오름차순 정렬

        for (pair in countList) {
            changeArr.add(pair.first)
            changeArr.add(pair.second)
        }

        tempArr.add(changeArr)
    }

    val resultArr = Array(maxCol){IntArray(array[0].size)} //최대 열 길이만큼 배열 초기화

    for (i in tempArr.indices){
        for (j in tempArr[i].indices){
            resultArr[j][i] = tempArr[i][j]
        }
    }

    tempArr = ArrayList()

    for (i in resultArr.indices){
        var tempList = ArrayList<Int>()
        for (j in resultArr[i].indices){
            tempList.add(resultArr[i][j])
        }
        tempArr.add(tempList)
    }

    return tempArr
}