package com.ssafy.algorithm

import java.io.*
import java.util.*

lateinit var map : Array<IntArray> //미세먼지 확산되기 전 배열
lateinit var spreadMap : Array<IntArray> //확산되고 난 후 배열
lateinit var checked : Array<BooleanArray> //미세먼지 유무 체크 배열
lateinit var fresh : Pair<Int,Int> //공기청정기 위치
val dx = intArrayOf(0,0,-1,1)
val dy = intArrayOf(-1,1,0,0)

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))

    var token = StringTokenizer(br.readLine())

    val R = token.nextToken().toInt()
    val C = token.nextToken().toInt()
    var T = token.nextToken().toInt()

    map = Array(R){IntArray(C)}
    spreadMap = Array(R){IntArray(C)}
    checked = Array(R){BooleanArray(C)}

    var first = -1
    var second = -1
    var firstCheck = false // 첫번째 공기청정기 위치 체크

    for (i in map.indices){
        token = StringTokenizer(br.readLine())
        for (j in map[i].indices){
            map[i][j] = token.nextToken().toInt()
            spreadMap[i][j] = map[i][j]
            if (map[i][j] > 0) checked[i][j] = true
            else if (map[i][j] == -1 && !firstCheck) {
                first = i
                firstCheck = true
            }
            else if (map[i][j] == -1 && firstCheck) second = i
        }
    }

    fresh = Pair(first,second) //공기청정기 좌표 할당

    while (T>0){ //T초 만큼 반복
        spread()
        copyArr() //확산된 배열로 갱신
        move() //이동
        copySpread() //이동한 map을 spreadMap에 다시 갱신
        T--
    }

    var sum = 0

    for (i in map.indices){
        sum += map[i].filter { it > 0 }.sum()
    }

    println(sum)

}

fun spread(){ //미세먼지 확산
    for (i in map.indices){
        for (j in map[i].indices){
            var count = 0
            if (map[i][j] > 0) {
                for (k in dx.indices){
                    val r = i + dy[k]
                    val c = j + dx[k]

                    if (r >= 0 && r < map.size && c >= 0 && c < map[i].size && map[r][c] != -1){ //범위 안에 들어오면 미세먼지 갱신 및 카운트 증가
                        count++
                        spreadMap[r][c] += map[i][j] / 5
                    }
                }
                spreadMap[i][j] -= (map[i][j] / 5) * count // 중심 미세먼지 갱신
            }
        }
    }
}

fun copyArr(){
    for (i in spreadMap.indices){
        for (j in spreadMap[i].indices){
            map[i][j] = spreadMap[i][j]
        }
    }
}

fun copySpread(){
    for (i in spreadMap.indices){
        for (j in spreadMap[i].indices){
            spreadMap[i][j] = map[i][j]
        }
    }
}
fun move(){ //이동
    //위쪽 공기 청정기
    var direction = 1
    var currentR = fresh.first-1
    var currentC = 0
    while (true){
        if (map[currentR][currentC] == -1) break
        when(direction){
            1 ->{ //아랫 방향
                if (currentR == 0) {
                    map[currentR+1][currentC] = map[currentR][currentC]
                    currentC++
                    direction = 2
                }else {
                    if (currentR+1 != fresh.first) {
                        map[currentR+1][currentC] = map[currentR][currentC]
                    }
                    currentR--
                }
            }
            2 ->{ //왼쪽 방향
                if (currentC == map[currentR].size-1) {
                    map[currentR][currentC-1] = map[currentR][currentC]
                    currentR++
                    direction = 3
                }else{
                    map[currentR][currentC-1] = map[currentR][currentC]
                    currentC++
                }
            }
            3 ->{ //윗 방향
                if (currentR == fresh.first){
                    map[currentR-1][currentC] = map[currentR][currentC]
                    currentC--
                    direction = 4
                }else{
                    map[currentR-1][currentC] = map[currentR][currentC]
                    currentR++
                }
            }
            4 ->{ //오른쪽 방향
                if (currentC != 0) map[currentR][currentC+1] = map[currentR][currentC]
                currentC--
                if (currentC == 0) map[currentR][currentC+1] = 0
            }
        }
    }

    direction = 1
    currentR = fresh.second+1
    currentC = 0
    //아래쪽 공기 청정기
    while (true){
        if (map[currentR][currentC] == -1) break
        when(direction){
            1 ->{ //위쪽 방향
                if (currentR == map.size-1) {
                    map[currentR-1][currentC] = map[currentR][currentC]
                    direction = 2
                    currentC++
                }else {
                    if (map[currentR-1][currentC] != -1){
                        map[currentR-1][currentC] = map[currentR][currentC]
                    }
                   currentR++
                }
            }
            2 ->{ //왼쪽 방향
                if (currentC == map[currentR].size-1) {
                    map[currentR][currentC-1] = map[currentR][currentC]
                    currentR--
                    direction = 3
                }else{
                    map[currentR][currentC-1] = map[currentR][currentC]
                    currentC++
                }
            }
            3 ->{ //아랫 방향
                if (currentR == fresh.second){
                    map[currentR+1][currentC] = map[currentR][currentC]
                    currentC--
                    direction = 4
                }else{
                    map[currentR+1][currentC] = map[currentR][currentC]
                    currentR--
                }
            }
            4 ->{ //오른쪽 방향
                if (currentC != 0) map[currentR][currentC+1] = map[currentR][currentC]
                currentC--
                if (currentC == 0) map[currentR][currentC+1] = 0
            }
        }
    }
}


