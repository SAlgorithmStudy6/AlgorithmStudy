import java.util.LinkedList
import java.util.Queue

fun main() {
    Solution().solution(begin = "hit", target = "cog", words = arrayOf("hot", "dot", "dog", "lot", "log"))
}

class Solution {
    fun solution(begin: String, target: String, words: Array<String>): Int {
        var answer = 0
        val queue: Queue<Pair<String, ArrayList<Int>>> = LinkedList()
        if (words.contains(target)) { // 글자 수 하나 다른 단어 선택하기
            for (i in words.indices) {
                var count = 0
                for (j in begin.indices) {
                    if (begin[j] != words[i][j]) count++
                    if (count > 1) break
                }
                if (count == 1) queue.add(Pair(words[i], arrayListOf(i)))
            }
            answer = bfs(queue, target, words) // 탐색 시작
        }
        return answer
    }

    fun bfs(queue: Queue<Pair<String, ArrayList<Int>>>, target: String, words: Array<String>): Int {
        var count = 1

        while (queue.isNotEmpty()) {
            for (i in queue.indices) {
                val info = queue.poll()
                val changeWord = info.first // 바뀐 단어
                val indexList = info.second // 방문한 인덱스 리스트

                if (changeWord == target) return count // poll 했을 때 target이면 count 반환

                for (j in words.indices) {
                    if (!indexList.contains(j)) { // 중복 제거
                        var count = 0
                        for (k in changeWord.indices) {
                            if (changeWord[k] != words[j][k]) count++
                            if (count > 1) break
                        }

                        if (count == 1) { // 문자가 하나만 다르면 queue에 add
                            indexList.add(j)
                            queue.add(Pair(words[j], indexList))
                        }
                    }
                }
            }
            count++
        }
        return count
    }
}