package BOJ_14725_개미굴

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

// input
private lateinit var br: BufferedReader

// variables
private const val FLOOR = "--"
private var N = 0
private var K = 0
private var ans = StringBuilder()

private data class Trie(var name: String, var edge: ArrayList<Trie>)

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\BOJ_14725_개미굴\\res.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    input()

    solve()

    bw.write(ans.toString())
    bw.close()
} // End of main

private fun DFS(trie: Trie, depth: Int) {
    val comp = Comparator<Trie> { o1, o2 -> o1!!.name.compareTo(o2!!.name) }
    trie.edge.sortWith(comp)

    if (depth != -1) {
        for (i in 0 until depth) {
            ans.append(FLOOR)
        }
        ans.append(trie.name).append('\n')
    }

    trie.edge.forEach {
        DFS(it, depth + 1)
    }
} // End of DFS

private fun solve() {
    var trie = Trie("", ArrayList())
    var node: Trie

    while (N-- > 0) {
        val st = StringTokenizer(br.readLine())
        K = st.nextToken().toInt()
        node = trie

        while (K-- > 0) {
            var name = st.nextToken()
            var idx = -1

            var size = node.edge.size
            for (i in 0 until size) {
                if (node.edge[i].name == name) {
                    idx = i
                    break
                }
            }

            if (idx == -1) {
                node.edge.add(Trie(name, ArrayList()))
                node = node.edge[node.edge.size - 1]
            } else {
                node = node.edge[idx]
            }
        }
    }

    DFS(trie, -1)
} // End of solve

private fun input() {
    N = br.readLine().toInt()
} // End of input
