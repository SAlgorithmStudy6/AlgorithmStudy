package com.example.algorithm

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

lateinit var childGraph : Array<ArrayList<Int>> //자식 정보를 담는 인접리스트
lateinit var parentArr : IntArray //해당 노드가 가지는 부모 정보를 담는 배열
lateinit var visited : BooleanArray //노드 방문 체크 배열

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N = br.readLine().toInt()
    var count = 0

    childGraph = Array(N){ArrayList()}

    parentArr = IntArray(N){-1}
    visited = BooleanArray(N)

    val token = StringTokenizer(br.readLine())

    repeat(N){
        val parent = token.nextToken().toInt()

        if (parent != -1){
            childGraph[parent].add(it)
            parentArr[it] = parent
        }

    }

    val rNode = br.readLine().toInt()

    dfs(rNode)

    for (i in childGraph.indices){ //자식노드가 없고 방문한 기록이 없으면 ++
        if (childGraph[i].isEmpty() && !visited[i]) count++
    }

    println(count)
}

fun dfs(remove : Int){
    val stack = Stack<Int>()

    if (parentArr[remove] != -1){
        childGraph[parentArr[remove]].remove(remove) //해당 자식을 가지는 부모 노드에서 자식 노드 제거
    }

    visited[remove] = true

    for (node in childGraph[remove]){ //제거하는 노드의 자식 노드들을 스택에 추가
        stack.push(node)
    }

    while (childGraph[remove].isNotEmpty()){ //해당 노드에 자식들을 제거
        childGraph[remove].removeAt(0)
    }


    while (stack.isNotEmpty()){
        val node = stack.pop()

        visited[node] = true

        for (childNode in childGraph[node]){
            if (!visited[childNode]){
                stack.push(childNode)
            }
        }

        while (childGraph[node].isNotEmpty()){
            childGraph[node].removeAt(0)
        }
    }
}