package BOJ_2357

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.OutputStreamWriter
import java.util.*

// input
private lateinit var br: BufferedReader

// variables
private var N = 0
private var M = 0

fun main() {
    val path = "C:\\Users\\bigyo\\Desktop\\알고리즘\\KotlinAlgo\\src\\main\\kotlin\\BOJ_2357\\res\\2357.txt"
    br = BufferedReader(File(path).bufferedReader())
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val sb = StringBuilder()

    input()

    val arr = IntArray(N + 1)
    for (i in 1..N) {
        arr[i] = br.readLine().toInt()
    }

    val minTree = IntArray(N * 4)
    val maxTree = IntArray(N * 4)

    makeTree(arr, maxTree, 1, 1, N, "max") // 최대 트리 만들기
    makeTree(arr, minTree, 1, 1, N, "min") // 최소 트리 만들기

    while (M-- > 0) {
        val st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()

        sb.append(findVal(minTree, 1, 1, N, a, b, "min"))
            .append(' ')
            .append(findVal(maxTree, 1, 1, N, a, b, "max"))
            .append('\n')
    }

    bw.write(sb.toString())
    bw.close()
} // End of main

private fun makeTree(arr: IntArray, tree: IntArray, node: Int, start: Int, end: Int, mode: String): Int {
    if (start == end) {
        tree[node] = arr[start]
        return tree[node]
    }

    val mid = (start + end) / 2
    val left = makeTree(arr, tree, node * 2, start, mid, mode)
    val right = makeTree(arr, tree, node * 2 + 1, mid + 1, end, mode)

    return when (mode) {
        "max" -> {
            tree[node] = Math.max(left, right)
            tree[node]
        }

        else -> {
            tree[node] = Math.min(left, right)
            tree[node]
        }
    }
} // End of makeTree

private fun findVal(tree: IntArray, node: Int, start: Int, end: Int, left: Int, right: Int, mode: String): Int {
    // 범위 밖
    if (end < left || start > right) {
        return when (mode) {
            "min" -> {
                Int.MAX_VALUE
            }

            else -> {
                0
            }
        }
    }

    // 범위 안
    if (left <= start && end <= right) return tree[node]

    val mid = (start + end) / 2
    val leftVal = findVal(tree, node * 2, start, mid, left, right, mode)
    val rightVal = findVal(tree, node * 2 + 1, mid + 1, end, left, right, mode)

    return when (mode) {
        "min" -> {
            Math.min(leftVal, rightVal)
        }

        else -> {
            Math.max(leftVal, rightVal)
        }
    }
} // End of findVal

private fun input() {
    val st = StringTokenizer(br.readLine())
    N = st.nextToken().toInt() // 정수의 개수
    M = st.nextToken().toInt()
} // End of input
