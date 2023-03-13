# import sys
# sys.stdin = open("input.txt")

from copy import deepcopy

from collections import deque
move = [(1, 0), (0, 1), (-1, 0), (0, -1)] # 오른쪽, 아래, 왼쪽, 위  (4방향 다 가야함)




def bfs(_start, _h, _n, _arr) -> list:
    queue = deque()
    queue.append(_start)
    while queue:
        x, y = queue.popleft()
        for direction in move:
            nx, ny = x + direction[0], y+direction[1]
            if 0<=nx<_n and 0<=ny<_n and _arr[ny][nx]>_h:
                queue.append((nx, ny))
                _arr[ny][nx] = -1
    return _arr

def solution(_h, _n, _arr) -> int :
    area_count = 0
    for y in range(_n):
        for x in range(_n):
            if _arr[y][x] > h:
                pos = (x, y)
                _arr[y][x] = -1
                _arr = bfs(pos, _h, _n, _arr)
                area_count += 1
    return area_count



n = int(input())
max_height = 0

arr = []
answer = []
for _ in range(n):
    row = list(map(int, input().split()))
    max_height = max(max_height, max(row))
    arr.append(row)

for h in range(0, max_height):                         # 주의 : 비가 전혀 오지 않을 경우도 고려 해야 한다. =>  max_height를 2부터 했더니 valueError!
    answer.append(solution(h, n, deepcopy(arr)))       # arr.copy()도 얕은 복사 메소드
print(max(answer))
