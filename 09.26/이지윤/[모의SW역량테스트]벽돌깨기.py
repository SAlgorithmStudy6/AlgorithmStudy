import sys; sys.stdin = open("input.txt")
from itertools import product
from collections import deque
from copy import deepcopy

MOVES = [(-1,0), (1,0), (0,-1), (0,1)]   # 좌, 우 ,상, 하

def count_left_block(x_len, y_len, _arr) -> int:
    count_left = 0
    for y in range(y_len):
        for x in range(x_len):
            if _arr[y][x] :
                count_left+=1
    return count_left

def pop_visited_blocks(_visited, x_len, y_len, _arr) -> list:
    for y in range(y_len):
        count_pop = 0
        for x in range(x_len-1, -1, -1):
            if _visited[y][x] :
                _arr[y].pop(x)
                count_pop += 1
        _arr[y] = [0]*count_pop + _arr[y]
    return _arr

def find_first_block_position(_start_pos, _arr) -> tuple:
    for x_index in range(len(_arr[_start_pos[1]])):
        if _arr[_start_pos[1]][x_index] :
            return x_index, _start_pos[1]
    return -1, -1

def bfs(_start_pos, x_len, y_len, _arr) -> list:
    visited = [[False]*x_len for _ in range(y_len)]
    first_position = find_first_block_position(_start_pos, _arr)
    if first_position[0]<0 : return _arr
    queue = deque()
    queue.append(first_position)
    visited[first_position[1]][first_position[0]] = True
    while queue:
        present = queue.popleft()
        block_num = _arr[present[1]][present[0]]
        for move_delta in MOVES:
            for block_range in range(1, block_num):
                move_x = move_delta[0]*block_range
                move_y = move_delta[1]*block_range
                next_x = present[0] + move_x
                next_y = present[1] + move_y
                if 0<=next_x<x_len and 0<=next_y<y_len and not visited[next_y][next_x] :
                    queue.append((next_x, next_y))
                    visited[next_y][next_x] = True
    return pop_visited_blocks(visited, x_len, y_len, _arr)

def solution(_n, x_len, y_len, _arr) -> int:
    min_count = float("inf")
    start_pos_arr = [(0,y) for y in range(w)]
    for start_infos in product(start_pos_arr, repeat=_n) :
        result_arr = deepcopy(_arr)
        for start in start_infos:
            result_arr = bfs(start, x_len, y_len, result_arr)
            min_count = min(min_count, count_left_block(x_len, y_len, result_arr))
            if not min_count: return min_count
    return min_count

def transpose_matrix(_col, _row, _arr) -> list:
    temp=[[0]*_row for _ in range(_col)]
    for i in range(_row):
        for j in range(_col):
            temp[j][i] = _arr[i][j]
    return temp

T = int(input())
for t in range(1, T + 1):
    n, w, h = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(h)]
    col_arr = transpose_matrix(w, h, arr)
    answer = solution(n, h, w, deepcopy(col_arr))
    print(f"#{t} {answer}")
