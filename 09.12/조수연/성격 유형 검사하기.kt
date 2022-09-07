import java.lang.StringBuilder
import kotlin.collections.ArrayList

fun main() {
    val sl = Solution()
    val survey = arrayOf("TR", "RT", "TR")
    val choices = intArrayOf(7, 1, 3)

    println(sl.solution(survey, choices))
}

class Solution {
    lateinit var paper: ArrayList<Pair<Char, Int>>
    fun solution(survey: Array<String>, choices: IntArray): String {
        val sb = StringBuilder()

        val arr = arrayOf('R', 'T', 'C', 'F', 'J', 'M', 'A', 'N')
        paper = ArrayList()

        for (i in 0..7) { //초기 세팅
            paper.add(Pair(arr[i], 0))
        }

        for (i in survey.indices) { //성격 유형 점수 갱신
            getScore(survey[i], choices[i])
        }

        for (i in paper.indices step 2){ //성격 유형 만들기
            val score = paper[i].second
            val score2 = paper[i+1].second

            if (score >= score2) sb.append(paper[i].first)
            else sb.append(paper[i+1].first)
        }

        var answer  = sb.toString()

        return answer
    }

    fun getScore(personality: String, score: Int) {
        when (score) {
            1 ->
                for (i in paper.indices) {
                    if (personality[0] == paper[i].first) {
                        paper.set(i, Pair(paper[i].first, paper[i].second + 3))
                    }
                }
            2 ->
                for (i in paper.indices) {
                    if (personality[0] == paper[i].first) {
                        paper.set(i, Pair(paper[i].first, paper[i].second + 2))
                    }
                }
            3 ->
                for (i in paper.indices) {
                    if (personality[0] == paper[i].first) {
                        paper.set(i, Pair(paper[i].first, paper[i].second + 1))
                    }
                }
            5 ->
                for (i in paper.indices) {
                    if (personality[1] == paper[i].first) {
                        paper.set(i, Pair(paper[i].first, paper[i].second + 1))
                    }
                }
            6 ->
                for (i in paper.indices) {
                    if (personality[1] == paper[i].first) {
                        paper.set(i, Pair(paper[i].first, paper[i].second + 2))
                    }
                }
            7 ->
                for (i in paper.indices) {
                    if (personality[1] == paper[i].first) {
                        paper.set(i, Pair(paper[i].first, paper[i].second + 3))
                    }
                }
        }

    }
}













