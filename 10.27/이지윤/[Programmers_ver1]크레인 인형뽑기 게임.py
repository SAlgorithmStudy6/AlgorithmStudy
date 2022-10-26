def makeBoardToStack(board) -> list:
    board_len = len(board)
    stack_arr = [[] for _ in range(board_len+1)]
    for row_i in range(board_len-1, -1, -1):
        for col_i in range(board_len-1, -1, -1):
            if board[row_i][col_i] :
                stack_arr[col_i+1].append(board[row_i][col_i])
    return stack_arr

def solution(board, moves):
    answer = 0
    basket_stack = []
    board_stack = makeBoardToStack(board)
    for selected_i in moves:
        doll = board_stack[selected_i].pop() if board_stack[selected_i] else 0
        if basket_stack and basket_stack[-1]==doll:
            basket_stack.pop()
            answer += 2
        elif doll :
            basket_stack.append(doll)
    return answer