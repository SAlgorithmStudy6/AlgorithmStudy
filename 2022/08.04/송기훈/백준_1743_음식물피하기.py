import sys
from collections import deque

input = sys.stdin.readline

N, M, K = map(int, input().split())

matrix = [[0] * M for _ in range(N)]

dy = [0, 0, 1, -1]
dx = [1, -1, 0, 0]

for _ in range(K):
    y, x = map(int, input().split())
    matrix[y - 1][x - 1] = 1


def bfs(y, x, matrix):
    q = deque([[y, x]])
    matrix[y][x] = -1
    trashLength = 1

    while q:
        y, x = q.popleft()

        for i in range(4):
            moveY = y + dy[i]
            moveX = x + dx[i]
            if 0 <= moveY < N and 0 <= moveX < M and matrix[moveY][moveX] == 1:
                q.append([moveY, moveX])
                matrix[moveY][moveX] = -1
                trashLength += 1
    return trashLength

maxTrash = 0

for i in range(N):
    for j in range(M):
        if matrix[i][j] == 1:
            temp = bfs(i, j, matrix)
            maxTrash = max(temp, maxTrash)

print(maxTrash)
