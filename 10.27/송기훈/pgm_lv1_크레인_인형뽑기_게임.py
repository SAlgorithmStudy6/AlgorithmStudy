def solution(board, moves):
    answer = 0
    stack = []
    n = len(board)
    for move in moves:
        move = move - 1
        for y in range(n):
            if (board[y][move] != 0):
                catch = board[y][move]
                board[y][move] = 0
                # stack에 값이 있을 때
                if stack:
                    if stack[-1] == catch:
                        answer += 2
                        stack.pop(-1)
                    else:
                        stack.append(catch)
                # stack에 값이 없을 때
                else:
                    stack.append(catch)
                break

    return answer


board = [[0, 0, 0, 0, 0], [0, 0, 1, 0, 3], [
    0, 2, 5, 0, 1], [4, 2, 4, 4, 2], [3, 5, 1, 3, 1]]

moves = [1, 5, 3, 5, 1, 2, 1, 4]

print(solution(board, moves))
