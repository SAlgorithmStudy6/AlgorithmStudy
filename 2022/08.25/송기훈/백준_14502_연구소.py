import sys
import copy
from collections import deque
sys.stdin = open("input.txt", "r", encoding="utf-8")

input = sys.stdin.readline
n, m = map(int, input().split())
matrix = [list(map(int, input().split())) for _ in range(n)]

maxValue = -999999999
dy = [0, 0, 1, -1]
dx = [1, -1, 0, 0]


def bfs():
    global maxValue
    q = deque()
    temp = copy.deepcopy(matrix)
    for y in range(n):
        for x in range(m):
            if temp[y][x] == 2:
                q.append((y, x))
    while q:
        y, x = q.popleft()
        for i in range(4):
            nY = y + dy[i]
            nX = x + dx[i]
            if 0 <= nY < n and 0 <= nX < m and temp[nY][nX] == 0:
                temp[nY][nX] = 2
                q.append((nY, nX))

    count = 0
    for y in range(n):
        for x in range(m):
            if temp[y][x] == 0:
                count += 1

    if maxValue < count:
        maxValue = count


def pickThree(count):
    if count == 3:
        bfs()
        return
    for y in range(n):
        for x in range(m):
            if matrix[y][x] == 0:
                matrix[y][x] = 1
                pickThree(count + 1)
                matrix[y][x] = 0


pickThree(0)

print(maxValue)
