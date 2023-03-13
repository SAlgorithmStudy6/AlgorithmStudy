# pypy로 제출
import sys

sys.stdin = open("./input.txt", "r", encoding="utf-8")
input = sys.stdin.readline
n = int(input())
matrix = [list(map(int, input().split())) for _ in range(n)]


def dfs(x, y, status):
    global count

    if matrix[y][x] == 0 and y == n - 1 and x == n - 1:
        count += 1
        # return    들어가면 시간초과

    if status == 0 or status == 2:
        nY = y
        nX = x + 1
        if 0 <= nY < n and 0 <= nX < n and matrix[nY][nX] == 0:
            dfs(nX, nY, 0)
    if status == 1 or status == 2:
        nY = y + 1
        nX = x
        if 0 <= nY < n and 0 <= nX < n and matrix[nY][nX] == 0:
            dfs(nX, nY, 1)

    nY = y + 1
    nX = x + 1
    if (
        0 <= nY < n
        and 0 <= nX < n
        and matrix[nY][nX] == 0
        and matrix[nY][x] == 0
        and matrix[y][nX] == 0
    ):
        dfs(nX, nY, 2)


count = 0
dfs(1, 0, 0)

print(count)

# DP 풀이법
# https://bit.ly/3QGoKuQ
