import sys
from collections import deque
sys.stdin = open("input.txt", "r", encoding="UTF-8")

T = int(input())

pipeList = [[], [0, 1, 2, 3], [0, 1], [2, 3], [0, 3], [1, 3], [1, 2], [0, 2]]
typeList = [1, 0, 3, 2]

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

def bfs(depth, y, x):
    q = deque()
    q.append((depth, y, x))
    while q:
        nowDepth, nowY, nowX = q.popleft()
        if nowDepth == l-1:
            continue
        pipe = matrix[nowY][nowX]
        for dir in pipeList[pipe]:
            nY = nowY + dy[dir]
            nX = nowX + dx[dir]
            if nY < 0 or nX < 0 or nY >= n or nX >= m:
                continue
            if matrix[nY][nX] == 0:
                continue
            if visited[nY][nX] > 0:
                continue
            nextPipe = matrix[nY][nX]
            if typeList[dir] in pipeList[nextPipe]:
                visited[nY][nX] = nowDepth + 1
                q.append((nowDepth + 1, nY, nX))

for test_case in range(1, T+1):
    n, m, r, c, l = map(int, input().split())
    matrix = [list(map(int, input().split())) for _ in range(n)]
    visited = [[0] * m for _ in range(n)]
    result = 0
    visited[r][c] = 1
    bfs(0, r, c)

    for y in range(n):
        for x in range(m):
            if visited[y][x]:
                result += 1

    print("#{} {}".format(test_case, result))


