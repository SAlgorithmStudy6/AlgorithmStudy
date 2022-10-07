import sys; sys.stdin = open("input.txt")
from collections import deque
from copy import deepcopy

# MOVES = [[(1,0), (0,-1), (-1,0), (0,1)], [(1,0), (0,1), (-1,0), (0,-1)]]     # 반시계,시계
MOVES = [[(0,-1), (1,0), (0,1), (-1,0)], [(0,1), (1,0), (0,-1), (-1,0)]]    # 시계, 반시계 (반대)

def countDust(arr) -> int:
    count = 0
    for y in range(r):
        for x in range(c):
            if arr[y][x] > 0:
                count += arr[y][x]
    return count

def checkRange(_cleaner_index, _next_x, _next_y, _cleaner_y):
    if not _cleaner_index%2:
        if 0<=_next_x<c and 0<=_next_y<=_cleaner_y : return True
        else : return False
    else :
        if 0<=_next_x<c and _cleaner_y<=_next_y<r : return True
        else : return False

def activateCleaner(arr) -> list:
    for cleaner_index in range(len(cleaner)):
        cleaner_x, cleaner_y = 0, cleaner[cleaner_index]
        queue = deque([(cleaner_x + MOVES[cleaner_index][0][0], cleaner_y + MOVES[cleaner_index][0][1])])
        move_index = 0
        while queue:
            present_x, present_y = queue.popleft()
            delta = MOVES[cleaner_index][move_index]
            if not checkRange(cleaner_index, present_x + delta[0], present_y + delta[1], cleaner_y):
                move_index = (move_index+1)%4
                delta = MOVES[cleaner_index][move_index]
            next_x, next_y = present_x + delta[0], present_y + delta[1]
            if next_x==cleaner_x and next_y==cleaner_y:
                arr[present_y][present_x] = 0
                break
            arr[present_y][present_x] = arr[next_y][next_x]
            queue.append((next_x, next_y))
    return arr


def spreadDust(arr) -> list:
    _arr = [[0]*c for _ in range(r)]
    _arr[cleaner[0]][0], _arr[cleaner[1]][0] = -1, -1
    for y in range(r):
        for x in range(c):
            dust = arr[y][x]
            if dust==-1: continue
            if not dust//5:
                _arr[y][x] += arr[y][x]
                continue
            neighbor_count = 0
            for move in MOVES[0]:
                neighbor = (x+move[0], y+move[1])
                if 0<=neighbor[0]<c and 0<=neighbor[1]<r and arr[neighbor[1]][neighbor[0]]!=-1:
                    _arr[neighbor[1]][neighbor[0]] += dust//5
                    neighbor_count += 1
            _arr[y][x] += dust - neighbor_count*(dust//5)
    return _arr



def solution(arr) -> int:
    for _ in range(t):
        arr = spreadDust(arr)
        arr = activateCleaner(arr)
    return countDust(arr)


r, c, t = map(int, input().split())
arr_info = []
cleaner = []    # always 0열
for row_index in range(r):
    row = list(map(int, input().split()))
    if row[0]==-1 : cleaner.append(row_index)
    arr_info.append(row)

answer = solution(arr_info)
print(answer)