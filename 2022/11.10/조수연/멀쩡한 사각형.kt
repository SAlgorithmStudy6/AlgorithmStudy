package com.ssafy.algorithm

fun main() {
    val w = 8
    val h = 12
    println(Solution().solution(w, h))
}

class Solution {
    var big = 0L
    var small = 0L
    fun solution(w: Int, h: Int): Long {

        if (w > h) {
            big = w.toLong()
            small = h.toLong()
        } else {
            big = h.toLong()
            small = w.toLong()
        }

        //전체 사각형의 개수 w * h - 대각선라인에 걸친 사각형의 수 (big + samll) - w,h의 최대공약수
        return big * small - (big + small - gcd(big, small))
    }

    fun gcd(big: Long, small: Long): Long {
        if (small == 0.toLong()) {
            return big
        } else {
            return gcd(small, big % small)
        }
    }
}


