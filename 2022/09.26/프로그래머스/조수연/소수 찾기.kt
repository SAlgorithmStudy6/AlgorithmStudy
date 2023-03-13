package com.example.test

fun main() {
    val sl = Solution()
    val numbers = "011"
    println(sl.solution(numbers))
}

class Solution {
    lateinit var set: HashSet<Int>
    lateinit var visited: BooleanArray
    fun solution(numbers: String): Int {
        var answer = 0
        visited = BooleanArray(numbers.length)
        set = HashSet()
        val sb = StringBuilder()

        for (i in 1..numbers.length) {
            combination(0, i, numbers, sb)
        }

        for (num in set){
            if (num > 1){
                if (isPrime(num)) answer++
            }
        }
        return answer
    }

    fun combination(size: Int, numSize: Int, numbers: String, sb: StringBuilder) {
        if (size == numSize) {
            set.add(sb.toString().toInt())
            return
        }

        for (i in numbers.indices) {
            if (!visited[i]) {
                visited[i] = true
                combination(size + 1, numSize, numbers, sb.append(numbers[i]))
                sb.deleteCharAt(sb.length-1)
                visited[i] = false
            }
        }
    }

    fun isPrime(n: Int): Boolean { //소수 찾기
        if (n == 1) return false
        for (i in 2..Math.sqrt(n.toDouble()).toInt()) {
            if (n % i == 0) return false
        }
        return true
    }
}