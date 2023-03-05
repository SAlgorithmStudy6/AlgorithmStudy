import kotlin.math.min

fun main() {
    val edges = arrayOf(
            intArrayOf(2, 4), intArrayOf(1, 2), intArrayOf(6, 8), intArrayOf(1, 3), intArrayOf(5, 7),
            intArrayOf(2, 5), intArrayOf(3, 6), intArrayOf(6, 10), intArrayOf(6, 9)
//            intArrayOf(1, 2), intArrayOf(1, 3)
//                intArrayOf(1,3), intArrayOf(1,2)
    )
    val target =
//            intArrayOf(0, 7, 3)
            intArrayOf(0, 0, 0, 3, 0, 0, 5, 1, 2, 3)
//            intArrayOf(0,7,1)
    Solution().solution(edges, target)
}

class Solution {
    val graph = ArrayList<ArrayList<Int>>()
    lateinit var checked: BooleanArray
    lateinit var finished: BooleanArray
    lateinit var temp: IntArray
    var test = ArrayList<Int>()
    var min = Integer.MAX_VALUE
    val set = HashSet<Int>()
    var answer = ArrayList<Int>()
    fun solution(edges: Array<IntArray>, target: IntArray): IntArray {

        checked = BooleanArray(target.size + 1)
        finished = BooleanArray(target.size + 1)
        temp = IntArray(target.size + 1)

        for (i in 0..target.size) {
            graph.add(ArrayList())
        }

        for (edge in edges) {
            graph[edge[0]].add(edge[1])
        }

        for (i in 1 until target.size) {
            graph[i].sort()
            if (graph[i].isNotEmpty()) checked[graph[i][0]] = true //실선만 이동하기 때문에 이동 노드로 표시해주기
        }
        dfs(1, target, 0, 0)
        return if (answer.isEmpty()) return intArrayOf(-1) else answer.toIntArray()
    }

    fun dfs(node: Int, target: IntArray, sum: Int, count: Int) {

        if (count > min) {
            return
        }

        if (sum == target.sum()) {
            min = min(min, test.size)
            if (answer.isEmpty()) answer.addAll(test)
            else{
                for (i in answer.indices){
                    if (answer[i] > test[i]) {
                        answer.clear()
                        answer.addAll(test)
                        break
                    }
                }
            }
            return
        }

        var currentIdx = -1
        var nextIdx = -1

        if (graph[node].isNotEmpty()){
            run {
                graph[node].forEachIndexed { i, child ->
                    if (checked[child] && graph[node].size > 1) {
                        nextIdx = if (i + 1 == graph[node].size) graph[node][0] else graph[node][i+1]
                        currentIdx = child
                        return@run
                    } else if (graph[node].size == 1) {
                        currentIdx = graph[node][0]
                    }
                }
            }
        } else currentIdx = 1


        if (graph[node].isNotEmpty()) {
            if (nextIdx != -1){
                checked[currentIdx] = false
                checked[nextIdx] = true
            }
            dfs(currentIdx, target, sum, count)
            if (nextIdx != -1){
                checked[currentIdx] = true
                checked[nextIdx] = false
            }
        } else {
            set.add(node)
            for (j in 3 downTo 1) {
                    if (temp[node] + j < target[node - 1]) {
                        temp[node] += j
                        test.add(j)
                        if (nextIdx != -1){
                            checked[currentIdx] = false
                            checked[nextIdx] = true
                        }
                        dfs(currentIdx, target, sum + j, count + 1)
                        if (nextIdx != -1){
                            checked[currentIdx] = true
                            checked[nextIdx] = false
                        }
                        temp[node] -= j
                        test.removeAt(test.size - 1)
                    }else if (temp[node] + j == target[node - 1] && checkSum(node,target)){
                        temp[node] += j
                        test.add(j)
                        if (nextIdx != -1){
                            checked[currentIdx] = false
                            checked[nextIdx] = true
                        }
                        dfs(currentIdx, target, sum + j, count + 1)
                        if (nextIdx != -1){
                            checked[currentIdx] = true
                            checked[nextIdx] = false
                        }
                        temp[node] -= j
                        test.removeAt(test.size - 1)
                    }
            }
        }
    }

    fun checkSum(node: Int,target: IntArray) : Boolean{
        val index = set.indexOf(node)
        val nextIdx = if (index + 1 == set.size) 0 else index+1
        if (target[set.elementAt(nextIdx)-1] - temp[set.elementAt(nextIdx)] > 3) return false
        return true
    }
}