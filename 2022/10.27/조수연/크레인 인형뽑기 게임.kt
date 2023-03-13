package com.ssafy.algorithm

import java.util.Stack


fun main() {
    val board =
        arrayOf(
            intArrayOf(0, 0, 0, 0, 0), intArrayOf(0, 0, 1, 0, 3), intArrayOf(0, 2, 5, 0, 1),
            intArrayOf(4, 2, 4, 4, 2), intArrayOf(3, 5, 1, 3, 1)
        )
    val moves = intArrayOf(1, 5, 3, 5, 1, 2, 1, 4)
    println(Solution().solution(board, moves))
}


class Solution {
    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        var answer = 0
        val stack = Stack<Int>()

        //뽑기 시작
        for (line in moves) {
            for (i in board.indices) {
                if (board[i][line-1] != 0) { //해당 영역에 인형이 있으면 뽑기!
                    if(stack.isEmpty()){
                        stack.push(board[i][line-1])
                    }else{
                        //인형을 뽑았는데 마지막부분이 같은 인형이 아니면 추가
                        if (stack.peek() != board[i][line-1]) stack.push(board[i][line-1])
                        else { //같은 인형이면 터트리기
                            stack.pop()
                            answer += 2
                        }
                    }
                    board[i][line-1] = 0 //0으로 초기화
                    break
                }
            }
        }
        return answer
    }
}