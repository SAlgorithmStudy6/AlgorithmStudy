import java.io.*
import java.util.*

var answer = 0
val visited = BooleanArray(25)
val students = Array(25) { CharArray(5) }
val dy = intArrayOf(-1,1,0,0)
val dx = intArrayOf(0,0,-1,1)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(5) { students[it] = readLine().toCharArray() }

    dfs(0, 0, IntArray(7) { -1 })

    bw.write( "$answer")

    bw.flush()
    bw.close()
}

fun dfs(size: Int, start:Int,  members: IntArray) {

    if (size == 7) {
        if (check(members)) answer++
        return
    }

    for (i in start until 25){
        if(!visited[i]){
            visited[i] = true
            members[size] = i
            dfs(size+1,i+1,members)
            visited[i] = false
        }
    }
}

fun check(members: IntArray) : Boolean{
    var yCount = 0
    for (index in members){
        if (students[index / 5][index % 5] == 'Y') yCount++
    }

    if (yCount > 3) return false // S가 적어도 4개이상 있어야한다.

    val queue : Queue<Int> = LinkedList()
    queue.offer(members[0])
    var memberList = members.toMutableList()

    while (queue.isNotEmpty()){
        val location = queue.poll() // 현재 위치
        for (i in 0 until 4){
            val nextX = location % 5 + dx[i]
            val nextY = location / 5 + dy[i]

            if (nextX in 0 ..4 && nextY in 0..4){
                if (memberList.contains(nextY*5+nextX)){ // 이동한 위치에 memberList에 멤버가 있으면 다음 탐색
                    memberList.remove(nextY*5+nextX)
                    queue.offer(nextY*5+nextX)
                }
            }
        }
    }

    if (memberList.isNotEmpty()) return false // 인접한 멤버가 아니면 false
    return true
}