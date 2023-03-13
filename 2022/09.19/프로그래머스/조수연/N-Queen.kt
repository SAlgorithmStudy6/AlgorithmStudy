package com.ssafy.algorithm



fun main() {
    val sl = Solution()
    val n = 24
    println(sl.solution(n))
}

class Solution {
    var answer = 0
    lateinit var visited: Array<BooleanArray>
    fun solution(n: Int): Int {

        if (n == 1) answer = 1
        else if (n == 2 || n == 3) answer = 0
        else {
            visited = Array(n) { BooleanArray(n) { false } }
            nQueen(0, n)
        }

        return answer
    }

    fun nQueen(size: Int, n: Int) {
        if (size == n) { //size를 만족하면 nQueen을 만족하므로 ++
            answer++
            return
        }

        for (i in 0 until n) {
            if (size == 0) { //첫번째 인덱스는 고려할 필요 없음
                visited[size][i] = true
                nQueen(size + 1, n)
                visited[size][i] = false
            } else { //행 열 대각선 비교
                if (colCheck(size, i) && rowCheck(size, i) && crossCheck(size, i)) {
                    visited[size][i] = true
                    nQueen(size + 1, n)
                    visited[size][i] = false
                }
            }
        }
    }

    fun colCheck(row: Int, col: Int): Boolean { //열 체크
        var i = 1
        while (true) { //왼쪽 체크
            if (col - i < 0) break
            if (col - i >= 0) {
                if (visited[row][col - i]) return false
            }
            i++
        }

        i = 1
        while (true) { //오른쪽 체크
            if (col + i >= visited.size) break
            if (col + i < visited.size) {
                if (visited[row][col + i]) return false
            }
            i++
        }
        return true
    }

    fun rowCheck(row: Int, col: Int): Boolean { //행 체크
        var i = 1
        while (true) { //위쪽 체크
            if (row - i < 0) break
            if (row - i >= 0) {
                if (visited[row - i][col]) return false
            }
            i++
        }

        i = 1
        while (true) { //아래쪽 체크
            if (row + i >= visited.size) break
            if (row + i < visited.size) {
                if (visited[row + i][col]) return false
            }
            i++
        }
        return true
    }

    fun crossCheck(row: Int, col: Int): Boolean {
        var i = 1
        while (true) { //오른쪽 아래 대각선 체크
            if (i + row >= visited.size && i + col >= visited.size) break

            if (row + i < visited.size && col + i < visited.size) {
                if (visited[row + i][col + i]) return false
            }
            i++
        }

        i = 1
        while (true) { //왼쪽 위 대각선 체크
            if (row - i < 0 && col - i < 0) break

            if (row - i >= 0 && col - i >= 0) {
                if (visited[row - i][col - i]) return false
            }
            i++
        }

        i = 1
        while (true) { //오른쪽 위 대각선 체크
            if (row - i < 0 && col + i >= visited.size) break

            if (row - i >= 0 && col + i < visited.size) {
                if (visited[row - i][col + i]) return false
            }
            i++
        }

        i = 1
        while (true) { //왼쪽 아래 대각선 체크
            if (row + i >= visited.size && col - i < 0) break

            if (row + i < visited.size && col - i >= 0) {
                if (visited[row + i][col - i]) return false
            }
            i++
        }

        return true
    }
}


