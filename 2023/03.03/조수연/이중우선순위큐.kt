import java.util.*

fun main() {
    val operations = arrayOf("I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333")
    Solution().solution(operations)
}

class Solution {
    fun solution(operations: Array<String>): IntArray {
        var answer: IntArray
        val minPq = PriorityQueue<Int>() //오름차순
        val maxPq = PriorityQueue<Int>(Collections.reverseOrder()) //내림차순
        for (operation in operations) {
            val command = operation.split(" ")[0] //삽입, 삭제 명령어
            val data = operation.split(" ")[1].toInt()

            when (command) {
                "I" -> {
                    minPq.offer(data)
                    maxPq.offer(data)
                }
                "D" -> {
                    when (data) {
                        1 -> {
                            if (maxPq.isNotEmpty()) {
                                minPq.remove(maxPq.peek()) //minPq에서 maxPq.peek 부분도 같이 제거해주기
                                maxPq.poll()
                            }
                        }
                        -1 -> {
                            if (minPq.isNotEmpty()) {
                                maxPq.remove(minPq.peek()) //maxPq에서 minPq.peek 부분도 같이 제거해주기
                                minPq.poll()
                            }
                        }
                    }
                }
            }
        }
        if (maxPq.isEmpty() && minPq.isEmpty()) answer = intArrayOf(0, 0) //큐가 비어있으면 0,0
        else answer = intArrayOf(maxPq.peek(), minPq.peek())
        return answer
    }
}