package `DFS&BFS 부수기`

/*
    트리에서 리프노드란, 자식의 개수가 0인 노드를 의미한다.
    트리가 주어졌을 때, 노드를 하나 지운다. 그 때, 남은 트리에서 리프 노드의 개수를 구하는 프로그램을 작성하시오.

    노드를 지우면 그 노드와 노드의 모든 자손이 트리에서 제거된다.
    노드의 개수 N은 50보다 작은 자연수

    첫째 줄에 입력을 주어진 트리에서 입력으로 주어진 노드를 지웠을 때, 리프 노드의 개수를 출력한다.
 */

import java.util.*
import java.io.*

private var N = 0
private val arr = IntArray(50)
private val isVisited = BooleanArray(50)

fun main() {
    val path = "C:\\Users\\Samsung\\Desktop\\코틀린 알고리즘\\src\\main\\kotlin\\DFS&BFS 부수기\\res\\1068.txt"
    val br = BufferedReader(File(path).bufferedReader())

    N = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())

    for (i in 0 until N) {
        arr[i] = st.nextToken().toInt()
        // 배열에서 index는 각 노드의 번호가 되고, value는 부모 노드의 번호가 된다.
    }

    // 루트 노드는 항상 -1의 값을 가진다.
    val deleteNodeNum = br.readLine().toInt()
    // 루트 노드를 제거하면 리프노드는 존재할 수 없다.
    if (arr[deleteNodeNum] == -1) {
        println(0)
        return
    }

    // 시작하는 노드의 번호 -> 지울 노드의 번호
    DFS(deleteNodeNum)

    // 제거된 노드를 반영하여 자신의 노드 번호를 가지고 있는 노드를 찾는다.
    val isParent = BooleanArray(N)
    for (i in 0 until N) {
        if (arr[i] == -1 || isVisited[i]) continue // 루트 노드는 통과,

        isParent[arr[i]] = true
    }

    // 둘다 false인 값
    var count = 0
    for (i in 0 until N) {
        if (isParent[i] == false && isVisited[i] == false) {
            count++
        }
    }

    println(count)
} // End of main

/*
    배열에서
    value는 자신의 부모 노드 번호를 의미하고
    index는 해당 노드의 번호를 의미한다.
 */
private fun DFS(startNum: Int) {
    isVisited[startNum] = true
    // 부모 노드의 번호를 가진 값들로 모두 DFS탐색

    for (i in 0 until N) {
        if (arr[i] == startNum) {
            isVisited[i] = true
            DFS(i)
        }
    }
} // End of DFS