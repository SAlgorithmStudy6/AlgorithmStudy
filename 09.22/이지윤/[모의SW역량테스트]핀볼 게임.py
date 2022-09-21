import sys; sys.stdin = open("input.txt")
MOVES = [(0,-1), (1,0), (0,1), (-1,0)]      # [시계] 상(0), 우(1), 하(2), 좌(3)

BLOCK_VARIATIONS = [[2, 3, 0, 1],
                    [2, 3, 1, 0],
                    [1, 3, 0, 2],
                    [3, 2, 0, 1],
                    [2, 0, 3, 1],
                    [2, 3, 0, 1]]

def playGame(_start_move_index, _start_position) -> int:
    x, y = _start_position[0], _start_position[1]
    move_index = _start_move_index
    score = 0
    while True:
        x, y = x+MOVES[move_index][0], y+MOVES[move_index][1]
        if x==_start_position[0] and y==_start_position[1]:
            return score
        if not 0<=x<n or not 0<=y<n:
            move_index = BLOCK_VARIATIONS[0][move_index]
            score+=1
            continue
        if arr_info[y][x]<0:
            return score
        elif arr_info[y][x]==0: continue
        elif 6<=arr_info[y][x]<=10:
            warm_hole = warm_hole_info.get(arr_info[y][x])
            if x==warm_hole[0][0] and y==warm_hole[0][1] :
                x, y = warm_hole[1][0], warm_hole[1][1]
            else : x, y = warm_hole[0][0], warm_hole[0][1]
        else :
            move_index = BLOCK_VARIATIONS[arr_info[y][x]][move_index]
            score+=1

def solution() -> int:
    max_score = float("-inf")
    for y in range(n):
        for x in range(n):
            if not arr_info[y][x] :
                start = (x, y)
                for move_index in range(len(MOVES)):
                    score = playGame(move_index, start)
                    max_score = max(max_score, score)
    return max_score


T = int(input())
for t in range(1,T+1):
    n = int(input())
    arr_info = []
    warm_hole_n, warm_hole_info = 0, dict()
    for row_index in range(n):
        row = list(map(int, input().split()))
        for col_index in range(n):
            if 6<=row[col_index]:
                info = warm_hole_info.get(row[col_index], [])
                if not info: warm_hole_n += 1
                warm_hole_info[row[col_index]] = info + [(col_index, row_index)]
        arr_info.append(row)
    answer = solution()
    print(f"#{t} {answer}")
