package com.ssafy.algorithm

import java.util.*

fun main() {
    val sl = Solution()
    val s = "}}}"
    println(sl.solution(s))
}

class Solution {
    fun solution(s: String): Int {
        val queue: Queue<Char> = LinkedList()
        var answer: Int = 0

        for (c in s) {
            queue.add(c)
        }
        var count = 0
        while (count < s.length) {
            if (check(queue)) answer++ //올바른 괄호라면 ++

            queue.add(queue.poll()) // 회전하기

            count++

        }

        return answer
    }

    fun check(queue: Queue<Char>): Boolean {
        val stack: Stack<Char> = Stack()
        for (c in queue) {
            if (stack.isEmpty()) {
                if (c == ')' || c == '}' || c == ']') return false //비어있을 때 닫는 괄호가 오면 종료
                stack.add(c)
            } else {
                when (c) {
                    ')' ->
                        if (stack.contains('(')) {
                            if (stack.peek() == '(') stack.pop() //바로 앞에 같은 괄호가 있으면 stack pop
                            else stack.add(c)
                        }else{
                            return false //여는 기호가 없다면 종료
                        }
                    '}' ->
                        if (stack.contains('{')) {
                            if (stack.peek() == '{') stack.pop()
                            else stack.add(c)
                        }else{
                            return false
                        }
                    ']' ->
                        if (stack.contains('[')) {
                            if (stack.peek() == '[') stack.pop()
                            else stack.add(c)
                        }else{
                            return false
                        }
                    else -> stack.add(c)
                }
            }
        }

        if (stack.isEmpty()) return true

        return false
    }
}


