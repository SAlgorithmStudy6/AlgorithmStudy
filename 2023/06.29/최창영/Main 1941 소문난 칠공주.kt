package 백트래킹_부수기

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter

/*
    7명의 자리는 가로나 세로로 인접해 있어야 한다.
    화합과 번영을 위해서 반드시 이다솜파의 학생들로만 구성될 필요는 없다.
    그러나 생존을 위해서 이다솜파가 반드시 우위를 점해야 한다.
    7명의 학생 중 이다솜파의 학생이 적어도 4명 이상은 반드시 포함되어야 한다.

    S가 최소 4개 이상 즉, Y는 최대 3개
    7개를 인접

    5*5 행렬
    S는 이다솜파의 학생
    Y는 임도연파의 학생을 나타냄
 */

// input
private lateinit var br: BufferedReader

// variables
private var N = 5
private var ans = 0

private var map = Array(N) { CharArray(N) }
private var isVisited = Array(N) { BooleanArray(N) }
private var checkArray = CharArray(7)
private var dirX = arrayOf(-1, 1, 0, 0)
private var dirY = arrayOf(0, 0, -1, 1)
private var set = HashSet<String>()

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\백트래킹_부수기\\res\\1941.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    input()

    DFS(0)

    sb.append(ans)
    bw.write(sb.toString())
    bw.close()
} // End of main

private fun DFS(depth: Int) {
    if (depth == 7) {
        var cnt = 0
        val sb = StringBuilder()

        for (i in 0 until N) {
            for (j in 0 until N) {
                if (isVisited[i][j]) {
                    sb.append(i).append(j)
                    if (map[i][j] == 'S') {
                        cnt++
                    }
                }
            }
        }

        if (cnt >= 4 && !set.contains(sb.toString())) {
            set.add(sb.toString())
            ans++
            return
        }

        return
    }

    for (i in 0 until N) {
        for (j in 0 until N) {

            if (depth != 0) {
                var cnt = 0
                for (k in 0 until 4) {
                    val nextX = dirX[k] + i
                    val nextY = dirY[k] + j

                    if (!rangeCheck(nextX, nextY)) continue
                    if (isVisited[nextX][nextY]) cnt++
                }
                if (cnt == 0) continue
            }

            if (isVisited[i][j]) continue

            isVisited[i][j] = true
            // 백트래킹의 핵심은 가지치기
            // 25C7 조합
            checkArray[depth] = map[i][j]
            DFS(depth + 1)
            isVisited[i][j] = false
        }
    }
} // End of DFS

private fun rangeCheck(nextX: Int, nextY: Int): Boolean {
    return nextX < N && nextX >= 0 && nextY < N && nextY >= 0
} // End of rangeCheck

private fun input() {
    for (i in 0 until N) {
        map[i] = br.readLine().toCharArray()
    }
} // End of input
