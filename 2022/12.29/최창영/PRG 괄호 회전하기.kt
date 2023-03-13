package `일반구현 부수기`

/*
    {} (), []가 문자열을 올바른 괄호 문자열이라고 정의합니다.

    만약 A가 올바른 괄호 문자열이라면, (A), [A], {A}도 올바른 괄호 문자열입니다.

    예를 들어 []가 올바른 괄호 문자열이므로

    S가 매개변수로 주어질 때, S를 왼쪽으로 0 ~ s의 길이만큼 회전시켰을 때
    s가 올바른 괄호 문자열이 되게 하는 x의 개수를 return 하라
 */


import java.util.*
import java.lang.StringBuilder

private var S = ""
fun main() {
    val s = "{{{{"
    println(solution(s))
} // End of main

private fun solution(s: String): Int {
    var ans = 0
    S = s

    val len = s.length
    for (i in 0 until len) {
        // S로 들어온 값이 올바른 괄호의 형태를 가지는가를 파악하기\
        // 서로의 괄호가 짝이 맞는가?
        if (checkString(S, len)) {
            ans++
        }

        println("roation String : $S")
        S = rotation(S)
    }

    return ans
} // End of solution

private fun rotation(str: String): String {
    // 왼쪽으로 회전
    val temp = StringBuilder()
    temp.append(str.substring(1, str.length)).append(str[0])
    return temp.toString()
} // End of rotation

private fun checkString(str: String, len: Int): Boolean {
    val dque: Deque<Char> = LinkedList()

    for (i in 0 until len) {
        if (str[i] == '(' || str[i] == '[' || str[i] == '{') {
            dque.offerFirst(str[i])
        } else if (str[i] == ')' || str[i] == ']' || str[i] == '}') {
            if (dque.isEmpty()) {
                return false
            } else {
                if (dque.isEmpty()) {
                    return false
                }
                val temp = str[i]
                val poll = dque.pollFirst()

                if (temp == ')' && poll != '(') {
                    return false
                } else if (temp == '}' && poll != '{') {
                    return false
                } else if (temp == ']' && poll != '[') {
                    return false
                }
            }
        }
    }

    if (!dque.isEmpty()) {
        return false
    }

    return true
} // End of checkString