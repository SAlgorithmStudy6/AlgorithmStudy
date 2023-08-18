import java.io.*
import java.util.*
import kotlin.collections.HashMap

lateinit var st: StringTokenizer
lateinit var relation: HashMap<String,String>
lateinit var count: HashMap<String,Int>

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val t = readLine().toInt()

    repeat(t) {
        val f = readLine().toInt()
        relation = HashMap()
        count = HashMap()
        
        repeat(f) {
            st = StringTokenizer(readLine())

            val p1 = st.nextToken()
            val p2 = st.nextToken()

            relation.putIfAbsent(p1,p1)
            count.putIfAbsent(p1,1)

            relation.putIfAbsent(p2,p2)
            count.putIfAbsent(p2,1)

            union(p1,p2)

            bw.write("${count[find(p1)]!!}\n")
        }
    }

    bw.flush()
    bw.close()
}

/* 분리집합 */
fun union(x: String, y: String) {
    val nx = find(x)
    val ny = find(y)

    if (nx != ny) {
        relation[ny] = nx
        count.replace(nx,count[ny]!! + count[nx]!!)
    }
}

fun find(x: String): String {
    return if (relation[x] == x) x
    else {
        relation[x] = find(relation[x]!!)
        relation[x]!!
    }
}