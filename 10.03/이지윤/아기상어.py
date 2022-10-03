import sys; sys.stdin = open("input.txt")
from collections import deque
SHARK_START_SIZE, SHARK_START_POSITION = 2, 9
FISH_TYPE_NUM = 6
MOVES = [(0, -1), (-1, 0), (1, 0), (0, 1)]

def checkSharkSize() :
    if shark_arr[1]==shark_arr[-1]:
        shark_arr[-1] += 1
        shark_arr[1] = 0
    return

def findBestFishToEat(_fish_arr) -> (bool, int):
    _fish_arr.sort(key=lambda x : (x[1], x[0][1], x[0][0]))
    fish_position, time = _fish_arr[0]
    arr[fish_position[1]][fish_position[0]] = 0
    shark_arr[0] = fish_position
    return True, time


def bfsFindFishToEat(_start_pos) -> (bool, int):
    fish_arr = []
    visited = [[0]*n for _ in range(n)]
    queue = deque([_start_pos])
    visited[_start_pos[1]][_start_pos[0]] = 1
    while queue:
        present = queue.popleft()
        for move in MOVES:
            next_x, next_y = present[0]+move[0], present[1]+move[1]
            if 0<=next_x<n and 0<=next_y<n and not visited[next_y][next_x]:
                if 0 < arr[next_y][next_x] < shark_arr[-1]:
                    fish_arr.append([(next_x, next_y), visited[present[1]][present[0]]])
                elif arr[next_y][next_x] <= shark_arr[-1] and not fish_arr:
                    queue.append((next_x, next_y))
                    visited[next_y][next_x] = visited[present[1]][present[0]] + 1
    if not fish_arr : return False, -1
    else : return findBestFishToEat(_fish_arr=fish_arr)

def solution() -> int:
    total_time = 0
    while True:
        find_result, time = bfsFindFishToEat(shark_arr[0])
        if not find_result : return total_time
        shark_arr[1] += 1
        checkSharkSize()
        total_time += time

# ---------------------------------------------------------------------------
n = int(input())
arr = []
shark_arr = [(0,0), 0, SHARK_START_SIZE]
for row_index in range(n):
    row = list(map(int, input().split()))
    for col_index in range(n):
        if row[col_index]==SHARK_START_POSITION:
            shark_arr[0] = (col_index, row_index)
            row[col_index] = 0
    arr.append(row)
answer = solution()
print(answer)