package com.ssafy.algorithm

import java.util.*
import kotlin.collections.HashMap

fun main() {
    val N = 5
    val road = arrayOf(
        intArrayOf(1, 2, 1), intArrayOf(2, 3, 3), intArrayOf(5, 2, 2), intArrayOf(1, 4, 2),
        intArrayOf(5, 3, 1), intArrayOf(5, 4, 2)
    )
    val K = 3
    println(Solution().solution(N, road, K))
}

class Solution {
    lateinit var graph: Array<HashMap<Int, Int>>
    lateinit var visited: IntArray  //음식 배달한 가능 시간 안에 들어오는 배열
    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {

        graph = Array(N + 1) { HashMap() }

        for (info in road) { //양방향 그래프 정보 입력

            if(graph[info[0]].containsKey(info[1])){ //최소거리로 갱신하기
                if(graph[info[0]][info[1]]!! > info[2]){
                    graph[info[0]][info[1]] = info[2]
                    graph[info[1]][info[0]] = info[2]
                }
            }else{
                graph[info[0]][info[1]] = info[2]
                graph[info[1]][info[0]] = info[2]
            }
        }

        visited = IntArray(N + 1){-1}

        bfs(1) //탐색 시작

        return visited.filter { it != -1 && it <= k }.size
    }

    fun bfs(node : Int) {
        visited[node] = 0
        var queue : Queue<Int> = LinkedList()
        queue.add(node)

        while (queue.isNotEmpty()){

            val qPoll = queue.poll()

            for (next in graph[qPoll].keys){
                var distance = graph[qPoll][next] //거리

                if(visited[next]==-1){ //방문했던 곳이 아니면 초기화
                    queue.add(next)
                    visited[next] = distance!!+visited[qPoll]
                }else if(visited[next] > visited[qPoll]+distance!!){ //이미 방문한 노드면 최소거리로 갱신
                    visited[next] = visited[qPoll]+distance
                    queue.add(next)
                }
            }
        }
    }
}