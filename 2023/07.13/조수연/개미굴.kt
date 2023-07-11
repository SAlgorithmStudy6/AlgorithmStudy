import java.io.*
import java.util.*
import kotlin.collections.HashMap

data class Node(val children: HashMap<String, Node> = HashMap())

val bw = BufferedWriter(OutputStreamWriter(System.out))

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {

    val n = readLine().toInt()
    val root = Node()

    repeat(n) {
        val token = StringTokenizer(readLine())
        val k = token.nextToken().toInt()
        var current = root

        repeat(k) {
            val word = token.nextToken()
            if (!current.children.containsKey(word)) { // 해당 층에 word가 없으면 추가
                current.children[word] = Node()
            }
            current = current.children[word]!! // 층 갱신
        }
    }

    printNode(root, "")

    bw.flush()
    bw.close()
}

fun printNode(root: Node, bars: String) {
    val keys = root.children.keys.sorted()

    for (i in keys.indices) {
        bw.write("$bars${keys[i]}\n")
        printNode(root.children[keys[i]]!!, "$bars--")
    }
}
