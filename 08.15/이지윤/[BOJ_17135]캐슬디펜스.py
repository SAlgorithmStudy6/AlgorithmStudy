from itertools import combinations
from collections import deque
from copy import deepcopy

move = [(-1, 0), (0, -1), (1, 0)]    # 좌, 상, 우 (왼쪽일수록 가장 우선순위 높음)

def check_search_need(_arr) -> bool:
    for row in _arr:
        if 1 in row:
            return True
    return False

def bfs(_distance, _x, _y, _arr) -> tuple:
    start_pos = (_x, _y)
    if _arr[start_pos[1]][start_pos[0]] ==1 : return start_pos

    queue = deque()
    queue.append(start_pos)
    while queue and check_search_need(_arr) :
        present_pos = queue.pop()
        #if _arr[present_pos[1]][present_pos[0]] == 1 : return present_pos
        for _dis in range(1,_distance+1):
            for dx, dy in move:
                next_x, next_y = present_pos[0] + _dis*dx, present_pos[1] + _dis*dy
                if 0 <= next_x < m and _y-_distance+1 <= next_y and not _arr[next_y][next_x] < 0:
                    if _arr[next_y][next_x] == 1 : return next_x, next_y
                    if _dis==1 :
                        queue.append((next_x, next_y))
                        _arr[next_y][next_x] = -1
    return -1, -1


def game(_distance, _defense_arr, _arr) -> int:
    count, row_index = 0, n-1
    while _arr and row_index>=0 and check_search_need(_arr):    # and (1 in _arr)
        deleted_enemies = set()
        for col_index in range(m):
            if _defense_arr[col_index]: # 궁수
                pos = bfs(_distance, col_index, row_index, deepcopy(_arr))
                if not pos[0]<0 and not pos[1]<0:
                    deleted_enemies.add(pos)
        for enemy in deleted_enemies:
            _arr[enemy[1]][enemy[0]] = -1
            count+=1
        _arr.pop()
        row_index-=1
    return count

def solution(_n, _m, _d, _arr) -> int:
    total_num = 0
    for combs in combinations(range(_m), 3):
        defense = [True if i in combs else False for i in range(_m)]
        total_num = max(total_num, game(_d, defense, deepcopy(_arr)))
    return total_num

n,m,d = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]
answer = solution(n, m, d, arr)
print(answer)
