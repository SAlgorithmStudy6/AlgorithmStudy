import sys

sys.setrecursionlimit(10000)
input = sys.stdin.readline

N = int(input())

matrix = [list(map(int, input().split())) for _ in range(N)]

maxHeight = max(map(max, matrix))

dy = [0, 0, 1, -1]
dx = [1, -1, 0, 0]


def dfs(x, y, rain):

    for i in range(4):
        nY = y + dy[i]
        nX = x + dx[i]

        if (
            (0 <= nX < N)
            and (0 <= nY < N)
            and visited[nY][nX] == False
            and matrix[nY][nX] > rain
        ):
            visited[nY][nX] = True
            dfs(nX, nY, rain)


answer = 0
for rain in range(maxHeight):
    visited = [[False] * N for _ in range(N)]
    area = 0
    for y in range(N):
        for x in range(N):
            if visited[y][x] == False and matrix[y][x] > rain:
                visited[y][x] = True
                area += 1
                dfs(x, y, rain)

    answer = max(answer, area)

print(answer)
