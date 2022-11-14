import sys
from collections import deque

sys.stdin = open("input.txt", "r", encoding="UTF-8")

# 상 하 좌 우
dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

matrix = [list(input()) for _ in range(12)]

original = [[0 for _ in range(6)] for _ in range(12)]


def bfs(y, x):
    q = deque()
    tempMatrix = [item[:] for item in original]
    q.append([y, x])
    combo = 0
    count = 1

    tempMatrix[y][x] = count
    while q:
        nowY, nowX = q.popleft()
        for d in range(4):
            nY = nowY + dy[d]
            nX = nowX + dx[d]
            if 0 <= nY < 12 and 0 <= nX < 6:
                if (matrix[nY][nX] == matrix[nowY][nowX]
                        and tempMatrix[nY][nX] == 0):
                    count += 1
                    tempMatrix[nY][nX] = 1
                    q.append([nY, nX])

    if count >= 4:
        combo += 1
        for i in range(12):
            for j in range(6):
                if tempMatrix[i][j] == 1:
                    matrix[i][j] = "."

    return combo


def down():
    for x in range(6):
        q = deque()
        for y in range(11, -1, -1):
            if matrix[y][x] != ".":
                q.append(matrix[y][x])
        for y in range(11, -1, -1):
            if q:
                matrix[y][x] = q.popleft()
            else:
                matrix[y][x] = "."


result = 0
while True:
    flag = 0
    for y in range(12):
        for x in range(6):
            if matrix[y][x] != ".":
                flag += bfs(y, x)

    if flag == 0:
        print(result)
        break
    else:
        result += 1

    down()
