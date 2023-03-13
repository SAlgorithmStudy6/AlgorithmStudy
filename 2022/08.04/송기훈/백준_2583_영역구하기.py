import sys
sys.setrecursionlimit(10**9)
input = sys.stdin.readline

M, N, K = map(int, input().split())

matrix = [[0] * N for _ in range(M)]

dy = [0, 0, 1, -1]
dx = [1, -1, 0, 0]

for _ in range(K):
    x1, y1, x2, y2 = map(int, input().split())
    for i in range(y1, y2):
        for j in range(x1, x2):
            matrix[i][j] = 1


def dfs(y, x, area):
    matrix[y][x] = 1
    for i in range(4):
        moveY = y + dy[i]
        moveX = x + dx[i]
        if 0 <= moveY < M and 0 <= moveX < N and matrix[moveY][moveX] == 0:
            area = dfs(moveY, moveX, area + 1)
    return area


areaList = []

for i in range(M):
    for j in range(N):
        if matrix[i][j] == 0:
            temp = dfs(i, j, 1)
            areaList.append(temp)

areaList.sort()
print(len(areaList))
for i in areaList:
    print(i, end=" ")
