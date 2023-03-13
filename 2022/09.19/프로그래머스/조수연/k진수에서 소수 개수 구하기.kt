package com.ssafy.algorithm

fun main() {
    val sl = Solution()
    val n = 110011
    val k = 10
    println(sl.solution(n, k))
}

class Solution {
    var answer: Int = 0
    fun solution(n: Int, k: Int): Int {
        var num = n
        val sb = StringBuilder()
        while (true) { //진수로 변환하기
            if (num / k == 0) {
                sb.insert(0, num % k)
                break
            }
            sb.insert(0, num % k)
            num /= k
        }
        val code = sb.toString()

        val nums = code.split("0")

        check(nums) //소수 찾기기

        return answer
    }

    fun check(nums: List<String>) {
        for (num in nums) {
            if (num != "") { //split 잘랐을 때 ""이 리스트에 담길수도 있음 제외하고 탐색
                val prime = num.toLong() 
                if (prime != 1L) { //1은 pass
                    if (isPrime(prime)) answer++
                }
            }
        }
    }

    fun isPrime(prime: Long): Boolean { // 소수 판별

        for (i in 2..Math.sqrt(prime.toDouble()).toInt()) { // 숫자의 제곱근까지 loop 해서 판별 
            if ((prime % i).toInt() == 0) return false
        }

        return true
    }
}


